package demos.springdata.restdemo.model;

import com.google.gson.annotations.Expose;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Post {
    @Expose
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Expose
    @NonNull
    @Length(min = 2, max = 80)
    private String title;

    @Expose
    @NonNull
    @Length(min = 2, max = 2048)
    private String content;

    // If there is no author, the authorId will be taken
    @Expose
    @ManyToOne(optional = true)
    private User author;

    // With transient the field will not be persisted and will not generate a value in the database
    // Field only for deserialization, it will not be serialized
    // Transient allows the field to be only for the dto and not for the entity
    // If it is not Transient, there will be an error generated

    // (serialize = false) it will only deserialize this field,
    // the data will only be send, but will not be returned,
    // only the author found by his id will be returned
    @Expose(serialize = false)
    @Transient
    private Long authorId;

    //  post creation date
    @Expose
    private Date created = new Date();

    // post modification date
    @Expose
    private Date modified = new Date();

}
