package org.example.commands;

import org.example.creating.AddressCreating;
import org.example.managers.CollectionManager;
import org.example.managers.ConsoleManager;
import org.example.models.Address;

public class PrintFieldDescendingEmployeesCountCommand extends Command{

    private ConsoleManager console;
    private CollectionManager collectionManager;
    public PrintFieldDescendingEmployeesCountCommand(ConsoleManager console, CollectionManager collectionManager){
        super("print_field_descending_employees_count", "вывести значения поля employeesCount всех элементов в порядке убывания");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    public boolean execute(String argument){
        if (!argument.isEmpty()){
            console.printError("Данная команда не имеет аргументов");
            return false;
        }

        for (Object e: collectionManager.employessCount()) {
            console.println(e);
        }
        return true;
    }
}
