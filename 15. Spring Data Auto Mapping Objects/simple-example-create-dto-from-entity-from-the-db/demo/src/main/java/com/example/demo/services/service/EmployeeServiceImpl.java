package com.example.demo.services.service;

import com.example.demo.data.entities.Employee;
import com.example.demo.data.repositories.EmployeeRepository;
import com.example.demo.services.dtos.EmployeeSeedDto;
import com.example.demo.services.dtos.EmployeeViewDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(ModelMapper modelMapper, EmployeeRepository employeeRepository) {
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void save(EmployeeSeedDto employeeSeedDto) {
        // We receive a dto, then we map it to an Employee entity and
        // save the Employee o the database
        Employee employee = this.modelMapper.map(employeeSeedDto, Employee.class);
        int b;
        this.employeeRepository.save(employee);
    }

    // In the method we receive names and we return Employee dto to the view
    @Override
    public EmployeeViewDto findByFnAndLn(String fn, String ln) {
        // Get an employee from the database
        Employee employee = this.employeeRepository
                .findByFirstNameAndLastName(fn, ln);
        // map the employee to employee dto and return it to the view
        EmployeeViewDto employeeViewDto =
                this.modelMapper.map(employee,EmployeeViewDto.class);
        return employeeViewDto;
    }
}























