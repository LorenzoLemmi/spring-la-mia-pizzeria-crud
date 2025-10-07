package pizzerie.la_mia_pizzeria.controllers;

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

import jakarta.validation.Valid;
import pizzerie.la_mia_pizzeria.entities.Ingrediente;
import pizzerie.la_mia_pizzeria.repositories.IngredienteRepository;



@Controller
@RequestMapping("/ingredienti")
public class IngredienteController {

    @Autowired
    private IngredienteRepository repository;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("list", repository.findAll());
        model.addAttribute("ingrediente", new Ingrediente());
        return "ingredienti/index";
    }

    @PostMapping("/create")
    public String createIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, 
                                BindingResult bindingResult, Model model) {
        Ingrediente ingr = repository.findByNome(ingrediente.getNome());
        if (ingr != null) {
            bindingResult.addError(new ObjectError("nome", "Ingrediente già presente"));
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("list", repository.findAll());
            model.addAttribute("ingrediente", new Ingrediente());
            return "ingredienti/index";
        }
        repository.save(ingrediente);

        return "redirect:/ingredienti";
    }

    @PostMapping("/delete/{id}")
    public String deleteIngrediente (@PathVariable Integer id, Model model) {
        repository.deleteById(id);
        return "redirect:/ingredienti";
    }
}
