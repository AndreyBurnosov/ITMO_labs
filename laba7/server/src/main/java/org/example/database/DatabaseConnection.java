package org.example.database;

import org.example.database.configuration.DatabaseConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private final Connection connection;

    private DatabaseConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(
                    DatabaseConfiguration.POSTGRES_URL,
                    DatabaseConfiguration.POSTGRES_USER,
                    DatabaseConfiguration.POSTGRES_PASSWORD
            );
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException("Error connecting to the database", e);
        }
    }

    public PreparedStatement prepareStatement(String query) {
        try {
            return connection.prepareStatement(query);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public static DatabaseConnection getInstance() throws SQLException {
        return (instance == null || instance.getConnection().isClosed()) ? instance = new DatabaseConnection() : instance;
    }

    public Connection getConnection() {
        return connection;
    }
}