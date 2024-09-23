package org.example.managers;

import org.example.creating.AddressCreating;
import org.example.creating.OrganisationCreating;
import org.example.exceptions.ScriptRecursionException;
import org.example.models.Address;
import org.example.network.UDPClient;
import org.example.exceptions.NotFoundCommandException;
import org.example.models.Organisation;
import org.example.network.Request;
import org.example.network.Response;
import org.example.utility.ConsoleManager;
import org.example.utility.Status;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Runner {
    ConsoleManager console;
    UDPClient client;
    ArrayList<String> usedFileNames = new ArrayList<>();
    boolean running = true;

    private String username;
    private String password;

    public Runner(ConsoleManager console, UDPClient client) {
        this.console = console;
        this.client = client;

        console.print("Введите имя пользователя: ");
        this.username = console.readln();
        console.print("Введите пароль: ");
        this.password = console.readln();
    }

    public void commandGetting() {
        try {

            String[] userCommand;

            while (running) {
                userCommand = (console.readln().trim() + " ").split(" ", 2);
                if (userCommand[0].isEmpty()) continue;

                Request request = new Request(userCommand[0].trim(), userCommand[1].trim());
                request.setUsername(username);
                request.setPassword(password);
                client.sendData(request);
                Response response = client.readData();

                switch (response.getStatus()) {
                    case ASK_ORGANISATION -> {
                        Organisation organisation = OrganisationCreating.organisationCreating(console);

                        Request request1 = new Request(userCommand[0].trim(), userCommand[1].trim(), organisation);
                        request1.setUsername(username);
                        request1.setPassword(password);

                        client.sendData(request1);
                        Response newResponse = client.readData();

                        if (newResponse.getStatus() != Status.OK) {
                            console.printError(newResponse.getMessage());
                        } else {
                            printResponse(newResponse);
                        }
                    }
                    case EXIT -> {
                        running = false;
                        console.println(response.getMessage());
                        System.exit(1);
                    }
                    case EXECUTE_SCRIPT -> {
                        console.setFileMode(true);
                        scriptLaunch(userCommand);
                    }
                    case ASK_ID -> {
                        Integer id = OrganisationCreating.organisationSetId(console);

                        client.sendData(new Request(userCommand[0].trim(), id.toString(), username, password));
                        Response newResponse = client.readData();

                        if (newResponse.getStatus() != Status.OK) {
                            console.printError(newResponse.getMessage());
                        } else {
                            printResponse(newResponse);
                        }
                    }
                    case ASK_ORGANISATION_WITH_ID -> {
                        Integer id = OrganisationCreating.organisationSetId(console);

                        Organisation organisation = OrganisationCreating.organisationCreating(console);

                        organisation.setId(id);

                        client.sendData(new Request(userCommand[0].trim(), userCommand[1].trim(), organisation, username, password));
                        Response newResponse = client.readData();

                        if (newResponse.getStatus() != Status.OK) {
                            console.printError(newResponse.getMessage());
                        } else {
                            printResponse(newResponse);
                        }
                    }
                    case ASK_ADDRESS -> {
                        Address address = AddressCreating.addressCreating(console);

                        client.sendData(new Request(userCommand[0].trim(), address, username, password));
                        Response newResponse = client.readData();

                        if (newResponse.getStatus() != Status.OK) {
                            console.printError(newResponse.getMessage());
                        } else {
                            printResponse(newResponse);
                        }
                    }
                    default -> {
                        printResponse(response);
                    }
                }
            }


        } catch (NoSuchElementException e) {
            console.printError("Пользовательский ввод не обнаружен");
        } catch (IllegalStateException e) {
            console.printError("Непредвиденная ошибка");
        } catch (NotFoundCommandException e) {
            console.printError(e.getMessage());
        } catch (IOException e) {
            console.printError("Неизвестная ошибка " + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void scriptLaunch(String[] userCommand) {
        boolean commandStatus = true;
        if (userCommand[1].isEmpty()) {
            console.printError("Введите название файла со скриптом");
            return;
        }
        String fileName = userCommand[1];
        try {
            usedFileNames.add(fileName);
            String line;
            String[] scriptCommand = {" ", " "};
            File file = new File(fileName.trim());
            console.setFileMode(true);
            console.setScanner(new Scanner(file));
            while (commandStatus && console.getScanner().hasNext() && (line = console.getScanner().nextLine()) != null) {
                scriptCommand = (line.trim() + " ").split(" ", 2);
                scriptCommand[1] = scriptCommand[1].trim();
                while (scriptCommand[0].isEmpty() && console.getScanner().nextLine() != null) {
                    scriptCommand = (line.trim() + " ").split(" ", 2);
                    scriptCommand[1] = scriptCommand[1].trim();
                }
                if (scriptCommand[0].equals("execute_script")) {
                    if (usedFileNames.contains(fileName)) throw new ScriptRecursionException();
                }
                console.println("Выполнение команды " + scriptCommand[0]);
                client.sendData(new Request(scriptCommand[0].trim(), scriptCommand[1].trim(), username, password));
                Response response = client.readData();

                switch (response.getStatus()) {
                    case ASK_ORGANISATION -> {
                        Organisation organisation = OrganisationCreating.organisationCreating(console);

                        client.sendData(new Request(scriptCommand[0].trim(), scriptCommand[1].trim(), organisation, username, password));
                        Response newResponse = client.readData();

                        if (newResponse.getStatus() != Status.OK) {
                            console.printError(newResponse.getMessage());
                        } else {
                            printResponse(newResponse);
                        }
                    }
                    case EXIT -> {
                        running = false;
                    }
                    case EXECUTE_SCRIPT -> {
                        console.setFileMode(true);
                        scriptLaunch(scriptCommand);
                    }
                    case ASK_ORGANISATION_WITH_ID -> {
                        Integer id = OrganisationCreating.organisationSetId(console);

                        Organisation organisation = OrganisationCreating.organisationCreating(console);

                        organisation.setId(id);

                        client.sendData(new Request(userCommand[0].trim(), userCommand[1].trim(), organisation, username, password));
                        Response newResponse = client.readData();

                        if (newResponse.getStatus() != Status.OK) {
                            console.printError(newResponse.getMessage());
                        } else {
                            printResponse(newResponse);
                        }
                    }
                    case ASK_ADDRESS -> {
                        Address address = AddressCreating.addressCreating(console);

                        client.sendData(new Request(userCommand[0].trim(), address, username, password));
                        Response newResponse = client.readData();

                        if (newResponse.getStatus() != Status.OK) {
                            console.printError(newResponse.getMessage());
                        } else {
                            printResponse(newResponse);
                        }
                    }
                    default -> {
                        printResponse(response);
                    }
                }

            }
        } catch (ScriptRecursionException e) {
            console.printError(e.getMessage());
        } catch (IOException e) {
            console.printError("Ошибка чтения файла");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        console.setFileMode(false);
        usedFileNames.remove(fileName);
    }

    public void printResponse(Response response) {
        switch (response.getStatus()) {
            case OK -> console.println(response.getMessage());
            case ERROR -> console.printError(response.getMessage());
            case WRONG_ARGUMENT -> console.printError(response.getMessage());
            default -> {
            }
        }
    }

}

