package enums;

public enum Tastes {
    SWEET("сладкий"),
    SALTY("соленый"),
    BITTER("горький"),
    SPICY("острый"),
    TASTY("вкусный");
    private String name;
    Tastes (String name){
        this.name = name;
    }
    public static String getName(Tastes taste){
        return taste.name;
    }
}
