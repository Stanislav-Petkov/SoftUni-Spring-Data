package softuni.library.models.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "characters")
public class Character extends BaseEntity{

    private String firstName;
    private char middleName;
    private String lastName;
    private Integer age;
    private String role;
    private LocalDate birthday;
    private Book book;

    public Character() {
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "middle_name", nullable = false)
    public char getMiddleName() {
        return middleName;
    }

    public void setMiddleName(char middleName) {
        this.middleName = middleName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "age", nullable = false)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Column(name = "role", nullable = false)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Column(name = "birthday", nullable = false)
    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @ManyToOne(cascade = {CascadeType.MERGE},fetch = FetchType.EAGER)
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
