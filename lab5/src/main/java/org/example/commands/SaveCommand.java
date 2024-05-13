package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.managers.ConsoleManager;
import org.example.managers.DumpManager;

public class SaveCommand extends Command{

    private DumpManager dumpManager;
    private CollectionManager collectionManager;

    public SaveCommand(DumpManager dumpManager, CollectionManager collectionManager) {
        super("save", "сохранить коллекцию в файл");
        this.dumpManager = dumpManager;
        this.collectionManager = collectionManager;
    }

    public boolean execute(String argument){
        return collectionManager.saveCollection(dumpManager);
    }

}
