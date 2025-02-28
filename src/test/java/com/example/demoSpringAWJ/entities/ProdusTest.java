package com.example.demoSpringAWJ.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProdusTest {

    @Test
    void getIdProdus() {
        Produs produs = new Produs(1, "Hrana Caini", "Uscata", 50.0, 100);
        assertEquals(1, produs.getIdProdus());
    }

    @Test
    void setIdProdus() {
        Produs produs = new Produs();
        produs.setIdProdus(5);
        assertEquals(5, produs.getIdProdus());
    }

    @Test
    void getNumeProdus() {
        Produs produs = new Produs(1, "Hrana Caini", "Uscata", 50.0, 100);
        assertEquals("Hrana Caini", produs.getNumeProdus());
    }

    @Test
    void setNumeProdus() {
        Produs produs = new Produs();
        produs.setNumeProdus("Hrana Pisici");
        assertEquals("Hrana Pisici", produs.getNumeProdus());
    }

    @Test
    void getTipHrana() {
        Produs produs = new Produs(1, "Hrana Caini", "Uscata", 50.0, 100);
        assertEquals("Uscata", produs.getTipHrana());
    }

    @Test
    void setTipHrana() {
        Produs produs = new Produs();
        produs.setTipHrana("Conserva");
        assertEquals("Conserva", produs.getTipHrana());
    }

    @Test
    void getPret() {
        Produs produs = new Produs(1, "Hrana Caini", "Uscata", 50.0, 100);
        assertEquals(50.0, produs.getPret());
    }

    @Test
    void setPret() {
        Produs produs = new Produs();
        produs.setPret(75.5);
        assertEquals(75.5, produs.getPret());
    }

    @Test
    void getStoc() {
        Produs produs = new Produs(1, "Hrana Caini", "Uscata", 50.0, 100);
        assertEquals(100, produs.getStoc());
    }

    @Test
    void setStoc() {
        Produs produs = new Produs();
        produs.setStoc(250);
        assertEquals(250, produs.getStoc());
    }
}
