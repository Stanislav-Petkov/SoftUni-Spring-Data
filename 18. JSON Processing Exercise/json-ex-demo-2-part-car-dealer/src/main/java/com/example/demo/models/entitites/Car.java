package com.example.demo.models.entitites;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "cars")
public class Car extends BaseEntity {

    private String make;
    private String model;
    private BigInteger travelledDistance;
    private Set<Part> parts;// = new HashSet<>();
    private Sale sale;

    public Car() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "travelled_distance", precision = 22, scale = 0)
    public BigInteger getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(BigInteger travelledDistance) {
        this.travelledDistance = travelledDistance;
    }


    //Owning Side
    @ManyToMany(cascade = {ALL})
    @JoinTable(name = "parts_cars",
            joinColumns = @JoinColumn(name = "part_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id"))
    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }

    @OneToOne(mappedBy = "car")
    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
}
