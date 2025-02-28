/** Clasa pentru entities Comanda
 * @author Chivu Claudiu Dumitru
 * @version 03 ianuarie 2025
 */
package com.example.demoSpringAWJ.entities;

public class Comanda {

    private int idComanda;
    private int idClient;
    private String dataComenzii;
    private double total;
    private String status;

    // Constructor
    public Comanda(int idComanda, int idClient, String dataComenzii, double total, String status) {
        this.idComanda = idComanda;
        this.idClient = idClient;
        this.dataComenzii = dataComenzii;
        this.total = total;
        this.status = status;
    }

    // Getters și Setters
    public int getIdComanda() {
        return idComanda;
    }

    public void setIdComanda(int idComanda) {
        this.idComanda = idComanda;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getDataComenzii() {
        return dataComenzii;
    }

    public void setDataComenzii(String dataComenzii) {
        this.dataComenzii = dataComenzii;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
