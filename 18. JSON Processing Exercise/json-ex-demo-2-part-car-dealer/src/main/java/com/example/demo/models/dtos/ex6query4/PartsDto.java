package com.example.demo.models.dtos.ex6query4;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class PartsDto {
    @Expose
    private String Name;
    @Expose
    private BigDecimal Price;

    public PartsDto() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public BigDecimal getPrice() {
        return Price;
    }

    public void setPrice(BigDecimal Price) {
        this.Price = Price;
    }
}
