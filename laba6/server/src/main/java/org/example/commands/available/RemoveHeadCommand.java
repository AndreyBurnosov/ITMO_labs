package org.example.commands.available;

import org.example.commands.abstracts.Command;
import org.example.managers.CollectionManager;
import org.example.models.Organisation;
import org.example.network.Request;
import org.example.network.Response;
import org.example.utility.Status;

public class RemoveHeadCommand extends Command {
    private CollectionManager collectionManager;
    public RemoveHeadCommand(CollectionManager collectionManager){
        super("remove_head", "вывести и удалить первый элемент");
        this.collectionManager = collectionManager;
    }

    public Response execute(Request request){
        if (!request.getArgument().isEmpty()){
            return new Response(Status.ERROR, "Данная команда не имеет аргументов");
        }
        if (collectionManager.isEmpty()) {
            return new Response(Status.ERROR, "Коллекция пуста");
        }
        Organisation a = collectionManager.getFirstElement();
        collectionManager.removeById(a.getId());
        return new Response(Status.OK, a.toString());
    }
}
