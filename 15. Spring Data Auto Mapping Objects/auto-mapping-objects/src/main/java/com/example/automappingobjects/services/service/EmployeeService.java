package com.example.automappingobjects.services.service;

import com.example.automappingobjects.services.dto.EmployeeSeedDto;
import com.example.automappingobjects.services.dto.EmployeeViewDto;

public interface EmployeeService {
    void save(EmployeeSeedDto employeeSeedDto);

    // The service receives the entity from the database as a dto
    // When I want to send an object from the service to the web layer I return a dto
    // dto methodName();
    // When I want to save or delete from the database the method in the service receives a dto
    EmployeeViewDto findByFnAndLn(String fn, String ln);

}
