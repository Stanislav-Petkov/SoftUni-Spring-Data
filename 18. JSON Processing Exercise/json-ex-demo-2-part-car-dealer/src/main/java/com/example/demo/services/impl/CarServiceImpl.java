package com.example.demo.services.impl;

import com.example.demo.models.dtos.CarSeedDto;
import com.example.demo.models.dtos.ex6query2.CarMakeToyotaDto;
import com.example.demo.models.dtos.ex6query4.CarDto;
import com.example.demo.models.dtos.ex6query4.CarPartsDto;
import com.example.demo.models.dtos.ex6query4.PartsDto;
import com.example.demo.models.entitites.Car;
import com.example.demo.models.entitites.Part;
import com.example.demo.repositories.CarRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.services.CarService;
import com.example.demo.services.PartService;
import com.example.demo.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final PartService partService;

    @Autowired
    public CarServiceImpl(CarRepository carRepository,
                          ValidationUtil validationUtil,
                          ModelMapper modelMapper, PartService partService
                          ) {
        this.carRepository = carRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.partService = partService;

    }

    public void getACar(){
//        long id = 1;
//        Car car = this.carRepository.getOne(id);
//        Set<Part> parts = car.getParts();
//        System.out.println();

    }

    @Override
    public Car getRandomCar() {
        Random random = new Random();
        int i = random.nextInt((int) this.carRepository.count()) + 1;
        Car car = this.carRepository.getOne((long) i);

        return car;
    }

    @Override
    public List<CarMakeToyotaDto> getAllToyotaCars() {
        return this.carRepository
                .findAllByMakeEqualsOrderByModelAscTravelledDistanceDesc("Toyota")
                .stream()
                .map(car -> {
                    CarMakeToyotaDto carMakeToyotaDto = this.modelMapper
                            .map(car, CarMakeToyotaDto.class);

                    return carMakeToyotaDto;
                }).collect(Collectors.toList());
    }

    @Override
    public List<CarPartsDto> findAllCarPartsDto() {
        return this.carRepository
                .findAll()
                .stream()
                .map(car -> {
                    CarPartsDto carPartsDto = new CarPartsDto();
                    CarDto carDto = new CarDto();

                    carDto.setMake(car.getMake());
                    carDto.setModel(car.getModel());
                    carDto.setTravelledDistance(car.getTravelledDistance());

                    Set<PartsDto> partsDtos = new HashSet<>();
                    Set<Part> parts = car.getParts();
                    for (Part part : parts) {
                        PartsDto partsDto = this.modelMapper
                                .map(part, PartsDto.class);
                        partsDtos.add(partsDto);
                    }
                    carPartsDto.setCar(carDto);
                    carPartsDto.setParts(partsDtos);

                    return carPartsDto;
                }).collect(Collectors.toList());
    }

    @Override
    public void seedCars(CarSeedDto[] carSeedDtos) {
        if(this.carRepository.count() != 0){
            return;
        }

        Arrays.stream(carSeedDtos)
                .forEach(carSeedDto -> {
                    if(this.validationUtil.isValid(carSeedDto)){

                        Car car = this.modelMapper.map(carSeedDto,Car.class);
                        car.setParts(new HashSet<>(this.partService.getRandomParts()));

                        System.out.println();
                        this.carRepository.saveAndFlush(car);
                        System.out.println();
                    }else {

                        this.validationUtil.violations(carSeedDto)
                                .stream()
                                .map(ConstraintViolation::getMessage)
                                .forEach(System.out::println);
                    }

                });
    }
}
