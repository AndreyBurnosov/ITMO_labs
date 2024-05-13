package org.example.commands;

import org.example.creating.OrganisationCreating;
import org.example.managers.CollectionManager;
import org.example.managers.ConsoleManager;

public class RemoveByIdCommand extends Command{

    private ConsoleManager console;
    private CollectionManager collectionManager;
    public RemoveByIdCommand(ConsoleManager console, CollectionManager collectionManager){
        super("remove_by_id", "удалить элемент по id");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    public boolean execute(String argument){
        if (argument.isEmpty()) {
            console.printError("Введите id для удаления элемента");
            return false;
        }
        if (!argument.matches("\\d+")){
            console.printError("id должен быть числом");
            return false;
        }
        Integer id = Integer.parseInt(argument);
        if (collectionManager.getCollectionById(id) == null ){
            console.printError("Элемента с таким id не существует");
            return false;
        }
        collectionManager.removeById(id);
        return true;
    }
}
