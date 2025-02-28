/** Clasa pentru operatii pe baza de date
 * @author Chivu Claudiu Dumitru
 * @version 27 decembrie 2024
 */
package com.example.demoSpringAWJ.services;

import com.example.demoSpringAWJ.repositories.DatabaseConnection;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class DatabaseService {

    private final DatabaseConnection databaseConnection;

    public DatabaseService(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
        // Inițializează baza de date (creează tabelele, dacă nu există)
        this.databaseConnection.initializeDatabase();
        // Adaugă comenzile fictive
        addFictiveOrders();
    }

    public void addFictiveOrders() {
        String insertSQL = "INSERT INTO comenzi (data_comenzii, total, id_client, status) VALUES (?, ?, ?, ?)";

        // Crearea unor comenzi fictive
       List<Object[]> orders = new ArrayList<>();
       /*
        orders.add(new Object[]{Date.valueOf("2024-12-01"), 150.75, 1, "In procesare"});
        orders.add(new Object[]{Date.valueOf("2024-12-02"), 200.50, 2, "In procesare"});
        orders.add(new Object[]{Date.valueOf("2024-12-03"), 120.00, 3, "Livrata"});
        orders.add(new Object[]{Date.valueOf("2024-12-04"), 75.25, 4, "Anulata"});
        orders.add(new Object[]{Date.valueOf("2024-12-05"), 180.90, 5, "In procesare"});
        orders.add(new Object[]{Date.valueOf("2024-12-06"), 220.30, 6, "Expediata"});
        orders.add(new Object[]{Date.valueOf("2024-12-07"), 135.10, 7, "Livrata"});
        orders.add(new Object[]{Date.valueOf("2024-12-08"), 250.00, 8, "Anulata"});
        orders.add(new Object[]{Date.valueOf("2024-12-09"), 100.45, 9, "In procesare"});
        orders.add(new Object[]{Date.valueOf("2024-12-10"), 190.60, 10, "Expediata"});
*/
        try (Connection connection = databaseConnection.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
                for (Object[] order : orders) {
                    preparedStatement.setDate(1, (Date) order[0]);
                    preparedStatement.setDouble(2, (Double) order[1]);
                    preparedStatement.setInt(3, (Integer) order[2]);
                    preparedStatement.setString(4, (String) order[3]);
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
