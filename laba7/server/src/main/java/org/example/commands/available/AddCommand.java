package org.example.commands.available;

import org.example.commands.abstracts.Command;
import org.example.managers.CollectionManager;
import org.example.network.Request;
import org.example.network.Response;
import org.example.utility.Status;

public class AddCommand extends Command {
    private CollectionManager collectionManager;
    public AddCommand(CollectionManager collectionManager){
        super("add", "добавить элемент");
        this.collectionManager = collectionManager;
    }

    public Response execute(Request request){

            if (request.getOrganisation() == null) return new
                    Response(Status.ASK_ORGANISATION, "Введите данные элемента для добавления");
            collectionManager.add(request.getOrganisation(), request.getUser());
            return new Response(Status.OK, "Элемент успешно добавлен");
    }
}
