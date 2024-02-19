package enums;

public enum Symbols {
    SKULL("Череп"),
    BONES("кости"),
    POISON("яд");
    private String name;
    Symbols (String name){
        this.name = name;
    }
    public static String getName(Symbols symbol){
        return symbol.name;
    }
}
