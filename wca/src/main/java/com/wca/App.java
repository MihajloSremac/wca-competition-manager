package com.wca;

import com.wca.config.DatabaseConnection;
import com.wca.uihandler.TakmicenjeUIHandler;

public class App {
    public static void main(String[] args) {
        // Test konekcije na bazu
        try {
            System.out.println("Testiranje konekcije sa bazom podataka...");
            java.sql.Connection conn = DatabaseConnection.getConnection();
            if (conn != null) {
                System.out.println("Povezivanje sa bazom podataka uspesno!");
                 System.out.println("=== WCA User Management System ===");
                conn.close();
            } else {
                System.out.println("Neuspesno povezivanje sa bazom podataka!");
                return;
            }
        } catch (Exception e) {
            System.err.println("Greska pri povezivanju sa bazom podataka: " + e.getMessage());
            return;
        }

        TakmicenjeUIHandler handler = new TakmicenjeUIHandler();
        handler.start();
    }
}
