package org.example.commands.available;

import org.example.commands.abstracts.Command;
import org.example.managers.CollectionManager;
import org.example.network.Request;
import org.example.network.Response;
import org.example.utility.Status;

public class PrintFieldDescendingEmployeesCountCommand extends Command {
    private CollectionManager collectionManager;
    public PrintFieldDescendingEmployeesCountCommand(CollectionManager collectionManager){
        super("print_field_descending_employees_count", "вывести значения поля employeesCount всех элементов в порядке убывания");
        this.collectionManager = collectionManager;
    }

    public Response execute(Request request){
        if (!request.getArgument().isEmpty()){
            return new Response(Status.ERROR, "Данная команда не имеет аргументов");
        }
        StringBuilder str = new StringBuilder();
        for (Object e: collectionManager.employessCount()) {
            str.append(e).append('\n');
        }
        return new Response(Status.OK, str.toString());
    }
}
