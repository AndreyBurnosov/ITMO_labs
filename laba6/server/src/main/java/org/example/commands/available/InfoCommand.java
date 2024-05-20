package org.example.commands.available;

import org.example.commands.abstracts.Command;
import org.example.managers.CollectionManager;
import org.example.network.Request;
import org.example.network.Response;
import org.example.utility.Status;

public class InfoCommand extends Command {

    private CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        super("info", "вывести в стандартный поток вывода информацию о коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request){

        return new Response(Status.OK, "тип коллекции: " + collectionManager.collectionType()
                + ", дата инициализации: " + collectionManager.getLastInitTime()
                + ", размер: " + collectionManager.collectionSize());
    }
}
