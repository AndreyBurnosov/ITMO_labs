package org.example;

import org.example.commands.*;
import org.example.managers.*;

import java.util.Map;

public class Main {

    public static void main(String[] args) {

        var console = new ConsoleManager();
        DumpManager dumpManager = new DumpManager(System.getenv("OUTPUT_FILE"), console);
        CollectionManager collectionManager = new CollectionManager(dumpManager);
        collectionManager.loadCollection(dumpManager);

        CommandManager commandManager = new CommandManager(){{
            registration("help", new HelpCommand(console, this));
            registration("info", new InfoCommand(console,collectionManager));
            registration("show", new ShowCommand(console, collectionManager));
            registration("update", new UpdateCommand(console, collectionManager));
            registration("remove_by_id", new RemoveByIdCommand(console, collectionManager));
            registration("clear", new ClearCommand(console, collectionManager));
            registration("save", new SaveCommand(dumpManager, collectionManager));
            registration("execute_script", new ExecuteScriptCommand(dumpManager, collectionManager, console));
            registration("exit", new ExitCommand(console));
            registration("remove_head", new RemoveHeadCommand(console, collectionManager));
            registration("remove_lower", new RemoveLowerCommand(console, collectionManager));
            registration("add_if_max", new AddIfMaxCommand(console, collectionManager));
            registration("average_of_annual_turnover", new AverageOfAnnualTurnoverCommand(console, collectionManager));
            registration("count_by_postal_address", new CountByPostalAddressCommand(console, collectionManager));
            registration("print_field_descending_employees_count", new PrintFieldDescendingEmployeesCountCommand(console, collectionManager));
            registration("add", new AddCommand(console, collectionManager));
        }};
        Runner runner = new Runner(console, commandManager);
        runner.commandGetting();

    }
}