package org.example.managers;

import org.example.commands.Command;

import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class Runner {

    ConsoleManager console;
    CommandManager commandManager;
    ArrayList<String> usedFileNames = new ArrayList<>();

   public Runner(ConsoleManager console, CommandManager commandManager){
       this.console = console;
       this.commandManager = commandManager;
   }

   public void commandGetting(){
       try{

           String[] userCommand;

           while (true){
                userCommand = (console.readln().trim()+" ").split(" ", 2);
                if (userCommand[0].equals("")) {console.printError("Команда не введена");
                } else {
                    if (commandManager.getCommand(userCommand[0]) == null) {
                        console.printError("Команда введена некорректно");
                    } else {
                        userCommand[1] = userCommand[1].trim();

                        if (userCommand[0].equals("exit")) break;
                        if (userCommand[0].equals("execute_script")) {
                            boolean commandStatus = scriptLaunch(userCommand);}
                        boolean commandStatus = commandLaunch(userCommand);

                        if (commandStatus) console.println("Команда выполнена успешно");}}
           }

       }catch(NoSuchElementException e){
           console.printError("Пользовательский ввод не обнаружен");
       }catch (IllegalStateException e){
           console.printError("Непредвиденная ошибка");
       }
   }

   public boolean scriptLaunch(String[] userCommand){
       boolean commandStatus = true;
       if (userCommand[1].isEmpty()) {
           console.printError("Введите название файла со скриптом");
           return false;
       }
       String fileName = userCommand[1];
       try{
           usedFileNames.add(fileName);
           String line;
           String[] scriptCommand = {" ", " "};
           File file = new File(fileName);
           console.setFileMode(true);
           console.setScanner(new Scanner(file));
           while (commandStatus && console.getScanner().hasNext() && (line = console.getScanner().nextLine()) != null){
               scriptCommand = (line.trim()+" ").split(" ", 2);
               scriptCommand[1] = scriptCommand[1].trim();
               while (scriptCommand[0].isEmpty() && console.getScanner().nextLine() != null){
                   scriptCommand = (line.trim()+" ").split(" ", 2);
                   scriptCommand[1] = scriptCommand[1].trim();
               }
               if (scriptCommand[0].equals("execute_script")){
                   if (usedFileNames.contains(fileName)){
                       console.printError("Скрипты не могут вызываться рекурсивно");
                       commandStatus = false;
                   }
               }
               commandStatus = commandLaunch(scriptCommand);
           }
       }catch(IOException e){
           console.printError("Ошибка чтения файла");
       }
       console.setFileMode(false);
       usedFileNames.remove(fileName);
       return commandStatus;
   }

    public boolean commandLaunch(String[] userCommand) {
        Command command = commandManager.getCommand(userCommand[0]);
        return command.execute(userCommand[1]);
    }

}
