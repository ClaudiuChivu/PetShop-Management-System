package com.example.demoSpringAWJ.controllers;

import com.example.demoSpringAWJ.entities.Comanda;
import com.example.demoSpringAWJ.services.ComandaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ComandaControllerTest {

    @InjectMocks
    private ComandaController comandaController;

    @Mock
    private ComandaService comandaService;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void vizualizareComenzi() {
        // Arrange
        Comanda comanda1 = new Comanda(1, 101, "2025-01-01", 100.0, "In Procesare");
        Comanda comanda2 = new Comanda(2, 102, "2025-01-02", 200.0, "Livrata");
        List<Comanda> mockComenzi = Arrays.asList(comanda1, comanda2);

        when(comandaService.getComenziFiltrate(null, null, null)).thenReturn(mockComenzi);

        // Act
        String viewName = comandaController.vizualizareComenzi(null, null, null, model);

        // Assert
        assertEquals("comenzi", viewName);
        verify(model).addAttribute("comenzi", mockComenzi);
    }

    @Test
    void updateStatusForm() {
        // Arrange
        Comanda mockComanda = new Comanda(1, 101, "2025-01-01", 100.0, "In Procesare");

        when(comandaService.getComandaDupaId(1)).thenReturn(mockComanda);

        // Act
        String viewName = comandaController.updateStatusFormular(1, model);

        // Assert
        assertEquals("updateStatus", viewName);
        verify(model).addAttribute("comanda", mockComanda);
    }

    @Test
    void updateStatusComanda() {
        // Act
        String viewName = comandaController.updateStatusComanda(1, "Livrata");

        // Assert
        assertEquals("redirect:/comenzi", viewName);
        verify(comandaService).updateStatusComanda(1, "Livrata");
    }
}
