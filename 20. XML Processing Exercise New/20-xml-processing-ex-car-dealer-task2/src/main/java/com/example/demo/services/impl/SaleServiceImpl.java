package com.example.demo.services.impl;

import com.example.demo.domain.dtos.export.query6.SaleExportDto;
import com.example.demo.domain.dtos.export.query6.SaleExportRootDto;
import com.example.demo.domain.entities.Car;
import com.example.demo.domain.entities.Customer;
import com.example.demo.domain.entities.Sale;
import com.example.demo.domain.repositories.CarRepository;
import com.example.demo.domain.repositories.CustomerRepository;
import com.example.demo.domain.repositories.SaleRepository;
import com.example.demo.services.SaleService;
import com.example.demo.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class SaleServiceImpl implements SaleService {
    private final String SALE_DISCOUNT_PATH = "src/main/resources/xmls/exported/sales-discounts.xml";

    private final SaleRepository saleRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, CarRepository carRepository, CustomerRepository customerRepository, ModelMapper modelMapper, XmlParser xmlParser) {
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public void salesDiscount() throws JAXBException {
        SaleExportRootDto saleExportRootDto = new SaleExportRootDto();
        List<SaleExportDto> saleExportDtos = new ArrayList<>();

        for(Sale sale : this.saleRepository.findAll()){
            SaleExportDto saleExportDto = this.modelMapper.map(sale, SaleExportDto.class);
            saleExportDto.setDiscount(saleExportDto.getDiscount() / 100);
            double totalPrice = sale.getCar().getParts().stream()
                    .mapToDouble(p -> Double.parseDouble(p.getPrice().toString()))
                    .sum();
            saleExportDto.setPrice(BigDecimal.valueOf(totalPrice));

            double priceWithDiscount = totalPrice - (totalPrice * sale.getDiscount() * 1.0 / 100);
            saleExportDto.setPriceWithDiscount(BigDecimal.valueOf(priceWithDiscount));
            saleExportDtos.add(saleExportDto);
        }
        saleExportRootDto.setSales(saleExportDtos);
        this.xmlParser.exportXml(saleExportRootDto,SaleExportRootDto.class,SALE_DISCOUNT_PATH);
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
