package com.example.cardealer.domain.dtos;

import com.google.gson.annotations.Expose;

import java.time.LocalDateTime;
import java.util.Set;

public class CustomerExportDto {

    @Expose
    private long id;
    @Expose
    private String name;
    @Expose
    private String birthDate;
    @Expose
    private boolean isYoungDriver;
    @Expose
    private Set<SaleExportDto> sales;

    public CustomerExportDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    public Set<SaleExportDto> getSales() {
        return sales;
    }

    public void setSales(Set<SaleExportDto> sales) {
        this.sales = sales;
    }
}
