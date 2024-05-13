package org.example.commands;

import org.example.managers.ConsoleManager;

public class ExitCommand extends Command{

    private ConsoleManager console;
    public ExitCommand(ConsoleManager console){
        super("exit", "завершит программу (без сохранения в файл)");
        this.console = console;
    }

    public boolean execute(String argument){
        if (!argument.isEmpty()){
            console.printError("Данная команда не имеет аргументов");
            return false;
        }
        System.exit(1);
        return true;
    }
}
