package org.example.commands.available;

import org.example.commands.abstracts.Command;
import org.example.managers.CollectionManager;
import org.example.managers.CommandManager;
import org.example.managers.DumpManager;
import org.example.network.Request;
import org.example.network.Response;
import org.example.utility.Status;

public class ExecuteScriptCommand extends Command {

    private DumpManager dumpManager;
    private CollectionManager collectionManager;
    private CommandManager commandManager;


    public ExecuteScriptCommand(DumpManager dumpManager, CollectionManager collectionManager) {
        super("execute_script", "считать и исполнить скрипт из указанного файла. " +
                "В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме");
        this.dumpManager = dumpManager;
        this.collectionManager = collectionManager;
    }

    public Response execute(Request request){

        return new Response(Status.EXECUTE_SCRIPT, "Выполнения скрипта из файла " + request.getArgument());
    }
}
