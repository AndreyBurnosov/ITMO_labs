package org.example.commands;

import org.example.creating.OrganisationCreating;
import org.example.managers.CollectionManager;
import org.example.managers.ConsoleManager;

public class AverageOfAnnualTurnoverCommand extends Command{

    private ConsoleManager console;
    private CollectionManager collectionManager;
    public AverageOfAnnualTurnoverCommand(ConsoleManager console, CollectionManager collectionManager){
        super("average_of_annual_turnover", "выводит среднее значение поля annualTurnover для всех элементов коллекции");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    public boolean execute(String argument){
        if (!argument.isEmpty()){
            console.printError("Данная команда не имеет аргументов");
            return false;
        }
        console.println(collectionManager.averageOfAnnualTurnove());
        return true;
    }
}
