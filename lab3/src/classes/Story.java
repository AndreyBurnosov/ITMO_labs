package classes;

import enums.Food;
import enums.Gender;
import enums.Tastes;

import java.util.List;

public class Story {
    public static void storyTelling(){
        Bottle bottle = new Bottle(List.of());
        bottle.setName("бутылочка");
        bottle.pour(List.of(Food.OMELET, Food.TOFFEE, Food.PINEAPPLE, Food.CHERRY_PIE,
                Food.CROUTONS_WITH_BUTTER, Food.ROAST_TURKEY), Tastes.TASTY, "содержимое");

        Human alisa = new Human();
        alisa.setName("Алиса");
        alisa.setGender(Gender.FEMALE);
        alisa.setAge(15);
        alisa.addItem(bottle);

        System.out.printf("На этой %s %s.\n", bottle.getName(), bottle.notExist());
        System.out.printf("%s %s.\n", alisa.getName(), alisa.toTry());
        System.out.printf("%s не заметила, как %s оказалась %s.\n", alisa.getName(), bottle.getName(), bottle.isFilled());
    }
}
