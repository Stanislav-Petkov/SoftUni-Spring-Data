package com.example.demo.data.repositories;

import com.example.demo.data.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // We can return an Employee entity from the database only
    // The database works with Employee and not with EmployeeSeedDto
    Employee findByFirstNameAndLastName(String fn, String ln);
}
