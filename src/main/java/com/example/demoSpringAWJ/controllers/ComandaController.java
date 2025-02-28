/** Clasa pentru controller Comanda
 * @author Chivu Claudiu Dumitru
 * @version 03 ianuarie 2025
 */
package com.example.demoSpringAWJ.controllers;

import com.example.demoSpringAWJ.entities.Comanda;
import com.example.demoSpringAWJ.services.ComandaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Controller
@RequestMapping("/comenzi")
public class ComandaController {

    @Autowired
    private ComandaService comandaService;

    // Vizualizează toate comenzile
    @GetMapping
    public String vizualizareComenzi(@RequestParam(required = false) String status,
                                     @RequestParam(required = false) String dataComenzii,
                                     @RequestParam(required = false) Integer idClient,
                                     Model model) {
        // Apelăm serviciul pentru a obține comenzile filtrate
        List<Comanda> comenzi = comandaService.getComenziFiltrate(status, dataComenzii, idClient);
        model.addAttribute("comenzi", comenzi);
        return "comenzi"; // Afișează comenzile
    }


    // Formular pentru actualizarea statusului unei comenzi
    @GetMapping("/updateStatus")
    public String updateStatusFormular(@RequestParam("idComanda") int idComanda, Model model) {
        System.out.println("ID Comanda primit: " + idComanda); // Verifică parametrul
        Comanda comanda = comandaService.getComandaDupaId(idComanda);
        if (comanda == null) {
            System.out.println("Comanda nu a fost găsită!");
            model.addAttribute("error", "Comanda nu a fost găsită!");
            return "error"; // Pagina de eroare
        }
        System.out.println("Comanda găsită: " + comanda);
        model.addAttribute("comanda", comanda);
        return "updateStatus"; // Pagina de actualizare a statusului
    }



    // Actualizează statusul unei comenzi

    @PostMapping("/updateStatusComanda")
    public String updateStatusComanda(@RequestParam("idComanda") int idComanda, @RequestParam("status") String status) {
        System.out.println("ID Comanda: " + idComanda);
        System.out.println("Status: " + status);
        comandaService.updateStatusComanda(idComanda, status);
        return "redirect:/comenzi";
    }


    @GetMapping("/statistici")
    public String statisticiComenzi(@RequestParam(required = false) String statistica,
                                    Model model) {
        // Dacă nu s-a selectat nici o opțiune, setăm valoarea default
        if (statistica == null || statistica.isEmpty()) {
            statistica = "numarTotalComenzi"; // Sau orice opțiune dorită
        }

        // Preluăm comenzile și realizăm calculele
        List<Comanda> comenzi = comandaService.getComenziInPerioada("2000-01-01", "2100-01-01");
        int numarTotalComenzi = comandaService.calculeazaNumarTotalComenzi(comenzi);
        double venitTotal = comandaService.calculeazaVenitTotal(comenzi);
        double mediaValoareComenzi = comandaService.calculeazaMediaValoareComenzi(comenzi);
        String celMaiPopularStatus = comandaService.calculeazaCelMaiPopularStatus(comenzi);

        // Adăugăm datele în model
        model.addAttribute("numarTotalComenzi", numarTotalComenzi);
        model.addAttribute("venitTotal", venitTotal);
        model.addAttribute("mediaValoareComenzi", mediaValoareComenzi);
        model.addAttribute("celMaiPopularStatus", celMaiPopularStatus);
        model.addAttribute("statistica", statistica);

        return "statistici"; // Returnează template-ul "statistici.html"
    }
}
