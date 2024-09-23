package org.example;

import org.example.commands.available.*;
import org.example.managers.CollectionManager;
import org.example.managers.CommandManager;
import org.example.managers.DumpManager;
import org.example.utility.ConsoleManager;

import java.io.IOException;
import java.net.InetAddress;

public class Server {
    public static final int PORT = 23517;
    private static final ConsoleManager console = new ConsoleManager();

    public static void main(String[] args) {

        DumpManager dumpManager = new DumpManager("test.csv", console);
        CollectionManager collectionManager = CollectionManager.getInstance(dumpManager);
        collectionManager.loadCollection(dumpManager);

        CommandManager commandManager = new CommandManager(){{
            registration("help", new HelpCommand(this));
            registration("info", new InfoCommand(collectionManager));
            registration("show", new ShowCommand(collectionManager));
            registration("update", new UpdateCommand(collectionManager));
            registration("remove_by_id", new RemoveByIdCommand(collectionManager));
            registration("clear", new ClearCommand(collectionManager));
            registration("execute_script", new ExecuteScriptCommand(dumpManager, collectionManager));
            registration("exit", new ExitCommand(collectionManager, dumpManager));
            registration("remove_head", new RemoveHeadCommand(collectionManager));
            registration("remove_lower", new RemoveLowerCommand(collectionManager));
            registration("add_if_max", new AddIfMaxCommand(collectionManager));
            registration("average_of_annual_turnover", new AverageOfAnnualTurnoverCommand(collectionManager));
            registration("count_by_postal_address", new CountByPostalAddressCommand(collectionManager));
            registration("print_field_descending_employees_count", new PrintFieldDescendingEmployeesCountCommand(collectionManager));
            registration("add", new AddCommand(collectionManager));
        }};

        try{
            UDPServer server = new UDPServer(InetAddress.getByName("localhost"), PORT, console, commandManager, collectionManager, dumpManager);
            server.run();
            System.out.println();
        }catch (IOException e){
            console.printError("При подключении что-то пошло не так" + e.getMessage());
        }

    }
}