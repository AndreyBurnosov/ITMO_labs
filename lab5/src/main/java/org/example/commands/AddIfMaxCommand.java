package org.example.commands;

import org.example.creating.OrganisationCreating;
import org.example.managers.CollectionManager;
import org.example.managers.ConsoleManager;
import org.example.models.Organisation;

public class AddIfMaxCommand extends Command{

    private ConsoleManager console;
    private CollectionManager collectionManager;
    public AddIfMaxCommand(ConsoleManager console, CollectionManager collectionManager){
        super("add_if_max", "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    public boolean execute(String argument){
        if (!argument.isEmpty()){
            console.printError("Данная команда не имеет аргументов");
            return false;
        }
        Organisation org = OrganisationCreating.organisationCreatingWithId(console);
        if (collectionManager.getMaxValue() < org.getId()) {
            collectionManager.add(org);
        };
        return true;
    }
}
