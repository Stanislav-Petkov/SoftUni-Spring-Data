package softuni.library.models.dtos.jsons;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class BookSeedDto {
    @Expose
    private String description;
    @Expose
    private Integer edition;
    @Expose
    private String name;
    @Expose
    private LocalDate written;
    @Expose
    private Integer author;

    public BookSeedDto() {
    }

    @Length(min = 100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Min(value = 1)
    @Max(value = 5)
    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    @Length(min = 5)
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public LocalDate getWritten() {
        return written;
    }

    public void setWritten(LocalDate written) {
        this.written = written;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }
}
