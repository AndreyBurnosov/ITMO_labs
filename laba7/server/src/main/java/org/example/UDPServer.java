package org.example;

import org.example.database.service.UserService;
import org.example.managers.CollectionManager;
import org.example.managers.CommandExecute;
import org.example.managers.CommandManager;
import org.example.managers.DumpManager;
import org.example.models.User;
import org.example.network.Request;
import org.example.network.Response;
import org.example.network.Serialisation;
import org.example.utility.ConsoleManager;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import org.example.utility.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UDPServer {
    private final DatagramChannel channel;
    private final SocketAddress address;
    private final Logger logger;
    private final ConsoleManager console;
    private final CommandManager commandManager;
    private final DumpManager dumpManager;
    private final CollectionManager collectionManager;
    private SocketAddress addr;

    private final ForkJoinPool readPool = new ForkJoinPool();
    private final ForkJoinPool writePool = new ForkJoinPool();
    private final ForkJoinPool processPool = new ForkJoinPool();

    public UDPServer(InetAddress host, int port, ConsoleManager console, CommandManager commandManager, CollectionManager collectionManager, DumpManager dumpManager) throws IOException {
        this.logger = LoggerFactory.getLogger(UDPServer.class);
        this.address = new InetSocketAddress(host, port);
        this.channel = DatagramChannel.open().bind(this.address);
        this.channel.configureBlocking(false);
        this.console = console;
        this.commandManager = commandManager;
        this.logger.info("DatagramChannel подключен к {}", address);
        this.collectionManager = collectionManager;
        this.dumpManager = dumpManager;
    }

    public void sendData(Response response) {
        writePool.execute(() -> {
            try {
                byte[] serializedMessage = Serialisation.serializeObject(response);
                ByteBuffer buf = ByteBuffer.wrap(serializedMessage);
                channel.send(buf, addr);
                buf.clear();
                this.logger.info("Отправлены данные на {}", addr);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        });
    }

    public CompletableFuture<Request> receiveData() {
        return CompletableFuture.supplyAsync(() -> {
            ByteBuffer receiveBuf = ByteBuffer.allocate(65000);
            SocketAddress address = null;
            try {
                while (address == null) {
                    address = channel.receive(receiveBuf);
                    if (address == null) continue; // ожидание получения данных

                    String command;
                    if (System.in.available() > 0) {
                        command = console.readlnServer().trim();
                        if (command.equals("save")) {
                            collectionManager.saveCollection(dumpManager);
                        }
                    }
                }
                receiveBuf.flip();
                byte[] toDeserialize = receiveBuf.array();
                Request request = (Request) Serialisation.deserializeObject(toDeserialize);
                addr = address;
                this.logger.info("Получен запрос от {}", address);
                return request;
            } catch (IOException | ClassNotFoundException e) {
                logger.error("Ошибка получения данных", e);
                return null;
            }
        }, readPool);
    }

    public void processRequest(Request request) {
        processPool.execute(() -> {
            try {
                Response response;
                Optional<User> optionalUser = UserService.getInstance().findByUsername(request.getUsername());
                if (optionalUser.isEmpty()) {
                    UserService.getInstance().save(request.getUsername(), request.getPassword());
                    optionalUser = UserService.getInstance().findByUsername(request.getUsername());
                }

                if (optionalUser.isEmpty()) {
                    response = new Response(Status.ERROR, "Ошибка при регистрации пользователя");
                    sendData(response);
                    return;
                }

                User user = optionalUser.get();
                if (!UserService.getInstance().checkPassword(user, request.getPassword())) {
                    response = new Response(Status.ERROR, "Неверный пароль");
                    sendData(response);
                    return;
                }

                request.setUser(user);

                CommandExecute commandExecute = new CommandExecute(request, commandManager);
                response = commandExecute.executeCommand(request);
                sendData(response);
            } catch (Exception e) {
                logger.error("Ошибка обработки запроса", e);
            }
        });
    }

    public void run() {
        logger.info("Сервер начал работу");
        boolean running = true;

        while (running) {
            try {
                CompletableFuture<Request> futureRequest = receiveData();
                futureRequest.thenAccept(request -> {
                    if (request != null) {
                        processRequest(request);
                        if (request.getCommand().equals("exit")) {
                            logger.info("Сервер завершает работу");
                        }
                    }
                }).exceptionally(ex -> {
                    logger.error("Ошибка при обработке запроса", ex);
                    return null;
                });

                TimeUnit.MILLISECONDS.sleep(100);

            } catch (Exception e) {
                logger.error("Ошибка в обработке данных", e);
            }
        }

        readPool.shutdown();
        processPool.shutdown();
        writePool.shutdown();
    }
}