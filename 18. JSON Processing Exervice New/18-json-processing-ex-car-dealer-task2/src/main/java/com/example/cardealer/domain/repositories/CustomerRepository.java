package com.example.cardealer.domain.repositories;

import com.example.cardealer.domain.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Set<Customer> getAllByOrderByBirthDateAscYoungDriverAsc();
//    Set<Customer> findAllByOrderByBirthDateAscYoungDriverDesc();
}
