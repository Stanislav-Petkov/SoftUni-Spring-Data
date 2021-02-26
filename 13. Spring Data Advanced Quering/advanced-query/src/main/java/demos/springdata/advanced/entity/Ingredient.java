package demos.springdata.advanced.entity;

import demos.springdata.advanced.dao.IngredientRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Objects;
import java.util.Set;

import static javax.persistence.CascadeType.REFRESH;

@Entity
@Table(name = "ingredients")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double price;

    // the leading side is the shampoo
    // I do not want to persist the shampoos from the Ingredient , so I do not put cascade = Persist
    // if the ingredient is refreshed its shampoos are refreshed with it also
    // I do not want when the Ingredient is deleted its shampoos to be deleted, so I do not set cascade=Remove
    @ManyToMany(mappedBy = "ingredients", cascade = REFRESH)// REFRESH
    private Set<Shampoo> shampoos;

    // be default lombok includes all fields in the hashcode
    // the hashcode of the Ingredient includes the hashcode of the shampoo and the opposite and a cycle it created
    // hashcode method should be manually implemented

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

























