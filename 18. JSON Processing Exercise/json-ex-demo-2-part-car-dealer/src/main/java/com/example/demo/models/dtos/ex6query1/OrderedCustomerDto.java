package com.example.demo.models.dtos.ex6query1;

import com.example.demo.models.entitites.Sale;
import com.google.gson.annotations.Expose;
import jdk.jfr.Enabled;

import java.time.LocalDateTime;
import java.util.Set;

public class OrderedCustomerDto {

    @Expose
    private long Id;

    @Expose
    private String Name;

    @Expose
    private LocalDateTime BirthDate;

    @Expose
    private boolean IsYoungDriver;

    @Expose
    private Set<SalesDtoOfOrderedCustomer> Sales;

    public OrderedCustomerDto() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public LocalDateTime getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        BirthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return IsYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        IsYoungDriver = youngDriver;
    }

    public Set<SalesDtoOfOrderedCustomer> getSales() {
        return Sales;
    }

    public void setSales(Set<SalesDtoOfOrderedCustomer> sales) {
        Sales = sales;
    }
}
