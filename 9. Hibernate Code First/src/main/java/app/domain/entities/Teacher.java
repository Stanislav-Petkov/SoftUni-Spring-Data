package app.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "teachers")
public class Teacher extends Person{

    private String degree;

    public Teacher(){

    }

    public Teacher(String firstName, String lastName, String degree){
        super(firstName, lastName);
        setDegree(degree);
    }

    @Column
    public String getDegree(){
        return this.degree;
    }
    private void setDegree(String degree) {
        this.degree = degree;
    }

}
