package org.example.commands.available;

import org.example.commands.abstracts.Command;
import org.example.managers.CollectionManager;
import org.example.network.Request;
import org.example.network.Response;
import org.example.utility.Status;

public class AverageOfAnnualTurnoverCommand extends Command {

    private CollectionManager collectionManager;
    public AverageOfAnnualTurnoverCommand(CollectionManager collectionManager){
        super("average_of_annual_turnover", "выводит среднее значение поля annualTurnover для всех элементов коллекции");
        this.collectionManager = collectionManager;
    }

    public Response execute(Request request){
        if (!request.getArgument().isEmpty()){
            return new Response(Status.ERROR, "Данная команда не имеет аргументов");
        }
        return new Response(Status.OK, String.valueOf(collectionManager.averageOfAnnualTurnove()));
    }
}
