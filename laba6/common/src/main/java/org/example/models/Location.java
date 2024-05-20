package org.example.models;

import org.example.utility.Validatable;

import java.io.Serializable;

public class Location implements Validatable, Serializable {
    private int x;
    private float y;
    private Integer z; //Поле не может быть null

    public Location(int x, float y, Integer z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {return x;}
    public float getY() {return y;}
    public Integer getZ() {return z;}

    @Override
    public boolean validate(){
        return z != null;

    }

    @Override
    public String toString(){
        return  "\t\t\t" + "x = " + x + '\n' +
                "\t\t\t" + "y = " + y + '\n' +
                "\t\t\t" + "z = " + z + '\n' +
                "\t\t" + "}";
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Location other = (Location) obj;
        return x == other.x && y == other.y && z.equals(other.z);

    }
}