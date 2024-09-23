package org.example.database.configuration;

import org.slf4j.LoggerFactory;

public class DatabaseConfiguration {
    public static final String POSTGRES_URL = "jdbc:postgresql://%s:%s/%s".formatted(System.getenv("DB_HOST"), System.getenv("DB_PORT"), System.getenv("DB_NAME"));
    public static final String POSTGRES_USER = System.getenv("DB_USER");
    public static final String POSTGRES_PASSWORD = System.getenv("DB_PASSWORD");

    static {
        LoggerFactory.getLogger(DatabaseConfiguration.class).info("Database configuration loaded: {} [{},{}]", POSTGRES_URL, POSTGRES_USER, "*".repeat(POSTGRES_PASSWORD.length()));
    }
}
