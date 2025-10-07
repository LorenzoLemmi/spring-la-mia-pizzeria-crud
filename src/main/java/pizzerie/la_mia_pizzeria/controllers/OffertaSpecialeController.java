package pizzerie.la_mia_pizzeria.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import pizzerie.la_mia_pizzeria.entities.OffertaSpeciale;
import pizzerie.la_mia_pizzeria.entities.Pizza;
import pizzerie.la_mia_pizzeria.repositories.OffertaSpecialeRepository;
import pizzerie.la_mia_pizzeria.repositories.PizzaRepository;


@Controller
@RequestMapping("/offerte")
public class OffertaSpecialeController {

    @Autowired
    private OffertaSpecialeRepository offertaRepo;

    @Autowired
    private PizzaRepository pizzaRepo;

    // Mostra il form per creare una nuova offerta
    @GetMapping("/create/{id}")
    public String createForm(@PathVariable Integer id, Model model) {
        Pizza pizza = pizzaRepo.findById(id).get();
        OffertaSpeciale offerta = new OffertaSpeciale();
        offerta.setPizza(pizza);
        model.addAttribute("offerta", offerta);
        model.addAttribute("pizza", pizza);
        return "pizze/formOffertaSpeciale";
    }

    // Gestisce l'invio del form di creazione dell'offerta
    @PostMapping("/create")
    public String createSubmit(@Valid @ModelAttribute("offerta") OffertaSpeciale offerta, 
                            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("pizza", offerta.getPizza());
            return "pizze/formOffertaSpeciale";
        }
        offertaRepo.save(offerta);
        return "redirect:/pizze/show/" + offerta.getPizza().getId();
    }

    // Mostra il form per modificare un'offerta esistente
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        OffertaSpeciale offerta = offertaRepo.findById(id).get();
        model.addAttribute("offerta", offerta);
        model.addAttribute("pizza", offerta.getPizza());
        return "pizze/formOffertaSpecialeUpdate";
    }

    // Gestisce l'invio del form di modifica
    @PostMapping("/edit/{id}")
    public String editSubmit(@PathVariable Integer id, 
                            @Valid @ModelAttribute("offerta") OffertaSpeciale offerta,
                            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("pizza", offerta.getPizza());
            return "pizze/formOffertaSpecialeUpdate";
        }
        Integer pizzaId = offerta.getPizza().getId();
        Pizza pizza = pizzaRepo.findById(pizzaId).orElse(null);
        if (pizza == null) {
            return "redirect:/pizze"; // oppure mostra un errore personalizzato
        }
        offerta.setPizza(pizza);

        offertaRepo.save(offerta);
        return "redirect:/pizze/show/" + offerta.getPizza().getId();
    }

    // Elimina un'offerta
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Optional<OffertaSpeciale> optOfferta = offertaRepo.findById(id);
        if (optOfferta.isEmpty()) {
            return "redirect:/pizze";
        }
        OffertaSpeciale offerta = optOfferta.get();
        Integer pizzaId = offerta.getPizza().getId();
        offertaRepo.delete(offerta);
        return "redirect:/pizze/show/" + pizzaId;

    }

}

