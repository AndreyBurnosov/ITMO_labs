package abstracts;

import java.util.Objects;

public abstract class Characters {
    protected String name;
    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
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
