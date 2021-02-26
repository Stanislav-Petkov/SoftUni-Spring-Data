package entities.hospitalDatabase;

import entities.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "medicament")
public class Medicament extends BaseEntity {
    private String name;
    private Patient patient;

    public Medicament(){
    }

    @Column(name = "name",nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "patient_it",referencedColumnName = "id")
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
