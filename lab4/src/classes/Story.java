package classes;

import enums.Food;
import enums.Gender;
import enums.Tastes;
import exceptions.WrongStoryLogicException;
import exceptions.WrongValueException;

import java.util.List;

public class Story {
    public static void storyTelling() throws WrongStoryLogicException {
        Bottle bottle = new Bottle(List.of());
        bottle.setName("бутылочка");
        bottle.pour(List.of(Food.OMELET, Food.TOFFEE, Food.PINEAPPLE, Food.CHERRY_PIE,
                Food.CROUTONS_WITH_BUTTER, Food.ROAST_TURKEY), Tastes.TASTY, "содержимое", -100);

        Human alisa = new Human();
        alisa.setName("Алиса");
        alisa.setGender(Gender.FEMALE);
        if (alisa.getGender() != "женский"){
            throw new WrongStoryLogicException("Алиса должна быть женского пола!");
        }
        alisa.setAge(15);
        alisa.addItem(bottle);
        alisa.setHeight(160);
        //Human.EventManager.celebrateBirthday(alisa);

        System.out.printf("На этой %s %s.\n", bottle.getName(), bottle.notExist());
        System.out.printf("%s %s.\n", alisa.getName(), alisa.toTry(alisa));
        System.out.printf("%s не заметила, как %s оказалась %s.\n", alisa.getName(), bottle.getName(), bottle.isFilled());
        class ScenarioEffect {
            void applyMagic(Human human) {
                System.out.printf("Под воздействием магии %s уменьшилась до %d см!\n", human.getName(), human.getHeight());
            }
        }
        new ScenarioEffect().applyMagic(alisa);

    }
}
