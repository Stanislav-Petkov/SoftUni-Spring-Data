package demos.springdata.advanced.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Set;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.EnumType.ORDINAL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name = "shampoos")
@AllArgsConstructor
@NoArgsConstructor
public class Shampoo {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String brand;
    private double price;

    @Enumerated(ORDINAL)
    private Size size;

    // optional = true - There might not exist a label for the shampoo
    // When we have ManyToOne the cascade should NOT be All,
    // if it is All when we delete 1 Shampoo it will delete its Label, which is shared among many Shampoos
    // If CascadeType.Persist is used. when a shampoo with a Label is created, the Label will be Persisted also
    //CascadeType.Refresh - synchronizes the stated from the memory to the stated on the disc
    // A good cascade option is Persist and Refresh
    // https://vladmihalcea.com/a-beginners-guide-to-jpa-and-hibernate-cascade-types/
    // Many shampoos can have 1 label
    @ManyToOne(optional = true, cascade = {PERSIST, REFRESH} )
    private Label label;

    //@ManyToMany -  One Shampoo can have many ingredients, One Ingredient can have many shampoos
    // The ingredients are a set and it can be empty, they are not optional
    // When I fetch a shampoo I want to fetch the ingredients
    // When I fetch ingredient I do NOT want to fetch shampoos, because 1 ingredient can participate in many Shampoos
    @ManyToMany(cascade = {PERSIST, REFRESH}, fetch = EAGER)
    @JoinTable(//name = "shampoo_ingredient",
    joinColumns = @JoinColumn(name = "shampoo_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id"))
    private Set<Ingredient> ingredients;


}





















