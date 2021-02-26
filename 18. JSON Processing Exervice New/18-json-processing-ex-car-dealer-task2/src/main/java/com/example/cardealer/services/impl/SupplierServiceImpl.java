package com.example.cardealer.services.impl;

import com.example.cardealer.domain.dtos.seed.SupplierSeedDto;
import com.example.cardealer.domain.entities.Supplier;
import com.example.cardealer.domain.repositories.SupplierRepository;
import com.example.cardealer.services.SupplierService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class SupplierServiceImpl implements SupplierService {
    private static final String SUPPLIER_PATH = "src/main/resources/jsons/suppliers.json";
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper, Gson gson) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public void seedSupplier() throws IOException {
        //Read json
        //json -> dto
        //dto save db

        String content = String.join("", Files
                .readAllLines(Path.of(SUPPLIER_PATH)));
        SupplierSeedDto[] supplierSeedDtos = this
                .gson.fromJson(content, SupplierSeedDto[].class);

        for (SupplierSeedDto supplierSeedDto : supplierSeedDtos) {
            this.supplierRepository.saveAndFlush(this
                    .modelMapper.map(supplierSeedDto, Supplier.class));
        }
    }
}





















