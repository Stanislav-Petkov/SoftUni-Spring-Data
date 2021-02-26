package com.example.automappingobjects.services.service;

import com.example.automappingobjects.data.entities.City;
import com.example.automappingobjects.data.entities.Employee;
import com.example.automappingobjects.data.repositories.EmployeeRepository;
import com.example.automappingobjects.services.dto.CityDto;
import com.example.automappingobjects.services.dto.EmployeeSeedDto;
import com.example.automappingobjects.services.dto.EmployeeViewDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    // They are private final because we want to have one instance from this object
    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;
    private final CityService cityService;

    @Autowired
    public EmployeeServiceImpl(ModelMapper modelMapper, EmployeeRepository employeeRepository, CityService cityService) {
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
        this.cityService = cityService;
    }

    // turn the EmployeeSeedDto into an Employee Entity
    @Override
    public void save(EmployeeSeedDto employeeSeedDto) {
        // fn, salary, city -> name

        Employee employee = this.modelMapper.map(employeeSeedDto, Employee.class);

//        CityDto city = this.cityService.findByName(employeeSeedDto.getAddressCity());
//        employee.setCity(this.modelMapper.map(city, City.class));
//        this.employeeRepository.save(employee);
    }

    // Turn the Employee from this.employeeRepository.findByFirstNameAndLastName(fn,ln)
    // to EmployeeViewDto
    @Override
    public EmployeeViewDto findByFnAndLn(String fn, String ln) {
        EmployeeViewDto employeeViewDto = this.modelMapper
                .map(this.employeeRepository.findByFirstNameAndLastName(fn,ln), EmployeeViewDto.class);

        return employeeViewDto;
    }
}























