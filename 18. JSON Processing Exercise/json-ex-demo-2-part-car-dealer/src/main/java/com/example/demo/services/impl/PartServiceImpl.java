package com.example.demo.services.impl;

import com.example.demo.models.dtos.PartSeedDto;
import com.example.demo.models.entitites.Part;
import com.example.demo.repositories.PartRepository;
import com.example.demo.services.PartService;
import com.example.demo.services.SupplierService;
import com.example.demo.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import java.util.*;

@Service
//@Transactional
public class PartServiceImpl implements PartService {
    private final ValidationUtil validationUtil;
    private final PartRepository partRepository;
    private final ModelMapper modelMapper;
    private final SupplierService supplierService;

    @Autowired
    public PartServiceImpl(ValidationUtil validationUtil, PartRepository partRepository, ModelMapper modelMapper, SupplierService supplierService) {
        this.validationUtil = validationUtil;
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
        this.supplierService = supplierService;
    }

    @Override
    public void seedParts(PartSeedDto[] partSeedDtos) {

            if(this.partRepository.count() != 0){
                return;
            }
        Arrays.stream(partSeedDtos).forEach(partSeedDto -> {
            if(this.validationUtil.isValid(partSeedDto)){
                Part part = this.modelMapper.map(partSeedDto,Part.class);

                System.out.println();

                //set random supplier
                part.setSupplier(this.supplierService.getRandomSupplier());
                //this.partRepository.saveAndFlush(part);
                System.out.println();
                this.partRepository.saveAndFlush(part);

                System.out.println();
            }else {
                this.validationUtil.violations(partSeedDto)
                        .stream()
                        .map(ConstraintViolation::getMessage)
                        .forEach(System.out::println);
            }

        });

    }

    @Override
    public List<Part> getRandomParts() {
        Random random = new Random();
        List<Part> randomParts = new ArrayList<>();
        int min = 10;
        int max = 20;

//        int randomNumBetween10And20 = random.nextInt(max - min + 1) + min;
        int randomCounter = random.nextInt(2) +1;
        for (int i = 0; i < randomCounter; i++) {
            long randomId = random
                    .nextInt((int) this.partRepository.count()) + 1;
            randomParts.add(this.partRepository.getOne(randomId));
        }

        return randomParts;
    }



}
