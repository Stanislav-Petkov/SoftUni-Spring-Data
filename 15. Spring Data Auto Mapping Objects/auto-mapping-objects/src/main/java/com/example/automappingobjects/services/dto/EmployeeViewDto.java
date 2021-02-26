package com.example.automappingobjects.services.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EmployeeViewDto {
    private String firstName;
    private double salary;
    //this name is correct because Employee has a City, which has a name
    // so EmployeeViewDto should have 	=> cityName so that the mapping is automatic
    // with the modelMapper.map() method
    private String address;

}
