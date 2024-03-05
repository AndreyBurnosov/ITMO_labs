package classes;

import abstracts.Characters;
import abstracts.Items;
import enums.Gender;
import enums.Mood;
import enums.Tastes;
import exceptions.WrongValueException;
import interfaces.HumanAction;

import java.util.ArrayList;
import java.util.List;

public class Human extends Characters implements HumanAction {
    private List<Items> storage = new ArrayList<>();;
    private Mood mood = Mood.CALM;
    private Gender gender;
    private int height;
    private int age = -1;
    public String toTry(Human human) {
        Bottle bottle = (Bottle) storage.get(0);
        String try_ = gender == Gender.FEMALE ? "попробовала ": "попробовал ";

        if (bottle.contentTaste() == Tastes.TASTY) {
            mood = Mood.CHEERFUL;
        }

        return try_ + bottle.getContentName() + ", " + bottle.drink(human);
    }

    public static class EventManager {
        public static void celebrateBirthday(Human human) {
            int newAge = human.getAge() + 1;
            human.setAge(newAge);
            System.out.println("С днем рождения, " + human.getName() + "! Теперь вам " + newAge + " лет.");
        }

        public static void changeHeight(Human human, int newHeight) {
            human.setHeight(newHeight);
            System.out.println(human.getName() + " теперь имеет рост " + newHeight + " см.");
        }
    }
    public List<Items> getStorage() {
        return storage;
    }

    public void addItem(Items item){

        new Object() {
            void notifyAddition() {
                if (item != null) {
                    System.out.println(Human.this.getName() + (Human.this.getGender() == "женский" ? " добавилa ": " добавил ") + item.getName() + " в хранилище.");
                } else {
                    System.out.println("Попытка добавить null предмет в хранилище.");
                }
            }
        }.notifyAddition();

        if (item != null) {
            item.setOwner(this.getName());
            this.storage.add(item);
        }
    }

    public void clearStorage(){
        for (var st: storage) {
            st.setOwner("ничей");
        }
        storage.clear();
    }

    public void removeItem(Items item){
        item.setOwner("ничей");
        storage.remove(item);
    }

    public void setGender(Gender gender){
        this.gender = gender;
    }
    public String getGender(){
        if (this.gender == null) {
            return "Неизвестный";
        }
        return(Gender.getName(this.gender));
    }
    public void setAge(int age) throws WrongValueException {
        // надо сделать выброс ошибки если возраст < 0
        this.age = age;
        if (this.age < 0) {
            throw new WrongValueException("Возраст не может быть отрицательным");
        }
    }
    public int getHeight(){
        return height;
    }

    public void setHeight(int height) throws WrongValueException {
        // надо сделать выброс ошибки если возраст < 0
        this.height = height;
        if (this.height < 0) {
            throw new WrongValueException("Рост не может быть отрицательным");
        }
    }
    public int getAge(){
        return age;
    }
}
