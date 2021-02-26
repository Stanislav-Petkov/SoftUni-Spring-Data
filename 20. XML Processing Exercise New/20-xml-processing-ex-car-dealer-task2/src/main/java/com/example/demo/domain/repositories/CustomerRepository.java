package com.example.demo.domain.repositories;

import com.example.demo.domain.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    //query 1
    @Query("SELECT c FROM Customer AS c ORDER BY c.birthDate ASC, c.youngDriver DESC")
    Set<Customer> findAllAndSort();


//    Set<Customer> getAllByOrderByBirthDateAscYoungDriverAsc();
//    Set<Customer> findAllByOrderByBirthDateAscYoungDriverDesc();
}
