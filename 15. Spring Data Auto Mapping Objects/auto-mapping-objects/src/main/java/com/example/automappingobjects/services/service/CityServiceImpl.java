package com.example.automappingobjects.services.service;

import com.example.automappingobjects.data.entities.City;
import com.example.automappingobjects.data.repositories.CityRepository;
import com.example.automappingobjects.services.dto.CitySeedDto;
import com.example.automappingobjects.services.dto.CityViewDto;
import com.example.automappingobjects.services.dto.EmployeeViewDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, ModelMapper modelMapper) {
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(CitySeedDto city) {
        this.cityRepository.save(this.modelMapper.map(city, City.class));
    }

    //Map Collections
    @Override
    public CityViewDto findByName(String name) {
        City city = this.cityRepository.findByName(name);
        CityViewDto cityViewDto = this.modelMapper.map(city, CityViewDto.class);
        // Get the Employee Set from the city and for each Employee to be mapped to EmployeeViewDto
        // Then collect all EmployeeViewDtos to Set in the cityViewDto
        cityViewDto.setEmployeeViewDtoSet(city.getEmployees()
                .stream()
                .map(e -> this.modelMapper.map(e, EmployeeViewDto.class))
                .collect(Collectors.toSet()));
        return cityViewDto;
    }

//    @Override
//    public CityDto findByName(String name) {
//        return this.modelMapper.map(this.cityRepository.findByName(name), CityDto.class);
//    }
}
