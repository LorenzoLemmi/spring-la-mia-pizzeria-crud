package pizzerie.la_mia_pizzeria.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pizzerie.la_mia_pizzeria.entities.Pizza;
import pizzerie.la_mia_pizzeria.repositories.PizzaRepository;

import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;




@Controller
@RequestMapping("/pizze")
public class PizzeriaController {
    
    @Autowired
    private PizzaRepository repository;

    @GetMapping
    public String getIndex(Model model) {
        List<Pizza> elencoPizze = repository.findAll();
        model.addAttribute("elencoPizze", elencoPizze);
        return "index";
    }

    @GetMapping("/show/{id}")
    public String showDetail(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("pizza", repository.findById(id).get());
        return "/pizze/show";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "/pizze/form";
    }
    
    @PostMapping("/create")
    public String createSubmit(@Valid @ModelAttribute("pizza") Pizza pizza, BindingResult bindingResult, 
    Model model) {
        if (bindingResult.hasErrors()) {
            return "/pizze/form";
        }
        repository.save(pizza);
        return "redirect:/pizze";
    }
}