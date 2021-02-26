package com.example.demo.services;

import com.example.demo.models.dtos.CustomerSeedDto;
import com.example.demo.models.dtos.ex6Query5.SalesByCustomerDto;
import com.example.demo.models.dtos.ex6query1.OrderedCustomerDto;
import com.example.demo.models.entitites.Customer;

import java.util.List;

public interface CustomerService {
    void seedCustomers(CustomerSeedDto[] customerServiceDtos);
    Customer getRandomCustomer();

    //Ex_6_Query_1
    List<OrderedCustomerDto> getOrderedCustomers();

    //Ex_6_Query_5
    List<SalesByCustomerDto> findAllCustomersWithNumberOfBoughtCarsAndSpentMoney();
}
