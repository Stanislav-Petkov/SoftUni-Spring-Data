package softuni.library.models.dtos.xmls;

import org.hibernate.validator.constraints.Length;

import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "library")
@XmlAccessorType(XmlAccessType.FIELD)
public class LibrarySeedDto {
    @XmlElement
    private String name;
    @XmlElement
    private String location;
    @XmlElement
    private Integer rating;
    @XmlElement
    private BookSeedXmlDto book;

    public LibrarySeedDto() {
    }

    @Length(min = 3)
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 5)
    @NotNull
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Min(value = 1)
    @Max(value = 10)
    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public BookSeedXmlDto getBook() {
        return book;
    }

    public void setBook(BookSeedXmlDto book) {
        this.book = book;
    }
}
