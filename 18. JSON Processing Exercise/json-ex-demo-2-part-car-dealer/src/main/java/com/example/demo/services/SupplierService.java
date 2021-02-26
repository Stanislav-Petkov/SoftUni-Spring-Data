package com.example.demo.services;

import com.example.demo.models.dtos.SupplierSeedDto;
import com.example.demo.models.dtos.ex6query3.NotImportingSupplierDto;
import com.example.demo.models.entitites.Supplier;

import java.util.List;

public interface SupplierService {
    void seedSuppliers(SupplierSeedDto[] supplierSeedDto);
    Supplier getRandomSupplier();

    //Ex_6_Query_3
    List<NotImportingSupplierDto> getNotImportingSuppliers();
}
