package org.example.models;

import org.example.utility.Validatable;

public class Coordinates implements Validatable {

    private int x; //Значение поля должно быть больше -304
    private Double y; //Значение поля должно быть больше -247, Поле не может быть null

    public Coordinates(int x, Double y){
        this.x = x;
        this.y = y;
    }

    public int getX() {return x;}
    public Double getY() {return y;}

    @Override
    public String toString(){
        return  "\t\t" + "x = " + x + '\n' +
                "\t\t" + "y = " + y + '\n' +
                '\t' + "}";
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Coordinates other = (Coordinates) obj;
        return x == other.x && y.equals(other.y);

    }
    @Override
    public boolean validate(){
        return x > -304 && y > -247 && y != null;
    }
}
