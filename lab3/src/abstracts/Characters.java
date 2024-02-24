package abstracts;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Characters {
    private String name;
    private int age;
    private List<Items> storage;
    public void addItem(Items item) {
        this.storage.add(item);
    }

    public void clearStorage() {
        storage.clear();
    }

    public void removeItem(Items item) {
        storage.remove(item);
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        if (this.name == null) {
            return "Аноним";
        }
        return(this.name);
    }
    @Override
    public String toString() {
        return getName();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Characters characters = (Characters) o;
        return Objects.equals(name, characters.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
