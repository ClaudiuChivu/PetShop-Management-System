package com.example.demoSpringAWJ.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ComandaTest {

    @Test
    void getIdComanda() {
        Comanda comanda = new Comanda(1, 2, "2023-12-01", 100.0, "Livrata");
        assertEquals(1, comanda.getIdComanda());
    }

    @Test
    void setIdComanda() {
        Comanda comanda = new Comanda(1, 2, "2023-12-01", 100.0, "Livrata");
        comanda.setIdComanda(5);
        assertEquals(5, comanda.getIdComanda());
    }

    @Test
    void getIdClient() {
        Comanda comanda = new Comanda(1, 2, "2023-12-01", 100.0, "Livrata");
        assertEquals(2, comanda.getIdClient());
    }

    @Test
    void setIdClient() {
        Comanda comanda = new Comanda(1, 2, "2023-12-01", 100.0, "Livrata");
        comanda.setIdClient(10);
        assertEquals(10, comanda.getIdClient());
    }

    @Test
    void getDataComenzii() {
        Comanda comanda = new Comanda(1, 2, "2023-12-01", 100.0, "Livrata");
        assertEquals("2023-12-01", comanda.getDataComenzii());
    }

    @Test
    void setDataComenzii() {
        Comanda comanda = new Comanda(1, 2, "2023-12-01", 100.0, "Livrata");
        comanda.setDataComenzii("2024-01-01");
        assertEquals("2024-01-01", comanda.getDataComenzii());
    }

    @Test
    void getTotal() {
        Comanda comanda = new Comanda(1, 2, "2023-12-01", 100.0, "Livrata");
        assertEquals(100.0, comanda.getTotal());
    }

    @Test
    void setTotal() {
        Comanda comanda = new Comanda(1, 2, "2023-12-01", 100.0, "Livrata");
        comanda.setTotal(150.0);
        assertEquals(150.0, comanda.getTotal());
    }

    @Test
    void getStatus() {
        Comanda comanda = new Comanda(1, 2, "2023-12-01", 100.0, "Livrata");
        assertEquals("Livrata", comanda.getStatus());
    }

    @Test
    void setStatus() {
        Comanda comanda = new Comanda(1, 2, "2023-12-01", 100.0, "Livrata");
        comanda.setStatus("In Procesare");
        assertEquals("In Procesare", comanda.getStatus());
    }
}
