package org.example.creating;


import org.example.models.Address;
import org.example.models.Coordinates;
import org.example.models.Organisation;
import org.example.models.OrganizationType;
import org.example.utility.ConsoleManager;

public class OrganisationCreating {

    public static Integer organisationSetId(ConsoleManager console){

        Integer id;
        while(true){
            console.println("Введите id организации");
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
                    id = Integer.parseInt(line);
                    if (id<=0){
                        console.printError("Значение должно быть больше нуля");
                    } else{
                        break;
                    }
                } catch (NumberFormatException e){
                    console.printError("Значение должно быть числом");

                }
            }
        }
        return id;
    }


    public static Organisation organisationCreating(ConsoleManager console){
        String name;
        while (true){
            console.println("Введите название организации");
            if (console.getFileMode()){
                name = console.getScanner().nextLine().trim();
            } else {
                name = console.readln().trim();
            }
            if (name.equals("exit")) System.exit(1);
            if (console.getFileMode()) {
                console.println(name);
            }
            if (name.isEmpty()){
                console.printError("Значение не было введено");
            } else{
                break;
            }
        }
        Coordinates coordinates = CoordinatesCreating.coordinatesCreating(console);
        double annualTurnover;
        while (true){
            console.println("Введите ежегодный оборот");
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
                    annualTurnover = Double.parseDouble(line);
                    if (console.getFileMode()) {
                        console.println(annualTurnover);
                    }
                    if (annualTurnover<=0){
                        console.printError("Значение должно быть больше нуля");
                    } else{
                        break;
                    }
                } catch (NumberFormatException e){
                    console.printError("Значение должно быть числом");

                }
            }
        }
        long employeesCount;
        while (true){
            console.println("Введите количество сотрудников");
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
                    employeesCount = Long.parseLong(line);
                    if (console.getFileMode()) {
                        console.println(employeesCount);
                    }
                    if (employeesCount <= 0){
                        console.printError("Значение должно быть больше нуля");
                    } else{
                        break;
                    }
                } catch (NumberFormatException e){
                    console.printError("Значение должно быть числом");

                }
            }
        }
        OrganizationType type;
        while (true){
            console.println("Введите тип организации из списка" + OrganizationType.names());
            var line = "";
            if (console.getFileMode()){
                line = console.getScanner().nextLine().trim();
            } else {
                line = console.readln().trim();
            }
            if (line.equals("exit")) System.exit(1);
            if (!line.isEmpty()){
                try {
                    type = OrganizationType.valueOf(line);
                    if (console.getFileMode()) {
                        console.println(type);
                    }
                    break;
                } catch (NullPointerException | IllegalArgumentException  e) {
                    console.printError("Введенное значение должно быть выбрано из списка: " + OrganizationType.names());
                }
            } else {
                type = null;
                break;
            }
        }

        Address postalAddress = AddressCreating.addressCreating(console);
        return new Organisation(name, coordinates, annualTurnover, employeesCount, type, postalAddress);

    }

    public static Organisation organisationCreatingWithId(ConsoleManager console){
        Integer id;
        while(true){
            console.println("Введите id организации");
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
                    id = Integer.parseInt(line);
                    if (id<=0){
                        console.printError("Значение должно быть больше нуля");
                    } else{
                        break;
                    }
                } catch (NumberFormatException e){
                    console.printError("Значение должно быть числом");

                }
            }
        }

        String name;
        while (true){
            console.println("Введите название организации");
            if (console.getFileMode()){
                name = console.getScanner().nextLine().trim();
            } else {
                name = console.readln().trim();
            }
            if (name.equals("exit")) System.exit(1);
            if (console.getFileMode()) {
                console.println(name);
            }
            if (name.isEmpty()){
                console.printError("Значение не было введено");
            } else{
                break;
            }
        }
        Coordinates coordinates = CoordinatesCreating.coordinatesCreating(console);
        double annualTurnover;
        while (true){
            console.println("Введите ежегодный оборот");
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
                    annualTurnover = Double.parseDouble(line);
                    if (console.getFileMode()) {
                        console.println(annualTurnover);
                    }
                    if (annualTurnover<=0){
                        console.printError("Значение должно быть больше нуля");
                    } else{
                        break;
                    }
                } catch (NumberFormatException e){
                    console.printError("Значение должно быть числом");

                }
            }
        }
        long employeesCount;
        while (true){
            console.println("Введите количество сотрудников");
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
                    employeesCount = Long.parseLong(line);
                    if (console.getFileMode()) {
                        console.println(employeesCount);
                    }
                    if (employeesCount <= 0){
                        console.printError("Значение должно быть больше нуля");
                    } else{
                        break;
                    }
                } catch (NumberFormatException e){
                    console.printError("Значение должно быть числом");

                }
            }
        }
        OrganizationType type;
        while (true){
            console.println("Введите тип организации из списка" + OrganizationType.names());
            var line = "";
            if (console.getFileMode()){
                line = console.getScanner().nextLine().trim();
            } else {
                line = console.readln().trim();
            }
            if (line.equals("exit")) System.exit(1);
            if (!line.isEmpty()){
                try {
                    type = OrganizationType.valueOf(line);
                    if (console.getFileMode()) {
                        console.println(type);
                    }
                    break;
                } catch (NullPointerException | IllegalArgumentException  e) {
                    console.printError("Введенное значение должно быть выбрано из списка: " + OrganizationType.names());
                }
            } else {
                type = null;
                break;
            }
        }

        Address postalAddress = AddressCreating.addressCreating(console);
        Organisation org = new Organisation(name, coordinates, annualTurnover, employeesCount, type, postalAddress);
        org.setId(id);
        return org;

    }
}
