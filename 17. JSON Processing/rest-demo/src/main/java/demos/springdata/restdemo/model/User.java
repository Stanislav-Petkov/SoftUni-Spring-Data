package demos.springdata.restdemo.model;

import com.google.gson.annotations.Expose;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

    // Only the @Expose fields are serialized
    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Expose
    @NonNull
    @Length(min = 2, max = 60)
    private String firstName;

    @Expose
    @NonNull
    @Length(min = 2, max = 60)
    private String lastName;

    @Expose
    @NonNull
    @Length(min = 3, max = 60)
    @Column(unique = true, nullable = false)
    @NotNull
    private String username;

    // Length min 3 does not exclude a null value
    // if the value is not null it should be at least 3 symbols

    // all NonNull fields are in the default constructor

    // NotNull is from the bean validation, the value will be validated
    // before reaching the persistence

    // nullable = false means that the value in the database cannot be null
    // this will be a constraint of the column in the table

    // With (serialize = false) this field value is not serialized to JSON
    // The password is only coming to the application and never returned
    @Expose(serialize = false)
    @NonNull
    @Length(min = 4, max = 30)
    @Column(unique = true, nullable = false)
    @NotNull
    private String password;

    // The role is mandatory, NotNull is for the bean validation

    // With NonNull the field goes to the RequiredArgConstructor
    @Expose
    @NotNull
    @NonNull
    private String role = "ROLE_USER";

    private boolean active = true;


    // We want to exclude the posts from the User toString()    @ToString.Exclude,
    // Because otherwise a cycle is created between the toStrings of User and Post
    // The leading side is the Post
    @OneToMany(mappedBy = "author")
    @ToString.Exclude
    private Collection<Post> posts = new ArrayList<Post>();

    @Expose
    private Date created = new Date();

    @Expose
    private Date modified = new Date();
}
















