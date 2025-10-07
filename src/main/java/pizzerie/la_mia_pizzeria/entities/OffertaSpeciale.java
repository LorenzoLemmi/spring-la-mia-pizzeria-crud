package pizzerie.la_mia_pizzeria.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "offerte_speciali")
public class OffertaSpeciale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Il titolo è obbligatorio")
    @Column(name = "titolo", nullable = false)
    private String titolo;

    @NotNull(message = "La data di inizio è obbligatoria")
    @Column(name = "dataInizio", nullable = false)
    private LocalDate dataInizio;

    @NotNull(message = "La data di fine è obbligatoria")
    @Column(name = "dataFine", nullable = false)
    private LocalDate dataFine;

    @NotNull(message = "Lo sconto è obbligatorio")
    @Column(name = "scontoPercentuale", nullable = false)
    private Integer scontoPercentuale;

    @ManyToOne()
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza;

    public Integer getScontoPercentuale() {
        return scontoPercentuale;
    }

    public void setScontoPercentuale(Integer scontoPercentuale) {
        this.scontoPercentuale = scontoPercentuale;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

}
