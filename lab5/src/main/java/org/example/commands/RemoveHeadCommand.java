package org.example.commands;

import org.example.creating.OrganisationCreating;
import org.example.managers.CollectionManager;
import org.example.managers.ConsoleManager;
import org.example.models.Organisation;

public class RemoveHeadCommand extends Command{

    private ConsoleManager console;
    private CollectionManager collectionManager;
    public RemoveHeadCommand(ConsoleManager console, CollectionManager collectionManager){
        super("remove_head", "вывести и удалить первый элемент");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    public boolean execute(String argument){
        if (!argument.isEmpty()){
            console.printError("Данная команда не имеет аргументов");
            return false;
        }
        if (collectionManager.isEmpty()) {
            console.printError("Коллекция пуста");
            return false;
        }
        Organisation a = collectionManager.getFirstElement();
        console.println(a);
        collectionManager.removeById(a.getId());
        return true;

    }
}
