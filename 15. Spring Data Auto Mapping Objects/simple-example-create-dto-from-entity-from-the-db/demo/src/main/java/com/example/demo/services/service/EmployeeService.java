package com.example.demo.services.service;

import com.example.demo.services.dtos.EmployeeSeedDto;
import com.example.demo.services.dtos.EmployeeViewDto;

public interface EmployeeService {

    void save(EmployeeSeedDto employeeSeedDto);

    EmployeeViewDto findByFnAndLn(String fn, String ln);
}
