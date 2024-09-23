package org.example.commands.available;

import org.example.commands.abstracts.Command;
import org.example.managers.CollectionManager;
import org.example.models.Organisation;
import org.example.network.Request;
import org.example.network.Response;
import org.example.utility.Status;

public class AddIfMaxCommand extends Command {

    private CollectionManager collectionManager;
    public AddIfMaxCommand(CollectionManager collectionManager){
        super("add_if_max", "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        this.collectionManager = collectionManager;
    }

    public Response execute(Request request){
        if (!request.getArgument().isEmpty()){
            return new Response(Status.ERROR, "Данная команда не имеет аргументов");
        }
        if (request.getOrganisation() == null) {
            return new Response(Status.ASK_ORGANISATION_WITH_ID, "Введите данные элемента для добавления");
        }
        Organisation org = request.getOrganisation();
        if (collectionManager.getMaxValue() < org.getId()) {
            collectionManager.add(org, request.getUser());
        };
        return new Response(Status.OK, "");
    }
}
