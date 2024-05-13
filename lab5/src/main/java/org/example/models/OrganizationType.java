package org.example.models;

public enum OrganizationType {
    COMMERCIAL,
    GOVERNMENT,
    PRIVATE_LIMITED_COMPANY,
    OPEN_JOINT_STOCK_COMPANY;

    public static String names(){
        StringBuilder nameList = new StringBuilder();
        for (var organizationType: values()){
            nameList.append(organizationType.name()).append(", ");
        }
        return (nameList).substring(0, nameList.length()-2);
    }
}
