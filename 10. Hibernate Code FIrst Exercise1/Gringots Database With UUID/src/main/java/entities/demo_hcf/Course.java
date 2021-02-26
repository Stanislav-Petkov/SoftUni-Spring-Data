package entities.demo_hcf;

import entities.BaseEntityIdentity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course extends BaseEntityIdentity {

    private String name;

    private Set<Student> students;

    private Teacher teacher;

    public Course() {
    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // JoinTable creates foreign keys
    @ManyToMany
    @JoinTable(name = "courses_students",
                joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"))
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    // Many courses can be related to 1 teacher, JoinColumn creates a foreign key
    // teacher_id will be the name of the column in the courses table and references the id in the teachers table
    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
