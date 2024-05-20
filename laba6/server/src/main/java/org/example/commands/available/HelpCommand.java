package org.example.commands.available;

import org.example.commands.abstracts.Command;
import org.example.managers.CommandManager;
import org.example.network.Request;
import org.example.network.Response;
import org.example.utility.Status;

public class HelpCommand extends Command {

    private CommandManager commandManager;

    public HelpCommand(CommandManager commandManager) {
        super("help", "вывести справку по доступным командам");
        this.commandManager = commandManager;
    }

    public Response execute(Request request){
        StringBuilder result = new StringBuilder();
        commandManager.getCommandMap().values().forEach(command -> {
            result.append(command.getName()).append(" ").append(command.getDescription()).append("\n");
        });
        return new Response(Status.OK, result.toString());
    }


}
