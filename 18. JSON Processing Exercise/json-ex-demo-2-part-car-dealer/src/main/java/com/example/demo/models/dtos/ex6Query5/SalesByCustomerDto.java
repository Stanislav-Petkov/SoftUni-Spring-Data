package com.example.demo.models.dtos.ex6Query5;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.math.BigInteger;

public class SalesByCustomerDto {
    @Expose
    private String fullName;
    @Expose
    private BigInteger boughtCars;
    @Expose
    private BigDecimal spentMoney;

    public SalesByCustomerDto() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public BigInteger getBoughtCars() {
        return boughtCars;
    }

    public void setBoughtCars(BigInteger boughtCars) {
        this.boughtCars = boughtCars;
    }

    public BigDecimal getSpentMoney() {
        return spentMoney;
    }

    public void setSpentMoney(BigDecimal spentMoney) {
        this.spentMoney = spentMoney;
    }
}
