package com.example.demo.services.impl;


import com.example.demo.domain.dtos.imports.PartImportDto;
import com.example.demo.domain.dtos.imports.PartImportRootDto;
import com.example.demo.domain.entities.Part;
import com.example.demo.domain.entities.Supplier;
import com.example.demo.domain.repositories.PartRepository;
import com.example.demo.domain.repositories.SupplierRepository;
import com.example.demo.services.PartService;
import com.example.demo.utils.ValidationUtil;
import com.example.demo.utils.XmlParser;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PartServiceImpl implements PartService {
    private static final String PARTS_PATH = "src/main/resources/xmls/parts.xml";
    private final ValidationUtil validationUtil;
    private final PartRepository partRepository;
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final XmlParser xmlParser;

    @Autowired
    public PartServiceImpl(ValidationUtil validationUtil, PartRepository partRepository, SupplierRepository supplierRepository,
                           ModelMapper modelMapper, Gson gson, XmlParser xmlParser) {
        this.validationUtil = validationUtil;
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seedParts() throws Exception {
        PartImportRootDto partImportRootDto = this.xmlParser
                .parseXml(PartImportRootDto.class, PARTS_PATH);
        List<PartImportDto> parts = partImportRootDto.getParts();
        for (PartImportDto part : parts) {
            Part map = this.modelMapper.map(part, Part.class);
            map.setSupplier(getRandomSupplier());
            this.partRepository.saveAndFlush(map);
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
