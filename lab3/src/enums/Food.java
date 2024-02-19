package enums;
//вишневого пирога, омлета, ананаса,
// жареной индюшки, тянучки и горячих гренков с маслом
public enum Food {
    CHERRY_PIE("вишнёвый пирог"),
    OMELET("омлет"),
    PINEAPPLE("ананас"),
    ROAST_TURKEY("жареная индюшка"),
    TOFFEE("тянучка"),
    CROUTONS_WITH_BUTTER("гренки с маслом");

    private String name;
    Food (String name){
        this.name = name;
    }
    public static String getName(Food food){
        return food.name;
    }
}
