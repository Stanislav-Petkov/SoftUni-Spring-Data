package com.example.demo.services;

import com.example.demo.models.dtos.ex6query6.SaleWithAppliedDiscountDto;

import java.util.List;

public interface SaleService {
    void seedSales();
    List<SaleWithAppliedDiscountDto> findAllSalesWithCarCustomerAndPriceWithAndWithoutDiscount();
}
