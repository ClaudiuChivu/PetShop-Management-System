/** Clasa pentru entities
 * @author Chivu Claudiu Dumitru
 * @version 27 decembrie 2024
 */
package com.example.demoSpringAWJ.entities;

public class Produs {

    private int idProdus;
    private String numeProdus;
    private String tipHrana;
    private double pret;
    private int stoc;
    public Produs() {
    }

    // Constructor cu parametri
    public Produs(int idProdus, String numeProdus, String tipHrana, double pret, int stoc) {
        this.idProdus = idProdus;
        this.numeProdus = numeProdus;
        this.tipHrana = tipHrana;
        this.pret = pret;
        this.stoc = stoc;
    }
    // Getters și Setters
    public int getIdProdus() {
        return idProdus;
    }

    public void setIdProdus(int idProdus) {
        this.idProdus = idProdus;
    }

    public String getNumeProdus() {
        return numeProdus;
    }

    public void setNumeProdus(String numeProdus) {
        this.numeProdus = numeProdus;
    }

    public String getTipHrana() {
        return tipHrana;
    }

    public void setTipHrana(String tipHrana) {
        this.tipHrana = tipHrana;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public int getStoc() {
        return stoc;
    }

    public void setStoc(int stoc) {
        this.stoc = stoc;
    }
}
