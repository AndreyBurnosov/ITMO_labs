package org.example.creating;

import org.example.models.Location;
import org.example.utility.ConsoleManager;

public class LocationCreating {

    public static Location locationCreating(ConsoleManager console) {

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
                    break;
                } catch (NumberFormatException e){
                    console.printError("Значение должно быть числом");
                }
            }
        }
        float y;
        while (true) {
            console.println("Введите координату y");
            var line = "";
            if (console.getFileMode()) {
                line = console.getScanner().nextLine().trim();
            } else {
                line = console.readln().trim();
            }
            if (line.equals("exit")) System.exit(1);
            if (line.isEmpty()) {
                console.printError("Значение не было введено");
            }
            if (!line.isEmpty()) {
                try {
                    y = Float.parseFloat(line);
                    if (console.getFileMode()) {
                        console.println(y);
                    }
                    break;
                } catch (NumberFormatException e) {
                    console.printError("Значение должно быть числом");
                }
            }
        }
        Integer z;
        while (true){
            console.println("Введите координату z");
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
                    z = Integer.parseInt(line);
                    if (console.getFileMode()) {
                        console.println(z);
                    }
                    break;
                } catch (NumberFormatException e){
                    console.printError("Значение должно быть числом");
                }
            }
        }
        return new Location(x,y,z);

    }
}
