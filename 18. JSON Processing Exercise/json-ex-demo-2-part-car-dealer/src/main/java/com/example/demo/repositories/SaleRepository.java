package com.example.demo.repositories;

import com.example.demo.models.entitites.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    //EX_6_QUERY_6
    List<Sale> findAll();
}
