package com.example.demo.services;

import javax.xml.bind.JAXBException;

public interface CarService {
    void seedCars() throws Exception;

    //Query 4
    void carsAndParts() throws JAXBException;

    //Query 2
//    String findByToyota();
}
