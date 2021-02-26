package com.example.cardealer.services.impl;

import com.example.cardealer.domain.dtos.CarExportDto;
import com.example.cardealer.domain.dtos.seed.CarSeedDto;
import com.example.cardealer.domain.entities.Car;
import com.example.cardealer.domain.entities.Part;
import com.example.cardealer.domain.repositories.CarRepository;
import com.example.cardealer.domain.repositories.PartRepository;
import com.example.cardealer.services.CarService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class CarServiceImpl implements CarService {
    private static final String CARS_PATH = "src/main/resources/jsons/cars.json";
    private final PartRepository partRepository;
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public CarServiceImpl(PartRepository partRepository, CarRepository carRepository, ModelMapper modelMapper, Gson gson) {
        this.partRepository = partRepository;
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    @Transactional
    public void seedCars() throws Exception {
        String value = String.join("", Files.readAllLines(
                Path.of(CARS_PATH)
        ));
        CarSeedDto[] carSeedDtos = this.gson.fromJson(value,CarSeedDto[].class);

        for (CarSeedDto carSeedDto : carSeedDtos) {
            Car car = this.modelMapper.map(carSeedDto, Car.class);
            car.setParts(getRandomParts());
            this.carRepository.saveAndFlush(car);
        }
    }

    @Override
    public String findByToyota() {
        Set<Car> cars = this
                .carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc("Toyota");
        List<CarExportDto> carExportDtos = new ArrayList<>();
        for (Car car : cars) {
            CarExportDto carExportDto = this.modelMapper.map(car, CarExportDto.class);
            carExportDtos.add(carExportDto);
        }

        return this.gson.toJson(carExportDtos);
    }

    private Set<Part> getRandomParts() throws Exception {
        Set<Part> parts = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            Part part = this.getRandomPart();
            parts.add(part);
        }
        return parts;
    }

    private Part getRandomPart() throws Exception {
        Random random=new Random();
        long index = random.nextInt((int) this.partRepository.count()) + 1;
        Optional<Part> part = this.partRepository.findById(index);
        if(part.isPresent()){
            return part.get();
        }else {
            throw new Exception("Part was not found");
        }
    }
}
