package enums;

public enum Mood {
    CHEERFUL("весёлый"),
    SAD("грустный"),
    ANGRY("злой"),
    UPSET("расстороенный"),
    WARY("настороженный"),
    CALM("спокойный");
    private String name;
    Mood (String name){
        this.name = name;
    }
    public static String getName(Mood mood){
        return mood.name;
    }
}
