package org.example.models;

import org.example.utility.Element;
import org.example.utility.Validatable;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import static java.lang.Math.max;

public class Organisation extends Element implements Validatable, Serializable {
    private Integer id;
    private Long ownerId;
    private String name;

    private Coordinates coordinates; // not exists in database
    transient private int cord_x;
    transient private Double cord_y;

    private Date creationDate;
    private double annualTurnover;
    private long employeesCount;
    private OrganizationType type;

    private Address postalAddress; // not exists in database
    transient private String street;
    transient private int town_x;
    transient private float town_y;
    transient private Integer town_z;

    public Organisation(String name, Coordinates coordinates, double annualTurnover, long employeesCount, OrganizationType type, Address postalAddress) {
        this.name = name;
        this.coordinates = coordinates;
        this.cord_x = coordinates.getX();
        this.cord_y = coordinates.getY();
        this.creationDate = new Date(); // Установка текущей даты и времени создания
        this.annualTurnover = annualTurnover;
        this.employeesCount = employeesCount;
        this.type = type;
        this.postalAddress = postalAddress;
        this.street = postalAddress.getStreet();
        this.town_x = postalAddress.getTown().getX();
        this.town_y = postalAddress.getTown().getY();
        this.town_z = postalAddress.getTown().getZ();
    }

    public void initSub() {
        this.street = postalAddress.getStreet();
        this.town_x = postalAddress.getTown().getX();
        this.town_y = postalAddress.getTown().getY();
        this.town_z = postalAddress.getTown().getZ();
        this.cord_x = coordinates.getX();
        this.cord_y = coordinates.getY();
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public int getCord_x() {
        return cord_x;
    }

    public Double getCord_y() {
        return cord_y;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setAnnualTurnover(double annualTurnover) {
        this.annualTurnover = annualTurnover;
    }

    public void setEmployeesCount(long employeesCount) {
        this.employeesCount = employeesCount;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }

    public void setPostalAddress(Address postalAddress) {
        this.postalAddress = postalAddress;
    }

    public String getStreet() {
        return street;
    }

    public int getTown_x() {
        return town_x;
    }

    public float getTown_y() {
        return town_y;
    }

    public Integer getTown_z() {
        return town_z;
    }

    // Геттеры и сеттеры для полей класса


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public double getAnnualTurnover() {
        return annualTurnover;
    }

    public long getEmployeesCount() {
        return employeesCount;
    }

    public OrganizationType getType() {
        return type;
    }

    public Address getPostalAddress() {
        return postalAddress;
    }

    public static String[] toCsvArray(Organisation e) {
        ArrayList<String> res = new ArrayList<>();
        res.add(String.valueOf(e.getId()));
        res.add(e.getName());
        res.add(String.valueOf(e.getCoordinates().getX()));
        res.add(String.valueOf(e.getCoordinates().getY()));
        res.add(String.valueOf(e.getCreationDate().toInstant().toEpochMilli()));
        res.add(String.valueOf(e.getAnnualTurnover()));
        res.add(String.valueOf(e.getEmployeesCount()));
        res.add(e.getType() == null ? "" : e.getType().name());
        res.add(e.getPostalAddress().getStreet());
        res.add(String.valueOf(e.getPostalAddress().getTown().getX()));
        res.add(String.valueOf(e.getPostalAddress().getTown().getY()));
        res.add(String.valueOf(e.getPostalAddress().getTown().getZ()));
        return res.toArray(new String[0]);
    }

    public static Organisation fromCsvArray(String[] a) {
        Integer id;
        String name;
        Coordinates coordinates;
        ZonedDateTime creationDate;
        double annualTurnover;
        long employeesCount;
        OrganizationType type;
        Address postalAddress;
        try {
            try {
                id = Integer.valueOf(a[0]);
            } catch (NumberFormatException e) {
                id = null;
            }
            name = a[1];
            try {
                int x = a[2].equals("null") ? null : Integer.parseInt(a[2]);
                Double y = Double.parseDouble(a[3]);
                coordinates = new Coordinates(x, y);
            } catch (NumberFormatException e) {
                coordinates = null;
            }
            try {
                creationDate = ZonedDateTime.parse(a[4], DateTimeFormatter.ISO_ZONED_DATE_TIME);
            } catch (DateTimeParseException e) {
                creationDate = null;
            }
            ;
            try {
                annualTurnover = Double.parseDouble(a[5]);
            } catch (NumberFormatException e) {
                annualTurnover = 0D;
            }
            try {
                employeesCount = Long.parseLong(a[6]);
            } catch (NumberFormatException e) {
                employeesCount = 0L;
            }
            try {
                type = OrganizationType.valueOf(a[7]);
            } catch (NullPointerException | IllegalArgumentException e) {
                type = null;
            }
            try {
                String street = String.valueOf(a[8]);
                int x = a[9].equals("null") ? null : Integer.parseInt(a[9]);
                float y = a[10].equals("null") ? null : Float.parseFloat(a[10]);
                Integer z = Integer.parseInt(a[11]);
                postalAddress = new Address(street, new Location(x, y, z));
            } catch (NumberFormatException e) {
                postalAddress = null;
            }

            return new Organisation(name, coordinates, annualTurnover, employeesCount, type, postalAddress);
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        return null;
    }

    public String toString() {
        return "Organization{" + '\n' +
                '\t' + "id = " + id + '\n' +
                '\t' + "name = " + name + '\n' +
                '\t' + "coordinates = " + "{\n" + coordinates.toString() + '\n' +
                '\t' + "creationDate = " + creationDate + '\n' +
                '\t' + "annualTurnover = " + annualTurnover + '\n' +
                '\t' + "employeesCount = " + employeesCount + '\n' +
                '\t' + "type = " + (type == null ? "null" : type) + '\n' +
                '\t' + "postalAddress = " + "{\n" + (postalAddress == null ? "null" : postalAddress.toString()) + '\n' +
                '}';
    }

    public boolean validate() {

        return id != null && id > 0 // Проверяем, что id не null и больше 0
                && name != null && !name.isEmpty() // Проверяем, что имя не null и не пустое
                && coordinates != null // Проверяем, что координаты не null
                && creationDate != null // Проверяем, что дата создания не null
                && annualTurnover > 0 // Проверяем, что годовой оборот больше 0
                && employeesCount > 0;
        // Поля type и postalAddress могут быть null, поэтому их не проверяем
    }

    @Override
    public int compareTo(Element other) {
        // Это базовая реализация, сравнивающая только id.
        // Можно расширить логику сравнения, если нужно учитывать другие поля.
        return (id - other.getId());
    }

    @Override
    public int hashCode() {
        // hashCode должен использовать те же поля, что и equals, для корректной работы в коллекциях
        return Objects.hash(id, name, coordinates, creationDate, annualTurnover, employeesCount, type, postalAddress);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Organisation other = (Organisation) obj;
        return Objects.equals(id, other.id) &&
                Objects.equals(name, other.name) &&
                Objects.equals(coordinates, other.coordinates) &&
                Objects.equals(creationDate, other.creationDate) &&
                Double.compare(annualTurnover, other.annualTurnover) == 0 &&
                employeesCount == other.employeesCount &&
                type == other.type &&
                Objects.equals(postalAddress, other.postalAddress);
    }
}
