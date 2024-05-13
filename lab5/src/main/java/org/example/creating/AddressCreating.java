package org.example.creating;

import org.example.managers.ConsoleManager;
import org.example.models.Address;
import org.example.models.Coordinates;
import org.example.models.Location;

public class AddressCreating {
    public static Address addressCreating(ConsoleManager console) {
        String street;
        while (true){
            console.println("Введите улицу");
            var line = "";
            if (console.getFileMode()){
                line = console.getScanner().nextLine().trim();
            } else {
                line = console.readln().trim();
            }
            if (line.equals("exit")) System.exit(1);
            street = line;
            break;
        }
        Location town = LocationCreating.locationCreating(console);
        return new Address(street, town);
    }
}
