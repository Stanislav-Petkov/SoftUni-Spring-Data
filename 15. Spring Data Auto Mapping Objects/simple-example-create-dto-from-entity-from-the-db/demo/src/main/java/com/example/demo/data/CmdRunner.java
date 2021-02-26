package com.example.demo.data;

import com.example.demo.services.dtos.EmployeeSeedDto;
import com.example.demo.services.dtos.EmployeeViewDto;
import com.example.demo.services.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CmdRunner implements CommandLineRunner {

    private final EmployeeService employeeService;

    public CmdRunner(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create a new dto object
//        EmployeeSeedDto employeeSeedDto =
//                new EmployeeSeedDto("Ivan", "Draganov",1500,"Sofia");
//        // give the dto the service
//        employeeService.save(employeeSeedDto);

        EmployeeViewDto employeeViewDto =
                this.employeeService.findByFnAndLn("Ivan", "Draganov");

        System.out.println();
    }
}


























