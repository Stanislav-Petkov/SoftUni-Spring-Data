package com.example.demo.services;

import com.example.demo.models.dtos.PartSeedDto;
import com.example.demo.models.entitites.Part;

import java.util.List;
import java.util.Set;

public interface PartService {
    void seedParts(PartSeedDto[] partSeedDtos);
    List<Part> getRandomParts();
//    Set<Part> findAllByIdEquals(Long id) ;
}
