package pizzerie.la_mia_pizzeria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pizzerie.la_mia_pizzeria.entities.Ingrediente;



public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {

    public Ingrediente findByNome(String nome);

}
