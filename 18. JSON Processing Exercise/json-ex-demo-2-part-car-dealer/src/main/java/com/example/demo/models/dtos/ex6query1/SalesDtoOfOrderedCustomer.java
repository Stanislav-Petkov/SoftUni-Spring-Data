package com.example.demo.models.dtos.ex6query1;

import com.example.demo.models.entitites.Car;
import com.example.demo.models.entitites.Customer;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class SalesDtoOfOrderedCustomer {
    @Expose
    private long Id;

    @Expose
    private BigDecimal Discount;

    @Expose
    private long CarId;

    @Expose
    private long CustomerId;

    public SalesDtoOfOrderedCustomer() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public BigDecimal getDiscount() {
        return Discount;
    }

    public void setDiscount(BigDecimal discount) {
        Discount = discount;
    }

    public long getCarId() {
        return CarId;
    }

    public void setCarId(long carId) {
        CarId = carId;
    }

    public long getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(long customerId) {
        CustomerId = customerId;
    }
}
