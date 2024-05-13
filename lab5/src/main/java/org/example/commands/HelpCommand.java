package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.managers.CommandManager;
import org.example.managers.ConsoleManager;

public class HelpCommand extends Command{

    private ConsoleManager console;
    private CommandManager commandManager;

    public HelpCommand(ConsoleManager console, CommandManager commandManager) {
        super("help", "выводит справку по доступным командам");
        this.console = console;
        this.commandManager = commandManager;
    }

    public boolean execute(String argument){
        if (!argument.isEmpty()){
            console.printError("Данная команда не имеет аргументов");
            return false;
        }
        commandManager.getCommandMap().values().forEach(command -> {
            console.println(command.getName() + " " + command.getDescription());
        });
        return true;
    }


}
