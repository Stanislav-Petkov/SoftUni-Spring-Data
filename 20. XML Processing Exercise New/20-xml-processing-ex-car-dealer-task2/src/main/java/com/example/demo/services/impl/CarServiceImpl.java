package com.example.demo.services.impl;


import com.example.demo.domain.dtos.export.query4.CarExportDto;
import com.example.demo.domain.dtos.export.query4.CarExportRootDto;
import com.example.demo.domain.dtos.export.query4.PartExportDto;
import com.example.demo.domain.dtos.export.query4.PartExportRootDto;
import com.example.demo.domain.dtos.imports.CarImportDto;
import com.example.demo.domain.dtos.imports.CarImportRootDto;
import com.example.demo.domain.entities.Car;
import com.example.demo.domain.entities.Part;
import com.example.demo.domain.repositories.CarRepository;
import com.example.demo.domain.repositories.PartRepository;
import com.example.demo.services.CarService;
import com.example.demo.utils.XmlParser;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBException;
import java.util.*;

@Service
public class CarServiceImpl implements CarService {
    private static final String CARS_PATH = "src/main/resources/xmls/cars.xml";
    private static final String CARS_PARTS_PATH = "src/main/resources/xmls/exported/cars-and-parts.xml";


    private final PartRepository partRepository;
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final XmlParser xmlParser;

    @Autowired
    public CarServiceImpl(PartRepository partRepository, CarRepository carRepository, ModelMapper modelMapper, Gson gson, XmlParser xmlParser) {
        this.partRepository = partRepository;
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.xmlParser = xmlParser;
    }

    @Override
    @Transactional
    public void seedCars() throws Exception {
        CarImportRootDto carImportRootDto = this.xmlParser
                .parseXml(CarImportRootDto.class, CARS_PATH);
        List<CarImportDto> cars = carImportRootDto.getCars();
        for (CarImportDto carImportDto : cars) {
            Car car = this.modelMapper.map(carImportDto, Car.class);
            car.setParts(getRandomParts());
            this.carRepository.saveAndFlush(car);
        }

    }

    @Override
    public void carsAndParts() throws JAXBException {
        List<Car> all = this.carRepository.findAll();
        CarExportRootDto carRootDto = new CarExportRootDto();
        List<CarExportDto> carExportDtos = new ArrayList<>();

        for (Car car : all) {
            CarExportDto carExportDto = this.modelMapper.map(car,CarExportDto.class);
            //parts
            PartExportRootDto partRootDto = new PartExportRootDto();
            List<PartExportDto> partExportDtos = new ArrayList<>();
            for (Part part : car.getParts()) {
                PartExportDto partDto = this.modelMapper.map(part, PartExportDto.class);
                partExportDtos.add(partDto);
            }

            partRootDto.setParts(partExportDtos);
            carExportDto.setParts(partRootDto);
            carExportDtos.add(carExportDto);
        }
        carRootDto.setCars(carExportDtos);
        this.xmlParser.exportXml(carRootDto,CarExportRootDto.class,CARS_PARTS_PATH);
    }

//    @Override
//    public String findByToyota() {
//        Set<Car> cars = this
//                .carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc("Toyota");
//        List<CarExportDto> carExportDtos = new ArrayList<>();
//        for (Car car : cars) {
//            CarExportDto carExportDto = this.modelMapper.map(car, CarExportDto.class);
//            carExportDtos.add(carExportDto);
//        }
//
//        return this.gson.toJson(carExportDtos);
//    }

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
