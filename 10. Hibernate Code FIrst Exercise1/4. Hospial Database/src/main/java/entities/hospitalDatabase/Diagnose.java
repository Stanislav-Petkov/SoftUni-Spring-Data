package entities.hospitalDatabase;

import entities.BaseEntity;

import javax.persistence.*;
import java.util.regex.Pattern;

@Entity
@Table(name = "diagnoses")
public class Diagnose extends BaseEntity {

    private String name;
    private String comments;
    private Patient patient;

    public Diagnose(){
    }

    @Column(name = "name",nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "comments")
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
