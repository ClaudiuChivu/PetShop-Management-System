/** Clasa pentru Repository Comanda
 * @author Chivu Claudiu Dumitru
 * @version 03 ianuarie 2025
 */
package com.example.demoSpringAWJ.repositories;

import com.example.demoSpringAWJ.entities.Comanda;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ComandaRepository {

    private final DatabaseConnection databaseConnection;

    public ComandaRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    // Obține toate comenzile
    public List<Comanda> getAllComenzi() {
        List<Comanda> comenzi = new ArrayList<>();
        String sql = "SELECT * FROM comenzi";  // Asigură-te că acest SQL corespunde structurii bazei tale de date

        try (Connection connection = databaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Comanda comanda = new Comanda(
                        resultSet.getInt("id_comanda"),
                        resultSet.getInt("id_client"),
                        resultSet.getString("data_comenzii"),
                        resultSet.getDouble("total"),
                        resultSet.getString("status")
                );
                comenzi.add(comanda);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comenzi;
    }

    // Obține comenzile filtrate după status
    public List<Comanda> getComenziDupaStatus(String status) {
        List<Comanda> comenzi = new ArrayList<>();
        String sql = "SELECT * FROM comenzi WHERE status = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, status);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Comanda comanda = new Comanda(
                            resultSet.getInt("id_comanda"),
                            resultSet.getInt("id_client"),
                            resultSet.getString("data_comenzii"),
                            resultSet.getDouble("total"),
                            resultSet.getString("status")
                    );
                    comenzi.add(comanda);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comenzi;
    }

    // Obține comenzile după dată
    public List<Comanda> getComenziDupaData(String dataComenzii) {
        List<Comanda> comenzi = new ArrayList<>();
        String sql = "SELECT * FROM comenzi WHERE data_comenzii = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, dataComenzii);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Comanda comanda = new Comanda(
                            resultSet.getInt("id_comanda"),
                            resultSet.getInt("id_client"),
                            resultSet.getString("data_comenzii"),
                            resultSet.getDouble("total"),
                            resultSet.getString("status")
                    );
                    comenzi.add(comanda);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comenzi;
    }

    // Obține comenzile după id-ul clientului
    public List<Comanda> getComenziDupaClient(int idClient) {
        List<Comanda> comenzi = new ArrayList<>();
        String sql = "SELECT * FROM comenzi WHERE id_client = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idClient);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Comanda comanda = new Comanda(
                            resultSet.getInt("id_comanda"),
                            resultSet.getInt("id_client"),
                            resultSet.getString("data_comenzii"),
                            resultSet.getDouble("total"),
                            resultSet.getString("status")
                    );
                    comenzi.add(comanda);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comenzi;
    }

    // Actualizează statusul comenzii
    public void updateComandaStatus(int idComanda, String status) {
        String sql = "UPDATE comenzi SET status = ? WHERE id_comanda = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, status);
            statement.setInt(2, idComanda);
            int rowsUpdated = statement.executeUpdate(); // Verifică câte rânduri au fost actualizate
            System.out.println("Rows updated: " + rowsUpdated);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Obține o comandă după ID
    public Comanda getComandaDupaId(int idComanda) {
        String sql = "SELECT * FROM comenzi WHERE id_comanda = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idComanda);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Comanda(
                            resultSet.getInt("id_comanda"),
                            resultSet.getInt("id_client"),
                            resultSet.getString("data_comenzii"),
                            resultSet.getDouble("total"),
                            resultSet.getString("status")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Comanda> getComenziInPerioada(String startDate, String endDate) {
        List<Comanda> comenzi = new ArrayList<>();
        String sql = "SELECT * FROM comenzi WHERE data_comenzii BETWEEN ? AND ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, startDate);
            statement.setString(2, endDate);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Comanda comanda = new Comanda(
                            resultSet.getInt("id_comanda"),
                            resultSet.getInt("id_client"),
                            resultSet.getString("data_comenzii"), // Data păstrată ca String
                            resultSet.getDouble("total"),
                            resultSet.getString("status")
                    );
                    comenzi.add(comanda);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comenzi;
    }
}


