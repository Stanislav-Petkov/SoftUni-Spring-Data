package softuni.library.models.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book extends BaseEntity {
    private String name;
    private Integer edition;
    private LocalDate written;
    private String description;
    private Author author;

    public Book() {
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "edition")
    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    @Column(name = "written", nullable = false)

    public LocalDate getWritten() {
        return written;
    }

    public void setWritten(LocalDate written) {
        this.written = written;
    }

    @Column(name = "description", length = 10000000)
    @Length(min = 100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(cascade = {CascadeType.MERGE},fetch = FetchType.EAGER)
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
