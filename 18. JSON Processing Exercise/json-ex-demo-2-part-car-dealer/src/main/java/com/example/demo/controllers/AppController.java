package com.example.demo.controllers;

import com.example.demo.constants.GlobalConstants;
import com.example.demo.models.dtos.CarSeedDto;
import com.example.demo.models.dtos.CustomerSeedDto;
import com.example.demo.models.dtos.PartSeedDto;
import com.example.demo.models.dtos.SupplierSeedDto;
import com.example.demo.models.dtos.ex6Query5.SalesByCustomerDto;
import com.example.demo.models.dtos.ex6query1.OrderedCustomerDto;
import com.example.demo.models.dtos.ex6query2.CarMakeToyotaDto;
import com.example.demo.models.dtos.ex6query3.NotImportingSupplierDto;
import com.example.demo.models.dtos.ex6query4.CarPartsDto;
import com.example.demo.models.dtos.ex6query6.SaleWithAppliedDiscountDto;
import com.example.demo.models.entitites.Customer;
import com.example.demo.services.*;
import com.example.demo.utils.FileIOUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static com.example.demo.constants.GlobalConstants.*;

@Component
public class AppController implements CommandLineRunner {
    private final Gson gson;
    private final FileIOUtil fileIOUtil;
    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;

    @Autowired
    public AppController(Gson gson, FileIOUtil fileIOUtil
            , SupplierService supplierService, PartService partService, CarService carService,
                         CustomerService customerService, SaleService saleService) {
        this.gson = gson;
        this.fileIOUtil = fileIOUtil;
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedSuppliers();
        this.seedParts();
        this.seedCars();
        this.seedCustomers();
        this.seedSales();
        this.writeOrderedCustomers();
//        this.writeToyotaCars();
//        this.writeSuppliersNotImporting();
//        this.writeCarsAndParts();
//        this.writeCustomerNameBoughtCarsAndSpentMoney();
//        this.writeSalesWithAppliedDiscount();
    }

    //EX_6_QUERY_6
    private void writeSalesWithAppliedDiscount() throws IOException {
        List<SaleWithAppliedDiscountDto> allSalesWithAndWithoutDiscount =
                this.saleService.findAllSalesWithCarCustomerAndPriceWithAndWithoutDiscount();
        System.out.println();
        String json = this.gson.toJson(allSalesWithAndWithoutDiscount);
        this.fileIOUtil.write(json, EX_6_QUERY_6_SALES_WITH_DISCOUNT);

    }

    //EX_6_QUERY_5
    private void writeCustomerNameBoughtCarsAndSpentMoney() throws IOException {
        List<SalesByCustomerDto> salesByCustomerDtos =
                this.customerService.findAllCustomersWithNumberOfBoughtCarsAndSpentMoney();

        System.out.println();
        String json = this.gson.toJson(salesByCustomerDtos);
        this.fileIOUtil.write(json, EX_6_QUERY_5_CUSTOMERS_TOTAL_SALES);
    }

    //EX_6_QUERY_4
    private void writeCarsAndParts() throws IOException {
        List<CarPartsDto> allCarPartsDto = this.carService
                .findAllCarPartsDto();
        String json = this.gson.toJson(allCarPartsDto);
        this.fileIOUtil.write(json,EX_6_QUERY_4_CARS_PARTS);
    }



    //EX_6_QUERY_3
    private void writeSuppliersNotImporting() throws IOException {
        List<NotImportingSupplierDto> notImportingSuppliers = this
                .supplierService.getNotImportingSuppliers();
        String json = this.gson.toJson(notImportingSuppliers);
        this.fileIOUtil.write(json, EX_6_QUERY_3_LOCAL_SUPPLIERS);
    }

    //EX_6_QUERY_2
    private void writeToyotaCars() throws IOException {
        List<CarMakeToyotaDto> allToyotaCars = this.carService.getAllToyotaCars();
        String json = this.gson.toJson(allToyotaCars);
        this.fileIOUtil.write(json, EX_6_QUERY_2_TOYOTA_CARS);
    }

    //EX_6_QUERY_1
    private void writeOrderedCustomers() throws IOException {
        List<OrderedCustomerDto> orderedCustomers = this.customerService.getOrderedCustomers();

        String content = this.gson.toJson(orderedCustomers);
        this.fileIOUtil.write(content, EX_6_QUERY_1_ORDERED_CUSTOMERS);
    }

    private void seedSales() {
        this.saleService.seedSales();

    }

    private void seedCustomers() throws FileNotFoundException {
        CustomerSeedDto[] customerSeedDtos = this.gson
                .fromJson(new FileReader(CUSTOMER_FILE_PATH), CustomerSeedDto[].class);

        System.out.println();
        this.customerService.seedCustomers(customerSeedDtos);
    }

    private void setCarsSetToEachPart() {
//        this.partService.setCarsSetToEachPart();
    }

    private void seedCars() throws FileNotFoundException {
        CarSeedDto[] carSeedDtos = this.gson.fromJson(
                new FileReader(CAR_FILE_PATH), CarSeedDto[].class
        );

        System.out.println();
        this.carService.seedCars(carSeedDtos);
    }

    private void seedParts() throws FileNotFoundException {
        PartSeedDto[] partSeedDtos = this.gson.fromJson(
                new FileReader(PART_FILE_PATH), PartSeedDto[].class);

        this.partService.seedParts(partSeedDtos);
    }


    private void seedSuppliers() throws FileNotFoundException {

        SupplierSeedDto[] supplierSeedDtos = this.gson.fromJson(
                new FileReader(SUPPLIER_FILE_PATH), SupplierSeedDto[].class);
        this.supplierService.seedSuppliers(supplierSeedDtos);
    }
}
