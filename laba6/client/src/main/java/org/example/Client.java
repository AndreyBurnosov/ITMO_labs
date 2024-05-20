package org.example;

import org.example.managers.Runner;
import org.example.network.UDPClient;
import org.example.utility.ConsoleManager;

import java.net.*;

public class Client {
    public static final int PORT = 23516;

    public static void main(String[] args) {

        ConsoleManager console = new ConsoleManager();
        try{
            DatagramSocket socket = new DatagramSocket();
            UDPClient client = new UDPClient(InetAddress.getByName("localhost"), PORT, socket);
            Runner runner = new Runner(console, client);
            runner.commandGetting();
        } catch (UnknownHostException | SocketException e) {
            console.printError("Ошибка при подключении к серверу");
        }
    }
}