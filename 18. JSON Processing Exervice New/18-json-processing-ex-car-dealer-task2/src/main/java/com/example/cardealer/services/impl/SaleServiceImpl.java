package com.example.cardealer.services.impl;

import com.example.cardealer.domain.entities.Car;
import com.example.cardealer.domain.entities.Customer;
import com.example.cardealer.domain.entities.Sale;
import com.example.cardealer.domain.repositories.CarRepository;
import com.example.cardealer.domain.repositories.CustomerRepository;
import com.example.cardealer.domain.repositories.SaleRepository;
import com.example.cardealer.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, CarRepository carRepository, CustomerRepository customerRepository) {
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void seedSales() {
        Sale sale = new Sale();
        sale.setCar(getRandomCar());
        sale.setCustomer(getRandomCustomer());
        sale.setDiscount(5);

        Sale sale1 = new Sale();
        sale1.setCar(getRandomCar());
        sale1.setCustomer(getRandomCustomer());
        sale1.setDiscount(10);

        Sale sale2 = new Sale();
        sale2.setCar(getRandomCar());
        sale2.setCustomer(getRandomCustomer());
        sale2.setDiscount(30);

        this.saleRepository.saveAndFlush(sale);
        this.saleRepository.saveAndFlush(sale1);
        this.saleRepository.saveAndFlush(sale2);
    }

    private Customer getRandomCustomer() {
        Random random = new Random();
        long index = random.nextInt((int) this.customerRepository.count()) + 1;
        return this.customerRepository.findById(index).get();
    }

    private Car getRandomCar() {
        Random random = new Random();
        long index = random.nextInt((int) this.carRepository.count()) + 1;
        return this.carRepository.findById(index).get();
    }
}
