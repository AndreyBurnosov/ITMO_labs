package org.example.creating;

import org.example.managers.ConsoleManager;
import org.example.models.Coordinates;

public class CoordinatesCreating {
    public static Coordinates coordinatesCreating(ConsoleManager console) {
        int x;
        while (true){
            console.println("Введите координату x");
            var line = "";
            if (console.getFileMode()){
                line = console.getScanner().nextLine().trim();
            } else {
                line = console.readln().trim();
            }
            if (line.equals("exit")) System.exit(1);
            if (line.isEmpty()) {
                console.printError("Значение не было введено");
            }
            if (!line.isEmpty()){
                try{
                    x = Integer.parseInt(line);
                    if (console.getFileMode()) {
                        console.println(x);
                    }
                    if (x <= -304){
                        console.printError("Значение должно быть больше -304");
                    } else break;
                } catch (NumberFormatException e){
                    console.printError("Значение должно быть числом");

                }
            }
        }
        Double y;
        while (true){
            console.println("Введите координату y");
            var line = "";
            if (console.getFileMode()){
                line = console.getScanner().nextLine().trim();
            } else {
                line = console.readln().trim();
            }
            if (line.equals("exit")) System.exit(1);
            if (line.isEmpty()) {
                console.printError("Значение не было введено");
            }
            if (!line.isEmpty()){
                try{
                    y = Double.parseDouble(line);
                    if (console.getFileMode()) {
                        console.println(y);
                    }
                    if (y <= -247){
                        console.printError("Значение должно Быть больше -247");
                    } else break;
                } catch (NumberFormatException e){
                    console.printError("Значение должно быть числом");
                }
            }
        }
        return new Coordinates(x,y);
    }
}
