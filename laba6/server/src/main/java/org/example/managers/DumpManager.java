package org.example.managers;

import com.opencsv.*;
import org.example.models.Organisation;
import org.example.utility.ConsoleManager;

import java.io.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.PriorityQueue;


public class DumpManager {
    private final String fileName;
    private final ConsoleManager console;

    public DumpManager(String fileName, ConsoleManager console) {
        this.fileName = fileName;
        this.console = console;
    }

    private String collection2CSV(Collection<Organisation> collection) {
        try {
            StringWriter sw = new StringWriter();
            CSVWriter csvWriter = new CSVWriter(sw, ';', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
            for (var e : collection) {
                csvWriter.writeNext(Organisation.toCsvArray(e));
            }
            String csv = sw.toString();
            return csv;
        } catch (Exception e) {
            console.printError("Ошибка сериализации");
            return null;
        }
    }

    /**
     * Записывает коллекцию в файл.
     * @param collection коллекция
     */
    public boolean writeCollection(Collection<Organisation> collection) {
        OutputStreamWriter writer = null;
        try {
            var csv = collection2CSV(collection);
            console.println(csv);
            if (csv == null) return false;
            writer = new OutputStreamWriter(new FileOutputStream(fileName));
            try {
                writer.write(csv);
                writer.flush();
                console.println("Коллекция успешна сохранена в файл!");
                return true;
            } catch (IOException e) {
                console.printError("Неожиданная ошибка сохранения");
            }
        } catch (FileNotFoundException | NullPointerException e) {
            console.printError("Файл не найден");
        } finally {
            try {
                writer.close();
            } catch(IOException e) {
                console.printError("Ошибка закрытия файла");
            }
        }
        return false;
    }

    /**
     * Преобразует CSV-строку в коллекцию.
     * @param CSV-строка
     * @return коллекция
     */
    private Collection<Organisation> CSV2collection(String s) {
        try {
            StringReader sr = new StringReader(s);
            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
            CSVReader csvReader = new CSVReaderBuilder(sr).withCSVParser(parser).build();
            LinkedList<Organisation> ds = new LinkedList<Organisation>();
            String[] record = null;
            while ((record = csvReader.readNext()) != null) {
                Organisation organisation = Organisation.fromCsvArray(record);
                if (organisation.validate())
                    ds.add(organisation);
                else
                    console.printError("Файл с колекцией содержит недействительные данные");
            }
            csvReader.close();
            return ds;
        } catch (Exception e) {
            console.printError("Ошибка десериализации");
            return null;
        }
    }

    /**
     * Считывает коллекцию из файл.
     * @return Считанная коллекция
     */
    public Collection<Organisation> readCollection() {
        Collection<Organisation> collection = new PriorityQueue<>();
        if (fileName != null && !fileName.isEmpty()) {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String nextLine;
                var s = new StringBuilder();
                while ((nextLine = br.readLine()) != null) {
                    s.append(nextLine);
                    s.append("\n");
                }
                for (var e: CSV2collection(s.toString())) collection.add(e);
            } catch (FileNotFoundException exception) {
                console.printError("Загружаемый файл не найден!");
            } catch (IOException exception) {
                console.printError("Ошибка чтения файла!");
            } catch (IllegalStateException exception) {
                console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        } else {
            console.printError("Аргумент командной строки с загрузочным файлом не найден!");
        }
        return collection;
    }
}