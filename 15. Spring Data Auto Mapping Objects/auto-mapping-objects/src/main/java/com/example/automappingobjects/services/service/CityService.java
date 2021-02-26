package com.example.automappingobjects.services.service;

import com.example.automappingobjects.services.dto.CityDto;
import com.example.automappingobjects.services.dto.CitySeedDto;
import com.example.automappingobjects.services.dto.CityViewDto;

public interface CityService {
    void save(CitySeedDto city);

//    CityDto findByName(String name);
    CityViewDto findByName(String name);
}
