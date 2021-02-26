package course.springdata.advanced.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "labels")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include // Add only the id in the equals and hashCode
    private Long id;

    private String title;
    private String subtitle;

    @ToString.Exclude // Remove the shampoos from the ToString so that there are no cycles
    @OneToMany(mappedBy = "label")
    private Set<Shampoo> shampoos = new HashSet<>();
}
