package com.example.demo.services.impl;

import com.example.demo.domain.dtos.imports.SupplierImportDto;
import com.example.demo.domain.dtos.imports.SupplierImportRootDto;
import com.example.demo.domain.entities.Supplier;
import com.example.demo.domain.repositories.SupplierRepository;
import com.example.demo.services.SupplierService;
import com.example.demo.utils.XmlParser;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {
    private static final String SUPPLIER_PATH = "src/main/resources/xmls/suppliers.xml";
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper, Gson gson, XmlParser xmlParser) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seedSupplier() throws IOException, JAXBException {
        //Read xml
        //xml -> dto
        //dto save db
        SupplierImportRootDto supplierImportRootDto = this.xmlParser.
                parseXml(SupplierImportRootDto.class, SUPPLIER_PATH);
        List<SupplierImportDto> suppliers = supplierImportRootDto.getSuppliers();
        for (SupplierImportDto supplier : suppliers) {
           this.supplierRepository.saveAndFlush(this.modelMapper.map(supplier, Supplier.class));
        }
    }
}





















