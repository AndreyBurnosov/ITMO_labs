package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.managers.ConsoleManager;

public class InfoCommand extends Command{

    private ConsoleManager console;
    private CollectionManager collectionManager;

    public InfoCommand(ConsoleManager console, CollectionManager collectionManager) {
        super("info", "выводит в стандартный поток вывода информацию о коллекции");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument){
        if (!argument.isEmpty()){
            console.printError("Данная команда не имеет аргументов, так как она выводит информацию о коллекции");
        }
        console.println("тип коллекции: " + collectionManager.collectionType()
                + ", дата инициализации: " + collectionManager.getLastInitTime()
                + ", размер: " + collectionManager.collectionSize());
        return true;
    }
}
