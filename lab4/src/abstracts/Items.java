package abstracts;

import java.util.Objects;

public abstract class Items {
    protected String name;
    protected String owner;
    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner){
        this.owner = owner;
    }
    @Override
    public String toString() {
        return getName();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Items item = (Items) o;
        return Objects.equals(name, item.name) && Objects.equals(owner, item.owner);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name) + Objects.hash(owner);
    }
}
