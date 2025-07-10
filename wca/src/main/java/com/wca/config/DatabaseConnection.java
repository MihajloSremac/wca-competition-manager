package com.wca.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class DatabaseConnection {
    private static final String PROPERTIES_FILE = "/database.properties";
    
    public static Connection getConnection() throws SQLException {
        try {
            Properties props = new Properties();
            InputStream input = DatabaseConnection.class.getResourceAsStream(PROPERTIES_FILE);
            if (input == null) {
                throw new SQLException("Properties file not found: " + PROPERTIES_FILE);
            }
            props.load(input);
            
            String url = props.getProperty("db.url");
            String username = props.getProperty("db.username");
            String password = props.getProperty("db.password");
            
            return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            throw new SQLException("Greška pri povezivanju sa bazom: " + e.getMessage(), e);
        }
    }
}
