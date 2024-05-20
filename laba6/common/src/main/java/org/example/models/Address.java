package org.example.models;

import org.example.utility.Validatable;

import java.io.Serializable;

public class Address implements Validatable, Serializable {
    private String street; //Поле может быть null
    private Location town; //Поле не может быть null

    public String getStreet() {return street;}
    public Location getTown() {return town;}

    public Address(String street, Location town){
        this.street = street;
        this.town = town;
    }

    @Override
    public String toString(){
        return  "\t\t" + "street = " + (street == "" ? "null" : street) + '\n' +
                "\t\t" + "town = " + "{\n" + town.toString() + '\n' +
                '\t' + "}";
    }

    @Override
    public boolean validate(){
        return town != null;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Address other = (Address) obj;
        return street.equals(other.street) && town.equals(other.town);

    }
}
