package com.example.demoSpringAWJ.controllers;

import com.example.demoSpringAWJ.entities.Produs;
import com.example.demoSpringAWJ.services.ProdusService;
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

class ProdusControllerTest {

    @InjectMocks
    private ProdusController produsController;

    @Mock
    private ProdusService produsService;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void afiseazaProduse() {
        // Arrange
        Produs produs1 = new Produs(1, "Produs 1", "Hrana", 10.5, 100);
        Produs produs2 = new Produs(2, "Produs 2", "Hrana", 20.0, 50);
        List<Produs> mockProduse = Arrays.asList(produs1, produs2);

        when(produsService.getAllProduse()).thenReturn(mockProduse);

        // Act
        String viewName = produsController.afiseazaProduse(model);

        // Assert
        assertEquals("produse", viewName);
        verify(model).addAttribute("produse", mockProduse);
    }

   // @Test
    //void adaugaProdus() {
        // Act
    //    String viewName = produsController.adaugaProdus("Produs 1", "Hrana", 10.5, 100);

        // Assert
      //  assertEquals("redirect:/produse", viewName);
        //verify(produsService).adaugaProdus(any(Produs.class));  // Verificăm dacă serviciul a fost apelat pentru a adăuga produsul
   // }

    @Test
    void editareProdus() {
        // Arrange
        Produs produs = new Produs(1, "Produs 1", "Hrana", 10.5, 100);
        when(produsService.getProdusDupaId(1)).thenReturn(produs);

        // Act
        String viewName = produsController.editareProdus(1, model);

        // Assert
        assertEquals("editareProdus", viewName);
        verify(model).addAttribute("produs", produs);
    }





    @Test
    void confirmaStergere() {
        // Act
        String viewName = produsController.confirmaStergere(1, model);

        // Assert
        assertEquals("confirmareStergere", viewName);
        verify(model).addAttribute("idProdus", 1);
    }

    @Test
    void stergeProdus() {
        // Act
        String viewName = produsController.stergeProdus(1, model);

        // Assert
        assertEquals("produse", viewName);
        verify(produsService).stergeProdus(1);  // Verificăm dacă serviciul a fost apelat pentru a șterge produsul
    }
}
