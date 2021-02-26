package com.example.demo.services.impl;

import com.example.demo.models.dtos.ex6query6.CarMakeModelTravelDistanceDto;
import com.example.demo.models.dtos.ex6query6.SaleWithAppliedDiscountDto;
import com.example.demo.models.entitites.Car;
import com.example.demo.models.entitites.Customer;
import com.example.demo.models.entitites.Part;
import com.example.demo.models.entitites.Sale;
import com.example.demo.repositories.SaleRepository;
import com.example.demo.services.CarService;
import com.example.demo.services.CustomerService;
import com.example.demo.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleRepository saleRepository;

    @Autowired
    public SaleServiceImpl(CarService carService, CustomerService customerService, SaleRepository saleRepository) {
        this.carService = carService;
        this.customerService = customerService;
        this.saleRepository = saleRepository;
    }

    @Override
    public void seedSales() {
        for (int i = 0; i < 10; i++) {

            Customer customer = this.customerService.getRandomCustomer();

            // Each Customer can have several cars from 1 to 5
            Random random = new Random();
            int randomNum = random.nextInt(5) + 1;
            for (int j = 0; j < randomNum; j++) {
                Sale sale = new Sale();
                Car randomCar = this.carService.getRandomCar();
                double randomDiscountDouble = getRandomDiscount();
                BigDecimal randomDiscount = new BigDecimal(randomDiscountDouble)
                        .setScale(2, RoundingMode.HALF_DOWN);

                if (customer.isYoungDriver()) {
                    randomDiscount = randomDiscount.add(new BigDecimal("0.05")
                            .setScale(2, RoundingMode.HALF_DOWN));
                }

                sale.setCustomer(customer);
                sale.setDiscount(randomDiscount);
                sale.setCar(randomCar);
                this.saleRepository.saveAndFlush(sale);
            }
        }
    }

    @Override
    public List<SaleWithAppliedDiscountDto> findAllSalesWithCarCustomerAndPriceWithAndWithoutDiscount() {
        List<Sale> allSales = this.saleRepository.findAll();

        List<SaleWithAppliedDiscountDto> salesWithAppliedDiscountDtos
                = new ArrayList<>();
        for (Sale sale : allSales) {
            CarMakeModelTravelDistanceDto car = new CarMakeModelTravelDistanceDto();
            car.setMake(sale.getCar().getMake());
            car.setModel(sale.getCar().getModel());
            car.setTravelledDistance(sale.getCar().getTravelledDistance());

            SaleWithAppliedDiscountDto saleWithAppliedDiscountDto
                    = new SaleWithAppliedDiscountDto();

            saleWithAppliedDiscountDto.setCustomerName(sale.getCustomer().getName());
            saleWithAppliedDiscountDto.setDiscount(sale.getDiscount());

            Set<Part> partsForACar = sale.getCar().getParts();
            List<BigDecimal> pricesForParts = new ArrayList<>();
            for (Part part : partsForACar) {
                pricesForParts.add(part.getPrice());
            }

            BigDecimal sum = BigDecimal.ZERO;
            for(BigDecimal price : pricesForParts){
                sum = sum.add(price);
            }
            saleWithAppliedDiscountDto.setPrice(sum);

            BigDecimal priceWithDiscount = BigDecimal.ZERO;
            priceWithDiscount = sum.subtract(sum.multiply(sale.getDiscount()));

            saleWithAppliedDiscountDto.setPriceWithDiscount(priceWithDiscount);

            // Create new dto and set the object

            saleWithAppliedDiscountDto.setCar(car);

            // Add the object to the list of dtos
            salesWithAppliedDiscountDtos.add(saleWithAppliedDiscountDto);
            System.out.println();
        }


        return salesWithAppliedDiscountDtos;
    }

    public double getRandomDiscount() {
        List<Double> givenList = Arrays.asList(0.0, 0.05, 0.1, 0.15, 0.2, 0.3, 0.4, 0.5);
        Random rand = new Random();
        double randomDiscount = givenList.get(rand.nextInt(givenList.size()));
        return randomDiscount;
    }
}
