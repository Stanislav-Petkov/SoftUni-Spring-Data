package com.example.demo.services.impl;

import com.example.demo.models.dtos.SupplierSeedDto;
import com.example.demo.models.dtos.ex6query3.NotImportingSupplierDto;
import com.example.demo.models.entitites.Supplier;
import com.example.demo.repositories.SupplierRepository;
import com.example.demo.services.SupplierService;
import com.example.demo.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
//@Transactional
public class SupplierServiceImpl implements SupplierService {

    private final ModelMapper modelMapper;
    private final SupplierRepository repository;
    private final ValidationUtil validationUtil;
    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(ModelMapper modelMapper, SupplierRepository repository, ValidationUtil validationUtil, SupplierRepository supplierRepository) {
        this.modelMapper = modelMapper;
        this.repository = repository;
        this.validationUtil = validationUtil;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public void seedSuppliers(SupplierSeedDto[] supplierSeedDtos) {
        if (this.repository.count() != 0) {
            return;
        }

        Arrays.stream(supplierSeedDtos)
                .forEach(supplierSeedDto -> {
                    if (this.validationUtil.isValid(supplierSeedDto)) {
                        Supplier supplier = this.modelMapper.map(supplierSeedDto
                                , Supplier.class);

                        this.supplierRepository.saveAndFlush(supplier);
                    } else {
                        this.validationUtil
                                .violations(supplierSeedDto)
                                .stream()
                                .map(ConstraintViolation::getMessage)
                                .forEach(System.out::println);
                    }
                });
    }

    @Override
    public Supplier getRandomSupplier() {
        Random random = new Random();
        long randomSupplierId = random
                .nextInt((int) this.supplierRepository.count()) + 1;

        return this.repository.getOne(randomSupplierId);
    }

    //Ex_6_Query_3
    @Override
    public List<NotImportingSupplierDto> getNotImportingSuppliers() {
        return this.repository.findAllThatDoNotImport()
                .stream()
                .map(supplier -> {
                    NotImportingSupplierDto notImportingSupplierDto = this
                            .modelMapper.map(supplier, NotImportingSupplierDto.class);

                    int countOfParts = supplier.getParts().size();
                    notImportingSupplierDto.setPartsCount(countOfParts);

                    return notImportingSupplierDto;
                }).collect(Collectors.toList());
    }
}
