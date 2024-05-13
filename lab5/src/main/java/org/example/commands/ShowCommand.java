package org.example.commands;

import org.example.creating.OrganisationCreating;
import org.example.managers.CollectionManager;
import org.example.managers.ConsoleManager;

public class ShowCommand extends Command{

    private ConsoleManager console;
    private CollectionManager collectionManager;
    public ShowCommand(ConsoleManager console, CollectionManager collectionManager){
        super("show", "Выводит все элементы");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    public boolean execute(String argument){
        if (!argument.isEmpty()){
            console.printError("Данная команда не имеет аргументов");
            return false;
        }
        collectionManager.printCollection(console);
        return true;
    }
}
