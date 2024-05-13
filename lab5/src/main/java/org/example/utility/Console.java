package org.example.utility;

import java.util.Scanner;

/**
 * Консоль для ввода команд и вывода результата
 */
public interface Console {

    void print(Object object);
    void println(Object object);
    void printError(Object object);
    String readln();



}
