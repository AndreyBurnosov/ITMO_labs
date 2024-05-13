package org.example.commands;

import org.example.creating.OrganisationCreating;
import org.example.managers.CollectionManager;
import org.example.managers.ConsoleManager;
import org.example.models.Organisation;

public class UpdateCommand extends Command{

    private ConsoleManager console;
    private CollectionManager collectionManager;
    public UpdateCommand(ConsoleManager console, CollectionManager collectionManager){
        super("update", "изменить элемент по id");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    public boolean execute(String argument){
        if (argument.isEmpty()) {
            console.printError("Введите id для обновления элемента");
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
        console.println("Обновите данные организации");
        if (collectionManager.addById(id, OrganisationCreating.organisationCreating(console))) return true;
        console.printError("Произошла ошибка при обновлении элемента");
        return false;
    }
}
