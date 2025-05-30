package pizzerie.la_mia_pizzeria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pizzerie.la_mia_pizzeria.entities.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

}
