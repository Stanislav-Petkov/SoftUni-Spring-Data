package com.example.demo.models.dtos.ex3query4;

import com.google.gson.annotations.Expose;

import java.util.List;
import java.util.Set;

public class SoldProductsWithCountDto {

    @Expose
    private Integer count;

    @Expose
    private Set<ProductNameAndPriceDto> products;

    public SoldProductsWithCountDto() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Set<ProductNameAndPriceDto> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductNameAndPriceDto> products) {
        this.products = products;
    }
}
