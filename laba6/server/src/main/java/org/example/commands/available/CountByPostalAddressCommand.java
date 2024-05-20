package org.example.commands.available;

import org.example.commands.abstracts.Command;
import org.example.managers.CollectionManager;
import org.example.models.Address;
import org.example.network.Request;
import org.example.network.Response;
import org.example.utility.Status;

public class CountByPostalAddressCommand extends Command {

    private CollectionManager collectionManager;
    public CountByPostalAddressCommand(CollectionManager collectionManager){
        super("count_by_postal_address", "выводит количество элементов, значение поля postalAddress которых равно заданному");
        this.collectionManager = collectionManager;
    }

    public Response execute(Request request){
        if (!request.getArgument().isEmpty()){
            return new Response(Status.ERROR, "Данная команда не имеет аргументов");
        }
        if (request.getAddress() == null) {
            return new Response(Status.ASK_ADDRESS, "Введите Адрес");
        }

        Address postalAddress = request.getAddress();

        return new Response(Status.OK, String.valueOf(collectionManager.compareAddress(postalAddress)));
    }
}
