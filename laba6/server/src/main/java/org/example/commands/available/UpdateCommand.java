package org.example.commands.available;

import org.example.commands.abstracts.Command;
import org.example.managers.CollectionManager;
import org.example.models.Organisation;
import org.example.network.Request;
import org.example.network.Response;
import org.example.utility.Status;

public class UpdateCommand extends Command {
    private CollectionManager collectionManager;
    public UpdateCommand(CollectionManager collectionManager){
        super("update", "изменить элемент по id");
        this.collectionManager = collectionManager;
    }

    public Response execute(Request request){
        if (request.getArgument().isEmpty()) {
            return new Response(Status.ASK_ID, "Введите id для обновления элемента");
        }
        if (!request.getArgument().matches("\\d+")) {
            return new Response(Status.ERROR, "id должен быть числом");
        }
        Integer id = Integer.parseInt(request.getArgument());
        if (collectionManager.getCollectionById(id) == null ){
            return new Response(Status.ERROR, "Элемента с таким id не существует");
        }
        if (request.getOrganisation() == null) {
            return new Response(Status.ASK_ORGANISATION, "Введите данные элемента для добавления");
        }
        collectionManager.removeById(id);
        Organisation o = request.getOrganisation();
        o.setId(id);
        collectionManager.addById(id, o);
        return new Response(Status.OK, "Данные элемента обновлены");
    }
}
