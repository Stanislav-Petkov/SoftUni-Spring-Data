package com.example.demo.repositories;

import com.example.demo.models.entitites.Car;
import com.example.demo.models.entitites.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PartRepository extends JpaRepository<Part,Long> {

}
