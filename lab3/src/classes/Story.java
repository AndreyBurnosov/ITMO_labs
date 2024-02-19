package classes;

import enums.Food;
import enums.Symbols;

public class Story {
    public static void storyTelling(){
        Alisa alisa = new Alisa();
        alisa.setName("Алиса");
        Bottle bottle = new Bottle();
        bottle.setName("бутылочка");
        Bottle.Content content = new Bottle.Content();
        content.setName("содержимое");
        System.out.printf("На этой %s %s %s, %s, надписи %s.\n", bottle.getName(), bottle.notExist(),
                Symbols.getName(Symbols.SKULL), Symbols.getName(Symbols.BONES), Symbols.getName(Symbols.POISON));
        System.out.printf("%s %s её %s.\n", alisa.getName(), alisa.toTry(), content.getName());
        System.out.printf("%s %s, %s смесь из %s, %s, %s, %s, %s и %s.\n", content.getName(), content.beTaste(), content.beSame(),
                Food.getName(Food.OMELET), Food.getName(Food.TOFFEE), Food.getName(Food.CHERRY_PIE), Food.getName(Food.PINEAPPLE),
                Food.getName(Food.ROAST_TURKEY), Food.getName(Food.CROUTONS_WITH_BUTTER));
        System.out.printf("%s %s, как %s %s.\n", alisa.getName(), alisa.notNotice(), bottle.getName(), bottle.becomeEmpty());
    }
}
