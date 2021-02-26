package com.example.demo.repositories;

import com.example.demo.models.entitites.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Car findAllByIdEquals(long id);

    // Ex_6_Query_2
    //Get all cars from make Toyota and order them by
    //model alphabetically and then by travelled distance descending.
    List<Car> findAllByMakeEqualsOrderByModelAscTravelledDistanceDesc(String make);

    // Ex_6_Query_4
    //Get all cars along with their list of parts. For the car get only make, model
    // and travelled distance. For the parts get only the name and the price.
    List<Car> findAll();
}
