package org.example.commands;

import org.example.creating.AddressCreating;
import org.example.creating.OrganisationCreating;
import org.example.managers.CollectionManager;
import org.example.managers.ConsoleManager;
import org.example.models.Address;

public class CountByPostalAddressCommand extends Command{

    private ConsoleManager console;
    private CollectionManager collectionManager;
    public CountByPostalAddressCommand(ConsoleManager console, CollectionManager collectionManager){
        super("count_by_postal_address", "выводит количество элементов, значение поля postalAddress которых равно заданному");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    public boolean execute(String argument){
        if (!argument.isEmpty()){
            console.printError("Данная команда не имеет аргументов");
            return false;
        }
        Address postalAddress = AddressCreating.addressCreating(console);

        console.println(collectionManager.compareAddress(postalAddress));
        return true;
    }
}
