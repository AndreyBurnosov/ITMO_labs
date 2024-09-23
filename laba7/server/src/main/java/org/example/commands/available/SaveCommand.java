package org.example.commands.available;

import org.example.commands.abstracts.Command;
import org.example.managers.CollectionManager;
import org.example.managers.DumpManager;
import org.example.network.Request;
import org.example.network.Response;
import org.example.utility.Status;

public class SaveCommand extends Command {

    private DumpManager dumpManager;
    private CollectionManager collectionManager;

    public SaveCommand(DumpManager dumpManager, CollectionManager collectionManager) {
        super("save", "сохранить коллекцию в файл");
        this.dumpManager = dumpManager;
        this.collectionManager = collectionManager;
    }

    public Response execute(Request request){

        if (collectionManager.saveCollection(dumpManager)) {
            return new Response(Status.OK, "Коллекция сохранена в файл");
        }else {
            return new Response(Status.ERROR, "Ошибка в сохранении коллекции");
        }
    }

}
