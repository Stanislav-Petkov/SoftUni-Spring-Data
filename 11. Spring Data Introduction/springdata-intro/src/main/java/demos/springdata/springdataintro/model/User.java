package demos.springdata.springdataintro.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(unique = true)
    private String name;

    @NonNull
    private int age;

    // When a user is deleted, his accounts are deleted also
    @OneToMany(mappedBy = "user", targetEntity = Account.class, cascade = CascadeType.ALL)
    private Set<Account> accounts = new HashSet<>();

    // In one of the two entities equals, hashcode and toString should be implemented manually,
    // because they contain cyclic dependencies.
    // In this entity the equals, hashcode and toString are manually implemented,
    // because otherwise a loop is created between this entity and Account entity
    // Cyclic dependencies:
    // these methods in this entity refer the fields in the Account entity and
    // these methods in the Account entity refer the fields in the User entity
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append('}');
        return sb.toString();
    }
}
