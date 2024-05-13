package org.example.commands;

import org.example.creating.OrganisationCreating;
import org.example.managers.CollectionManager;
import org.example.managers.ConsoleManager;

public class ClearCommand extends Command{

    private ConsoleManager console;
    private CollectionManager collectionManager;
    public ClearCommand(ConsoleManager console, CollectionManager collectionManager){
        super("clear", "очищает коллекцию");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    public boolean execute(String argument){
        if (!argument.isEmpty()){
            console.printError("Данная команда не имеет аргументов");
            return false;
        }
        collectionManager.clearCollection();
        return true;
    }
}
