package pizzerie.la_mia_pizzeria.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="pizze")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Il nome è obbligatorio")
    @Column(name="nome", nullable=false)
    private String nome;

    @NotBlank(message = "La descrizione è obbligatoria")
    @Column(name="descrizione", nullable=false)
    private String descrizione;

    @NotBlank(message = "L'URL dell'immagine è obbligatorio")
    @Column(name="url", nullable=false)
    private String url;

    @NotNull(message = "Il prezzo è obbligatorio")
    @DecimalMin(value = "0.01", message = "Il prezzo deve essere positivo")
    @Column(name="prezzo", nullable=false)
    private Double prezzo;

    @OneToMany(mappedBy="pizza")
    private List<OffertaSpeciale> offertaSpeciale;

    @ManyToMany()
    @JoinTable(name="pizza_ingredienti", 
            joinColumns=@JoinColumn(name="pizza_id"), 
            inverseJoinColumns=@JoinColumn(name="ingrediente_id"))
    private List<Ingrediente> ingredienti;

    public Integer getId() {
        return id;
    }

    public List<OffertaSpeciale> getOffertaSpeciale() {
        return offertaSpeciale;
    }

    public void setOffertaSpeciale(List<OffertaSpeciale> offertaSpeciale) {
        this.offertaSpeciale = offertaSpeciale;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public List<Ingrediente> getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(List<Ingrediente> ingredienti) {
        this.ingredienti = ingredienti;
    }

}
