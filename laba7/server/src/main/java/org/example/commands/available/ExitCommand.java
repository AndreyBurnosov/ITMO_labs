package org.example.commands.available;

import org.example.commands.abstracts.Command;
import org.example.managers.CollectionManager;
import org.example.managers.DumpManager;
import org.example.network.Request;
import org.example.network.Response;
import org.example.utility.Status;

public class ExitCommand extends Command {
    private CollectionManager collectionManager;
    private DumpManager dumpManager;
    public ExitCommand(CollectionManager collectionManager, DumpManager dumpManager){
        super("exit", "завершить программу (без сохранения в файл)");
        this.collectionManager = collectionManager;
        this.dumpManager = dumpManager;
    }

    public Response execute(Request request){
        return new Response(Status.EXIT, "");
    }
}
