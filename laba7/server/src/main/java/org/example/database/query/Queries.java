package org.example.database.query;

public class Queries {
    public static final String INSERT_ORGANISATION = "INSERT INTO organisations(name, owner_id, cord_x, cord_y, creation_date, annual_turnover, employees_count, organisation_type, street, town_x, town_y, town_z, id) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String SAVE_ORGANISATION = "INSERT INTO organisations(name, owner_id, cord_x, cord_y, creation_date, annual_turnover, employees_count, organisation_type, street, town_x, town_y, town_z) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String UPDATE_ORGANISATION = "UPDATE organisations SET name = ?, cord_x = ?, cord_y = ?, creation_date = ?, annual_turnover = ?, employees_count = ?, organisation_type = ?, street = ?, town_x = ?, town_y = ?, town_z = ? "
                    + "WHERE id = ? AND owner_id = ?";

    public static final String DELETE_ORGANISATION_BY_ID_AND_USER = "DELETE FROM organisations WHERE id = ? AND owner_id = ?";

    public static final String DELETE_ALL_ORGANISATIONS_BY_USER = "DELETE FROM organisations WHERE owner_id = ?";

    public static final String FIND_ALL_ORGANISATIONS_BY_USER = "SELECT * FROM organisations WHERE owner_id = ?";

    public static final String FIND_ALL_ORGANISATIONS = "SELECT * FROM organisations";

    public static final String FIND_ORGANISATION_BY_ID = "SELECT * FROM organisations WHERE id = ?";

    public static final String SAVE_USER = "INSERT INTO users (username, password) VALUES (?, ?)";
    public static final String EXISTS_BY_USERNAME = "SELECT COUNT(*) FROM users WHERE username = ?";
    public static final String FIND_BY_ID = "SELECT * FROM users WHERE id = ?";
    public static final String FIND_BY_USERNAME = "SELECT * FROM users WHERE username = ?";
}