package com.example.demo.repositories;

import com.example.demo.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
//    List<Product> findAllByPriceBetweenAndBuyerIsNull(BigDecimal lower, BigDecimal higher);

    //Ex_3_Query_1
    List<Product> findAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc
            (BigDecimal lower, BigDecimal higher);

}
