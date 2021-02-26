package com.example.demo.models.dtos.ex3query1;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class ProductInRangeDto {

    @Expose
    private String name;

    @Expose
    private BigDecimal price;

    @Expose
    private String seller;

    public ProductInRangeDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
