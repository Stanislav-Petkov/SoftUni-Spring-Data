package com.example.demo.models.dtos;

import com.google.gson.annotations.Expose;

import java.math.BigInteger;

public class CarSeedDto {
    @Expose
    private String make;

    @Expose
    private String model;

    @Expose
    private String travelledDistance;

    public CarSeedDto() {
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

    public String getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(String travelledDistance) {
        this.travelledDistance = travelledDistance;
    }
}
