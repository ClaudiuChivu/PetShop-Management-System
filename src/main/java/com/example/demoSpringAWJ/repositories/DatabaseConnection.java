/** Clasa pentru Conexiunea bazei de date
 * @author Chivu Claudiu Dumitru
 * @version 02 decembrie 2024
 */
package com.example.demoSpringAWJ.repositories;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class DatabaseConnection {

    private final String url = "jdbc:h2:file:./data/demoSpringAWJ";
    private final String user = "sa";
    private final String password = "password";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void initializeDatabase() {
        // Creare tabelă 'products'
        String createProductsTableSQL = "CREATE TABLE IF NOT EXISTS products (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL, " +
                "category VARCHAR(50) NOT NULL, " +
                "price DECIMAL(10, 2) NOT NULL, " +
                "purchased INT DEFAULT 1)";

        // Creare tabelă 'comenzi'
        String createComenziTableSQL = "CREATE TABLE IF NOT EXISTS comenzi (" +
                "id_comanda INT AUTO_INCREMENT PRIMARY KEY, " +
                "data_comenzii DATE NOT NULL, " +
                "total DECIMAL(10, 2) NOT NULL, " +
                "id_client INT NOT NULL, " +
                "status VARCHAR(50) NOT NULL)";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            // Execută comanda pentru a crea tabelele
            statement.execute(createProductsTableSQL);
            statement.execute(createComenziTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
