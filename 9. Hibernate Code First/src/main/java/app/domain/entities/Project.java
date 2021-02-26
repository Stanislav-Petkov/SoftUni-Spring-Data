package app.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Project {

    private long id;

    private String name;

    private Set<Student> students;

    public Project(){

    }

    public Project(String name){
        setName(name);
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "projects")
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
