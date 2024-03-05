package enums;

public enum Symbols {
    SKULL("череп"),
    BONES("кости"),
    POISON("\"Яд!\"");
    private String name;
    Symbols (String name){
        this.name = name;
    }
    public static String getName(Symbols symbol){
        return symbol.name;
    }
}
