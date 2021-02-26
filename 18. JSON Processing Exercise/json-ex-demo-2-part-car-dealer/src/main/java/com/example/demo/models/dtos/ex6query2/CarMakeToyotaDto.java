package com.example.demo.models.dtos.ex6query2;

import com.google.gson.annotations.Expose;
import org.springframework.ui.Model;

import javax.persistence.Id;
import java.math.BigInteger;

public class CarMakeToyotaDto {

    @Expose
    private long Id;
    @Expose
    private String Make;
    @Expose
    private String Model;
    @Expose
    private BigInteger TravelledDistance;

    public CarMakeToyotaDto() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getMake() {
        return Make;
    }

    public void setMake(String make) {
        Make = make;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public BigInteger getTravelledDistance() {
        return TravelledDistance;
    }

    public void setTravelledDistance(BigInteger travelledDistance) {
        TravelledDistance = travelledDistance;
    }
}
