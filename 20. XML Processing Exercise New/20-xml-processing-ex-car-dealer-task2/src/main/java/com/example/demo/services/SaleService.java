package com.example.demo.services;

import javax.xml.bind.JAXBException;

public interface SaleService {

    void salesDiscount() throws JAXBException;
    void seedSales();
}
