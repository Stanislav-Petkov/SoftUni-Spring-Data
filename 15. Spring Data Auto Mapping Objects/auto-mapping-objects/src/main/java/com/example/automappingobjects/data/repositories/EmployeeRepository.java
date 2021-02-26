package com.example.automappingobjects.data.repositories;

import com.example.automappingobjects.data.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // The database works with and Employee, not with EmployeeSeedDto
    Employee findByFirstNameAndLastName(String fn, String ln);
}
