package com.example.cardealer.services;

import java.io.IOException;

public interface CarService {
    void seedCars() throws Exception;

    //Query 2
    String findByToyota();
}
