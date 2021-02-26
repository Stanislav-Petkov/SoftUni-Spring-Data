package com.example.demo.models.dtos.ex3query4;

import com.example.demo.models.dtos.ex3query2.SoldProductsDto;
import com.google.gson.annotations.Expose;

import java.util.List;
import java.util.Set;

public class UserDetailsDto {

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private Integer age;

    @Expose
    private Set<SoldProductsWithCountDto> soldProducts;

    public UserDetailsDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<SoldProductsWithCountDto> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(Set<SoldProductsWithCountDto> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
