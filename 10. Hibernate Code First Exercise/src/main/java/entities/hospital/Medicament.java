package entities.hospital;

import entities.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "medicaments")
public class Medicament extends BaseEntity {

    private String name;
    private Patient patient;

    public Medicament(){

    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "patient_id",referencedColumnName = "id", nullable = false)
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
