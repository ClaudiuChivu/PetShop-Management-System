/** Clasa pentru Controller
 * @author Chivu Claudiu Dumitru
 * @version 27 decembrie 2024
 */
package com.example.demoSpringAWJ.controllers;

import com.example.demoSpringAWJ.entities.Produs;
import com.example.demoSpringAWJ.services.ProdusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Arrays;

import java.util.List;
import java.util.ArrayList;
@Controller
public class ProdusController {

    @Autowired
    private ProdusService produsService;

    // Afișează toate produsele
    @GetMapping("/produse")
    public String afiseazaProduse(Model model) {
        model.addAttribute("produse", produsService.getAllProduse());
        return "produse";  // Va încărca pagina Thymeleaf
    }

    // Adaugă un produs
    @PostMapping("/adaugareProdus")
    public String adaugaProdus(@RequestParam String numeProdus, @RequestParam String tipHrana,
                               @RequestParam double pret, @RequestParam int stoc, Model model) {

        List<String> erori = new ArrayList<>(); // Colectăm toate erorile

        // Validare tip produs
        List<String> tipuriValide = Arrays.asList("hrana uscata", "hrana umeda", "jucarii", "accesorii");
        if (!tipuriValide.contains(tipHrana)) {
            erori.add("Tipul produsului nu este valid! Tipuri permise: " + String.join(", ", tipuriValide));
        }

        // Validare preț
        if (pret <= 0) {
            erori.add("Prețul trebuie să fie un număr pozitiv!");
        }

        // Validare stoc
        if (stoc < 0) {
            erori.add("Stocul nu poate fi negativ!");
        }

        // Validare nume produs
        if (numeProdus == null || numeProdus.trim().isEmpty()) {
            erori.add("Numele produsului nu poate fi gol!");
        }

        // Dacă sunt erori, le adăugăm în model și rămânem pe pagina curentă
        if (!erori.isEmpty()) {
            model.addAttribute("eroare", String.join("<br>", erori));  // Afișează toate erorile ca un text HTML
            model.addAttribute("produse", produsService.getAllProduse()); // Păstrăm lista de produse
            return "produse";  // Rămânem pe aceeași pagină cu mesajele de eroare
        }

        // Crează și adaugă produsul dacă nu sunt erori
        Produs produs = new Produs();
        produs.setNumeProdus(numeProdus);
        produs.setTipHrana(tipHrana);
        produs.setPret(pret);
        produs.setStoc(stoc);
        produsService.adaugaProdus(produs);

        return "redirect:/produse";  // Redirecționează spre lista de produse
    }


    // Încărcați datele produsului pentru editare
    @GetMapping("/editareProdus")
    public String editareProdus(@RequestParam("id") int id, Model model) {
        Produs produs = produsService.getProdusDupaId(id);
        if (produs != null) {
            model.addAttribute("produs", produs); // Păstrăm produsul în model pentru a-l preîncărca în formular
        }
        return "editareProdus";  // Formularul pentru editare
    }

    // Salvează modificările produsului
    // Salvează modificările produsului
    @PostMapping("/salveazaModificari")
    public String salveazaModificari(@RequestParam("id") int id, @RequestParam String numeProdus,
                                     @RequestParam String tipHrana, @RequestParam double pret,
                                     @RequestParam int stoc, Model model) {

        List<String> erori = new ArrayList<>(); // Listă pentru a stoca erorile

        // Validare tip produs
        List<String> tipuriValide = Arrays.asList("hrana uscata", "hrana umeda", "jucarii", "accesorii");
        if (!tipuriValide.contains(tipHrana)) {
            erori.add("Tipul produsului nu este valid! Tipuri permise: " + String.join(", ", tipuriValide));
        }

        // Validare preț
        if (pret <= 0) {
            erori.add("Prețul trebuie să fie un număr pozitiv!");
        }

        // Validare stoc
        if (stoc < 0) {
            erori.add("Stocul nu poate fi negativ!");
        }

        // Validare nume produs
        if (numeProdus == null || numeProdus.trim().isEmpty()) {
            erori.add("Numele produsului nu poate fi gol!");
        }

        // Dacă există erori, returnăm pagina de editare cu mesajele de eroare
        if (!erori.isEmpty()) {
            model.addAttribute("erori", erori);  // Adăugăm erorile în model
            Produs produs = produsService.getProdusDupaId(id);  // Încărcăm produsul pentru a-l edita
            model.addAttribute("produs", produs);  // Păstrăm produsul în model
            return "editareProdus";  // Rămânem pe aceeași pagină
        }

        // Dacă nu sunt erori, actualizăm produsul
        Produs produs = new Produs();
        produs.setIdProdus(id);
        produs.setNumeProdus(numeProdus);
        produs.setTipHrana(tipHrana);
        produs.setPret(pret);
        produs.setStoc(stoc);

        produsService.updateProdus(produs);

        model.addAttribute("produse", produsService.getAllProduse());
        model.addAttribute("mesajConfirmare", "Produsul a fost modificat cu succes.");
        return "produse";  // Rămânem pe pagina cu lista de produse
    }

    @GetMapping("/confirmaStergere")
    public String confirmaStergere(@RequestParam("id") int id, Model model) {
        model.addAttribute("idProdus", id); // Adăugăm ID-ul produsului pentru confirmare
        return "confirmareStergere";  // Încărcăm pagina de confirmare
    }

    // Șterge un produs
    @PostMapping("/stergeProdus")
    public String stergeProdus(@RequestParam("id") int id, Model model) {
        boolean isDeleted = produsService.stergeProdus(id);  // Apelăm serviciul pentru a șterge produsul
        if (isDeleted) {
            model.addAttribute("mesajConfirmare", "Produsul a fost șters cu succes.");
        } else {
            model.addAttribute("mesajConfirmare", "Produsul nu a fost găsit.");
        }
        model.addAttribute("produse", produsService.getAllProduse());
        return "produse";  // Rămânem pe aceeași pagină
    }
}

