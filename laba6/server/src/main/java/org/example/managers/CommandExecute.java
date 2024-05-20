package org.example.managers;

import org.example.commands.abstracts.Command;
import org.example.network.Request;
import org.example.network.Response;
import org.example.utility.Status;

public class CommandExecute {
    private final Request request;
    private CommandManager commandManager;
    public CommandExecute(Request request, CommandManager commandManager){
        this.request = request;
        this.commandManager = commandManager;
    }

    public Response executeCommand(Request request){
        Command command = commandManager.getCommand(request.getCommand());
        if (command == null){
            return new Response(Status.ERROR, "Такой команды не существует");
        }
        return command.execute(request);
    }
}
