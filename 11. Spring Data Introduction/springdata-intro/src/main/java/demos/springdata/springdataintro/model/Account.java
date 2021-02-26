package demos.springdata.springdataintro.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;


// model package contains the entities

@Entity
@Table(name = "accounts")
@Data // getters, setters, equals, hashcode, toString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Min(0)
    private BigDecimal balance;


    @ManyToOne
    @NonNull
    private User user;
}
