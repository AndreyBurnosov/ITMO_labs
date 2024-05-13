package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.managers.ConsoleManager;

public class RemoveLowerCommand extends Command{

    private ConsoleManager console;
    private CollectionManager collectionManager;

    public RemoveLowerCommand(ConsoleManager console, CollectionManager collectionManager) {
        super("remove_lower", " удалить из коллекции все элементы, меньшие, чем заданный");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    public boolean execute(String argument){
        if (argument.isEmpty()) {
            console.printError("Введите id");
            return false;
        }
        if (!argument.matches("\\d+")){
            console.printError("id должен быть числом");
            return false;
        }
        Integer id = Integer.parseInt(argument);
        collectionManager.removeLower(id);
        return true;
    }
}
