package com.example.demo.services.impl;

import com.example.demo.models.dtos.CustomerSeedDto;
import com.example.demo.models.dtos.ex6Query5.SalesByCustomerDto;
import com.example.demo.models.dtos.ex6query1.OrderedCustomerDto;
import com.example.demo.models.dtos.ex6query1.SalesDtoOfOrderedCustomer;
import com.example.demo.models.entitites.Customer;
import com.example.demo.models.entitites.Sale;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.services.CustomerService;
import com.example.demo.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCustomers(CustomerSeedDto[] customerServiceDtos) {
        if (this.customerRepository.count() != 0) {
            return;
        }

        Arrays.stream(customerServiceDtos)
                .forEach(customerSeedDto -> {
                    if (this.validationUtil.isValid(customerSeedDto)) {
                        Customer customer = this.modelMapper
                                .map(customerSeedDto, Customer.class);
//                        Customer customer = new Customer();
//                        String name = customerSeedDto.getName();
//                        LocalDateTime birthDate = customerSeedDto.getBirthDate();
//                        LocalDateTime localDateTime = new LocalDateTime();


                        System.out.println();
                        this.customerRepository.saveAndFlush(customer);
                    } else {
                        this.validationUtil.violations(customerSeedDto)
                                .stream()
                                .map(ConstraintViolation::getMessage)
                                .forEach(System.out::println);
                    }
                });
    }

    @Override
    public Customer getRandomCustomer() {
        Random random = new Random();
        int randomId = random.nextInt((int) this.customerRepository.count()) + 1;
        Customer customer = this.customerRepository.getOne((long) randomId);
        return customer;
    }

    @Override
    public List<OrderedCustomerDto> getOrderedCustomers() {
        return this.customerRepository
                .findAllByOrderByBirthDateAsc()
                .stream()
                .map(customer -> {
                    OrderedCustomerDto orderedCustomerDto = this.modelMapper
                            .map(customer, OrderedCustomerDto.class);

                    Set<Sale> allCustomerSales = customer.getSales();
                    Set<SalesDtoOfOrderedCustomer> salesDto = new HashSet<>();

                    // Add Set<SalesDtoOfOrderedCustomer> salesDto to each customerDto
                    for (Sale customerSale : allCustomerSales) {
                        SalesDtoOfOrderedCustomer saleDto =
                                this.modelMapper.map(customerSale, SalesDtoOfOrderedCustomer.class);
                        salesDto.add(saleDto);
                    }

                    orderedCustomerDto.setSales(salesDto);
                    System.out.println();

                    return orderedCustomerDto;
                }).collect(Collectors.toList());

    }

    @Override
    public List<SalesByCustomerDto> findAllCustomersWithNumberOfBoughtCarsAndSpentMoney() {

        List<Object[]> objectsList = this
                .customerRepository
                .findAllCustomersWithNumberOfBoughtCarsAndSpentMoney();

        List<SalesByCustomerDto> salesByCustomerDtos = new ArrayList<>();
        for (Object[] object : objectsList) {
            SalesByCustomerDto salesByCustomerDto = new SalesByCustomerDto();

            salesByCustomerDto.setFullName((String) object[0]);
            salesByCustomerDto.setBoughtCars((BigInteger) object[1]);
            salesByCustomerDto.setSpentMoney((BigDecimal) object[2]);
            System.out.println();
            salesByCustomerDtos.add(salesByCustomerDto);
        }
        System.out.println();
        return salesByCustomerDtos;


    }
}

