package demos.springdata.advanced.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Objects;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name = "labels")
@NoArgsConstructor
@AllArgsConstructor
public class Label {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(unique = true)
    private String title;
    private String subtitle;

    // One label with many shampoos
    // foreign key mapped from the leading Many side
    // by default FetchType is Lazy, when I fetch a label it will not fetch the shampoos automatically
    // If I detach the label and send it somewhere, it has a method getShampoos, which returns a collection of
    // Shampoos, but If I detach a label and send the Label somewhere to a client, the shampoos will to
    // travel with it and the client will not be able to access the shampoos of the Label object
    // if the shampoos should be changed by the client the FetchType should be Eager, then the client can change
    // them and the new Label with its changed shampoos can be merged
    @OneToMany(mappedBy = "label", fetch = LAZY)
    private Set<Shampoo> shampoos;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Label label = (Label) o;
        return id == label.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}























