package com.example.cardealer.domain.repositories;

import com.example.cardealer.domain.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, Long> {
}
