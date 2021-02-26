package com.example.demo.models.dtos.ex6query4;

import com.google.gson.annotations.Expose;

import java.math.BigInteger;

public class CarDto {
    @Expose
    private String Make;
    @Expose
    private String Model;
    @Expose
    private BigInteger TravelledDistance;

    public CarDto() {
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
