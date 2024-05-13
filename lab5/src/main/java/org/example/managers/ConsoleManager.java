package org.example.managers;

import org.example.utility.Console;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConsoleManager implements Console {

    private static final String prompt = "> ";
    private static boolean fileMode = false;
    private static Scanner scanner;

    /**
     * Выводит object.toString() в консоль
     * @param object Объект для печати
     */
    public void print(Object object){
        System.out.print(object);
    }

    /**
     * Выводит object.toString() + \n в консоль
     * @param object Объект для печати
     */
    public void println(Object object){
        System.out.println(object);
    }

    /**
     * Выводит object.toString() в консоль
     * @param object Ошибка для вывода
     */
    public void printError(Object object){
        System.out.println("\u001B[31m" + "Error: " + object + "\u001B[0m");
    }

    public String readln() throws NoSuchElementException, IllegalStateException{
        System.out.print(prompt);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public Scanner getScanner(){
        return scanner;
    }
    public void setScanner(Scanner scanner){
        ConsoleManager.scanner = scanner;
    }

    public void setFileMode(boolean b){
        fileMode = b;
    }
    public boolean getFileMode(){return fileMode;}



}
