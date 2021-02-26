package com.example.demo.domain.repositories;

import com.example.demo.domain.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, Long> {
}
