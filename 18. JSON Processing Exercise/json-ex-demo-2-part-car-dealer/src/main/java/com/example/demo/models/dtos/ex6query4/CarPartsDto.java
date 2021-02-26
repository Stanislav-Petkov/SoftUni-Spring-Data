package com.example.demo.models.dtos.ex6query4;

import com.google.gson.annotations.Expose;

import java.util.Set;

public class CarPartsDto {
    @Expose
    private CarDto car;
    @Expose
    private Set<PartsDto> parts;

    public CarPartsDto() {
    }

    public CarDto getCar() {
        return car;
    }

    public void setCar(CarDto car) {
        this.car = car;
    }

    public Set<PartsDto> getParts() {
        return parts;
    }

    public void setParts(Set<PartsDto> parts) {
        this.parts = parts;
    }
}
