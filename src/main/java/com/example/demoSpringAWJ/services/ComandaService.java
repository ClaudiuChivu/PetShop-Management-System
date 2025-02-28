/** Clasa pentru Service Comanda
 * @author Chivu Claudiu Dumitru
 * @version 03 ianuarie 2025
 */
package com.example.demoSpringAWJ.services;

import com.example.demoSpringAWJ.entities.Comanda;
import com.example.demoSpringAWJ.repositories.ComandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class ComandaService {

    @Autowired
    private ComandaRepository comandaRepository;

    // Obține comenzile filtrate după status, dată și idClient
    public List<Comanda> getComenziFiltrate(String status, String dataComenzii, Integer idClient) {
        if (status != null && !status.isEmpty()) {
            return comandaRepository.getComenziDupaStatus(status);
        } else if (dataComenzii != null && !dataComenzii.isEmpty()) {
            return comandaRepository.getComenziDupaData(dataComenzii);
        } else if (idClient != null) {
            return comandaRepository.getComenziDupaClient(idClient);
        } else {
            return comandaRepository.getAllComenzi();
        }
    }

    // Obține o comandă după ID
    public Comanda getComandaDupaId(int idComanda) {
        return comandaRepository.getComandaDupaId(idComanda);
    }

    // Actualizează statusul unei comenzi
    public void updateStatusComanda(int idComanda, String status) {
        comandaRepository.updateComandaStatus(idComanda, status);
    }

    public List<Comanda> getComenziInPerioada(String startDate, String endDate) {
        return comandaRepository.getComenziInPerioada(startDate, endDate);
    }

    public int calculeazaNumarTotalComenzi(List<Comanda> comenzi) {
        return comenzi.size();
    }

    public double calculeazaVenitTotal(List<Comanda> comenzi) {
        return comenzi.stream().mapToDouble(Comanda::getTotal).sum();
    }

    public double calculeazaMediaValoareComenzi(List<Comanda> comenzi) {
        return comenzi.isEmpty() ? 0.0 : comenzi.stream().mapToDouble(Comanda::getTotal).average().orElse(0.0);
    }

    public String calculeazaCelMaiPopularStatus(List<Comanda> comenzi) {
        return comenzi.stream()
                .collect(Collectors.groupingBy(Comanda::getStatus, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Necunoscut");
    }

}
