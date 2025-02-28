/** Clasa pentru Repository
 * @author Chivu Claudiu Dumitru
 * @version 27 decembrie 2024
 */
package com.example.demoSpringAWJ.repositories;

import com.example.demoSpringAWJ.entities.Produs;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProdusRepository {

    private final DatabaseConnection databaseConnection;

    public ProdusRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
        databaseConnection.initializeDatabase(); // Crează tabelul dacă nu există
    }

    // Metodă pentru a obține toate produsele din baza de date
    public List<Produs> getAllProduse() {
        List<Produs> produse = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection connection = databaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                produse.add(new Produs(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("category"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("purchased")  // Dacă folosești boolean, transformă: resultSet.getBoolean("purchased")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produse;
    }

    // Metodă pentru a adăuga un produs
    public void adaugaProdus(Produs produs) {
        String sql = "INSERT INTO products (name, category, price, purchased) VALUES (?, ?, ?, ?)";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, produs.getNumeProdus());
            statement.setString(2, produs.getTipHrana());
            statement.setDouble(3, produs.getPret());
            statement.setInt(4, produs.getStoc());  // Ajustează conform tipului de date
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodă pentru a șterge un produs după ID
    public void deleteProdus(int id) {
        String sql = "DELETE FROM products WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodă pentru a găsi un produs după ID
    public Produs getProdusDupaId(int id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Produs(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("category"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("purchased")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Metodă pentru a actualiza un produs
    public void updateProdus(Produs produs) {
        String sql = "UPDATE products SET name = ?, category = ?, price = ?, purchased = ? WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, produs.getNumeProdus());
            statement.setString(2, produs.getTipHrana());
            statement.setDouble(3, produs.getPret());
            statement.setInt(4, produs.getStoc());
            statement.setInt(5, produs.getIdProdus());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
