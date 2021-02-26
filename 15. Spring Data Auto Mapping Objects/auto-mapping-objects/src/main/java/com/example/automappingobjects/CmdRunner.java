package com.example.automappingobjects;

import com.example.automappingobjects.services.dto.*;
import com.example.automappingobjects.services.service.CityService;
import com.example.automappingobjects.services.service.EmployeeService;
import com.example.automappingobjects.services.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CmdRunner implements CommandLineRunner {

    //3 - WEB
    //2 - Service
    //1 - Data
    private final EmployeeService employeeService;
    private final UserService userService;
    private final CityService cityService;

    //It is a bad practice to call a repository here in the view layer

    @Autowired
    public CmdRunner(EmployeeService employeeService, UserService userService, CityService cityService) {
        this.employeeService = employeeService;
        this.userService = userService;
        this.cityService = cityService;
    }

    // run method start the application
    @Override
    public void run(String... args) throws Exception {
//        EmployeeSeedDto employeeSeedDto =
//                new EmployeeSeedDto("Ivan","Ivanov", 1500, "Sofia");
//        EmployeeSeedDto employeeSeedDto2 =
//                new EmployeeSeedDto("Pesho","Stamat", 2000, "Sofia");
//        EmployeeSeedDto employeeSeedDto3 =
//                new EmployeeSeedDto("Gosho","Ivanov", 560, "Sofia");
//        // employeeSeedDto is turned into Employee and it is saved to the database
//
//        this.employeeService.save(employeeSeedDto);
//        this.employeeService.save(employeeSeedDto2);
//        this.employeeService.save(employeeSeedDto3);

//        CityViewDto cityViewDto = this.cityService.findByName("Sofia");
//        UserSeedDto userSeedDto = new UserSeedDto("Joro","Iliev", 19, "joro@ab.bg");
//        this.userService.save(userSeedDto);
        UserLoginDto loginDto = new UserLoginDto("Jorooo","Iliev");
        boolean isLogged = this.userService.login(loginDto);
        if(isLogged){
            System.out.printf("%s is logged successfully ", loginDto.getUsername());
        }else {
            System.out.printf("%s no such user",loginDto.getUsername());
        }

    }

//    void saveEmployeeDtoAsEmployeeEntityToTheDatabase() {
//        EmployeeSeedDto employeeSeedDto =
//                new EmployeeSeedDto("Ivan", "Draganov", 1500, "Sofia");
//        employeeService.save(employeeSeedDto);
//    }
//
//    void findEmployeeDtoFromTheDatabase() {
//        EmployeeViewDto employeeViewDto = this.employeeService.findByFnAndLn("Ivan", "Draganov");
//        System.out.println();
//    }
}
