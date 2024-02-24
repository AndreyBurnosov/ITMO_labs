package classes;

import abstracts.Characters;
import abstracts.Items;
import enums.Gender;
import enums.Mood;
import enums.Tastes;
import interfaces.HumanAction;

import java.util.ArrayList;
import java.util.List;

public class Human extends Characters implements HumanAction {
    private List<Items> storage = new ArrayList<>();;
    private Mood mood = Mood.CALM;
    private Gender gender;
    private int age = -1;
    public String toTry() {
        Bottle bottle = (Bottle) storage.get(0);
        String try_ = gender == Gender.FEMALE ? "попробовала ": "попробовал ";

        if (bottle.contentTaste() == Tastes.TASTY) {
            mood = Mood.CHEERFUL;
        }

        return try_ + bottle.getContentName() + ", " + bottle.drink();
    }
    public List<Items> getStorage() {
        return storage;
    }

    public void addItem(Items item){
        item.setOwner(this.getName());
        this.storage.add(item);
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
    public void setAge(int age){
        // надо сделать выброс ошибки если возраст < 0
        this.age = age;
    }
    public int getAge(){
        return age;
    }
}
