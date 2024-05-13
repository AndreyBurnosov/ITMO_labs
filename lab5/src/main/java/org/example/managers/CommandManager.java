package org.example.managers;

import org.example.commands.Command;

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

}
