package pizzerie.la_mia_pizzeria.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pizzerie.la_mia_pizzeria.entities.Pizza;




public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

    public List<Pizza> findByNomeContainingIgnoreCase(String nome);

    public Optional<Pizza> findByNome(String nome);
}
