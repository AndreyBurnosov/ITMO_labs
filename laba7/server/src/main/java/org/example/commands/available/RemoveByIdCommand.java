package org.example.commands.available;

import org.example.commands.abstracts.Command;
import org.example.managers.CollectionManager;
import org.example.network.Request;
import org.example.network.Response;
import org.example.utility.Status;

public class RemoveByIdCommand extends Command {
    private CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove_by_id", "удалить элемент по id");
        this.collectionManager = collectionManager;
    }

    public Response execute(Request request) {
        if (request.getArgument().isEmpty()) {
            return new Response(Status.ASK_ID, "Введите id для удаления элемента");
        }
        if (!request.getArgument().matches("\\d+")) {
            return new Response(Status.ERROR, "id должен быть числом");
        }
        Integer id = Integer.parseInt(request.getArgument());
        if (collectionManager.getCollectionById(id) == null) {
            return new Response(Status.ERROR, "Элемента с таким id не существует");
        }
        if (!collectionManager.getCollectionById(id).getOwnerId().equals(request.getUser().getId())) {
            return new Response(Status.ERROR, "Вы не можете удалить элемент, который не принадлежит вам");
        }

        collectionManager.removeById(id, request.getUser());
        return new Response(Status.OK, "Элемент успешно удалён");
    }
}