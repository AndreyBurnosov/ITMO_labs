package enums;

public enum Gender {
    MALE("мужской"),
    FEMALE("женский"),
    COMBAT_HELICOPTER("боевой вертолет");
    private String name;
    Gender (String name){
        this.name = name;
    }
    public static String getName(Gender gender){
        return gender.name;
    }
}
