package com.example.demo.services;

import com.example.demo.models.dtos.CarSeedDto;
import com.example.demo.models.dtos.ex6query2.CarMakeToyotaDto;
import com.example.demo.models.dtos.ex6query4.CarPartsDto;
import com.example.demo.models.entitites.Car;

import java.util.List;

public interface CarService {
    void seedCars(CarSeedDto[] carSeedDtos);
    public void getACar();
    Car getRandomCar();

    //Ex_6_Query_2
    List<CarMakeToyotaDto> getAllToyotaCars();

    //Ex_6_Query_4
    List<CarPartsDto> findAllCarPartsDto();
}
