package pizzerie.la_mia_pizzeria.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import pizzerie.la_mia_pizzeria.entities.OffertaSpeciale;
import pizzerie.la_mia_pizzeria.entities.Pizza;
import pizzerie.la_mia_pizzeria.repositories.OffertaSpecialeRepository;
import pizzerie.la_mia_pizzeria.repositories.PizzaRepository;




@Controller
@RequestMapping("/pizze")
public class PizzeriaController {
    
    @Autowired
    private PizzaRepository repository;
    @Autowired
    private OffertaSpecialeRepository offertaSpecialeRepository;

    @GetMapping
    public String index(Model model, @RequestParam(name="keyword", required=false) String keyword) {
        List<Pizza> pizze = null;
        if (keyword == null || keyword.isBlank()) {
            pizze = repository.findAll();
        } else {
            pizze = repository.findByNomeContainingIgnoreCase(keyword);
        }

        model.addAttribute("pizze", pizze);
        return "index";
    }

    @GetMapping("/show/{id}")
    public String showDetail(Model model, @PathVariable("id") Integer id) {
        Optional<Pizza> optPizza = repository.findById(id);

        if (optPizza.isPresent()) {
            model.addAttribute("pizza", optPizza.get());
            model.addAttribute("empty", false);
            return "/pizze/show";
        } else {
            model.addAttribute("empty", true);
        }
        return "/pizze/show";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "/pizze/form";
    }
    
    @PostMapping("/create")
    public String createSubmit(@Valid @ModelAttribute("pizza") Pizza formPizza, 
                            BindingResult bindingResult, RedirectAttributes redirectAttributes,
                            Model model) {
        Optional<Pizza> optPizza = repository.findByNome(formPizza.getNome());
        if (optPizza.isPresent()) {
            bindingResult.addError(new ObjectError("nome", "Nome già presente"));
        }

        if (bindingResult.hasErrors()) {
            return "/pizze/form";
        }
        repository.save(formPizza);
        redirectAttributes.addFlashAttribute("successMessage", "Pizza creata con successo");
        return "redirect:/pizze";
    }

    @GetMapping("/edit/{id}")
    public String editPizza(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("pizza", repository.findById(id).get());
        return "/pizze/formUpdate";
    }

    @PostMapping("/edit/{id}")
    public String updatePizza(@PathVariable("id") Integer id,
                            @Valid @ModelAttribute("pizza") Pizza formPizza,
                            BindingResult bindingResult) {
        Pizza oldPizza = repository.findById(formPizza.getId()).get();
        if (!oldPizza.getNome().equalsIgnoreCase(formPizza.getNome())) {
            bindingResult.addError(new ObjectError("nome", "Non è possibile modificare il nome della pizza"));
        }
        
        if (bindingResult.hasErrors()) {
            return "/pizze/formUpdate";
        }
        repository.save(formPizza);
        return "redirect:/pizze";
    }

    @PostMapping("/delete/{id}")
    public String deletePizza(@PathVariable("id") Integer id) {
        Pizza pizza = repository.findById(id).get();
        for (OffertaSpeciale offertaSpecialeToDelete : pizza.getOffertaSpeciale()) {
            offertaSpecialeRepository.delete(offertaSpecialeToDelete);
        }
        repository.deleteById(id);
        return "redirect:/pizze";
    }
}