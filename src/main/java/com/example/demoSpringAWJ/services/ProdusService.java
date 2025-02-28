/** Clasa pentru Service
 * @author Chivu Claudiu Dumitru
 * @version 27 decembrie 2024
 */
package com.example.demoSpringAWJ.services;

import com.example.demoSpringAWJ.entities.Produs;
import com.example.demoSpringAWJ.repositories.ProdusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdusService {

    private final ProdusRepository produsRepository;

    public ProdusService(ProdusRepository produsRepository) {
        this.produsRepository = produsRepository;
    }

    // Obține toate produsele
    public List<Produs> getAllProduse() {
        return produsRepository.getAllProduse();
    }

    // Adaugă un produs
    public void adaugaProdus(Produs produs) {
        produsRepository.adaugaProdus(produs);
    }

    // Șterge un produs după ID
    public boolean stergeProdus(int id) {
        if (produsRepository.getProdusDupaId(id) != null) {
            produsRepository.deleteProdus(id);
            return true;
        }
        return false;
    }

    // Obține un produs după ID
    public Produs getProdusDupaId(int id) {
        return produsRepository.getProdusDupaId(id);
    }

    // Actualizează un produs
    public void updateProdus(Produs produs) {
        produsRepository.updateProdus(produs);
    }
}
