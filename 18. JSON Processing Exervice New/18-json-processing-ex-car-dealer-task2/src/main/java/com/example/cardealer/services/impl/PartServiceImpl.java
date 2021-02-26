package com.example.cardealer.services.impl;

import com.example.cardealer.domain.dtos.seed.PartSeedDto;
import com.example.cardealer.domain.entities.Part;
import com.example.cardealer.domain.entities.Supplier;
import com.example.cardealer.domain.repositories.PartRepository;
import com.example.cardealer.domain.repositories.SupplierRepository;
import com.example.cardealer.services.PartService;
import com.example.cardealer.utils.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Random;

@Service
public class PartServiceImpl implements PartService {
    private static final String PARTS_PATH = "src/main/resources/jsons/parts.json";
    private final ValidationUtil validationUtil;
    private final PartRepository partRepository;
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public PartServiceImpl(ValidationUtil validationUtil, PartRepository partRepository, SupplierRepository supplierRepository,
                           ModelMapper modelMapper, Gson gson) {
        this.validationUtil = validationUtil;
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public void seedParts() throws Exception {
        String value = String.join("", Files
                .readAllLines(Path.of(PARTS_PATH)));
        PartSeedDto[] partSeedDtos = this.gson.fromJson(value, PartSeedDto[].class);
        for (PartSeedDto partSeedDto : partSeedDtos) {
            if(this.validationUtil.isValid(partSeedDto)) {
                Part part = this.modelMapper.map(partSeedDto, Part.class);

                // !!! part.setSupplier(this.supplierRepository.findById(partSeedDto.getId()));

                part.setSupplier(getRandomSupplier());
                this.partRepository.saveAndFlush(part);

                // return "Successful";
            }else {
                //return "error"
            }
        }
    }

    private Supplier getRandomSupplier() throws Exception {
        Random random = new Random();
        long index = random.nextInt((int) supplierRepository.count()) + 1;
        Optional<Supplier> supplier = this.supplierRepository.findById(index);
        if(supplier.isPresent()){
            return supplier.get();
        }else {
            throw new Exception("Supplier don't exist");
        }
    }
}
