package com.example.demo.models.dtos.ex6query6;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class SaleWithAppliedDiscountDto {
    @Expose
    private CarMakeModelTravelDistanceDto car;
    @Expose
    private String customerName;
    @Expose
    private BigDecimal Discount;
    @Expose
    private BigDecimal price;
    @Expose
    private BigDecimal priceWithDiscount;

    public SaleWithAppliedDiscountDto() {
    }

    public CarMakeModelTravelDistanceDto getCar() {
        return car;
    }

    public void setCar(CarMakeModelTravelDistanceDto car) {
        this.car = car;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getDiscount() {
        return Discount;
    }

    public void setDiscount(BigDecimal discount) {
        Discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }
}
