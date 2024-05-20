package org.example.commands.available;

import org.example.commands.abstracts.Command;
import org.example.managers.CollectionManager;
import org.example.network.Request;
import org.example.network.Response;
import org.example.utility.Status;

public class ClearCommand extends Command {
    private CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager) {
        super("clear", "очистить коллекцию");
        this.collectionManager = collectionManager;
    }
    @Override
    public Response execute(Request request){
        collectionManager.clearCollection();
        return new Response(Status.OK, "Коллекция успешно очищена");
    }
}
