package org.example.commands.available;

import org.example.commands.abstracts.Command;
import org.example.managers.CollectionManager;
import org.example.network.Request;
import org.example.network.Response;
import org.example.utility.Status;

public class RemoveLowerCommand extends Command {
    private CollectionManager collectionManager;

    public RemoveLowerCommand(CollectionManager collectionManager) {
        super("remove_lower", " удалить из коллекции все элементы, меньшие, чем заданный");
        this.collectionManager = collectionManager;
    }

    public Response execute(Request request){
        Integer id;
        if (!request.getArgument().isEmpty()){
            id = Integer.parseInt(request.getArgument());
        } else {
            return new Response(Status.ASK_ID, "Введите id элемента");
        }
        collectionManager.removeLower(id);

        return new Response(Status.OK, "Элемент(ы) успешно удален(ы)");
    }
}
