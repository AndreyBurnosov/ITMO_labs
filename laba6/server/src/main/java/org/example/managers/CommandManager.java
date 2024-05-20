package org.example.managers;

import org.example.commands.abstracts.Command;
import org.example.network.Request;
import org.example.network.Response;
import org.example.utility.Status;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {

    private Map<String, Command> commandMap = new HashMap<>();

    public void registration(String name, Command command){
        commandMap.put(name,command);
    }

    public Command getCommand(String name){
        return commandMap.get(name);
    }
    public Map<String, Command> getCommandMap(){return commandMap;}
    public Response execute (Request request){
        if (commandMap.containsKey(request.getCommand())){
            return getCommand(request.getCommand()).execute(request);
        }
        return new Response(Status.ERROR, "Такой команды не существует");
    }

}
