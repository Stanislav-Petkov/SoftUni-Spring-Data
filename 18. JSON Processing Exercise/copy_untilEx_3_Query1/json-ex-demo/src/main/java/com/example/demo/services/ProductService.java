package com.example.demo.services;

import com.example.demo.models.dtos.ProductInRangeDto;
import com.example.demo.models.dtos.ProductSeedDto;

import java.util.List;

public interface ProductService {
    void seedProducts(ProductSeedDto[] productSeedDtos);

    // Ex3_Query_1
    List<ProductInRangeDto> getAllProductsInRangeOrderByPrice();
}
