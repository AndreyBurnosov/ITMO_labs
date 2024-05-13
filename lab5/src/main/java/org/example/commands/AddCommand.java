package org.example.commands;

import org.example.creating.OrganisationCreating;
import org.example.managers.CollectionManager;
import org.example.managers.ConsoleManager;

public class AddCommand extends Command{

    private ConsoleManager console;
    private CollectionManager collectionManager;
    public AddCommand(ConsoleManager console, CollectionManager collectionManager){
        super("add", "добавить элемент");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    public boolean execute(String argument){
        if (!argument.isEmpty()){
            console.printError("Данная команда не имеет аргументов");
            return false;
        }
        collectionManager.add(OrganisationCreating.organisationCreating(console));
        return true;
    }
}
