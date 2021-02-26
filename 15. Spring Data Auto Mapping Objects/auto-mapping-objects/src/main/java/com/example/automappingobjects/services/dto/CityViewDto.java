package com.example.automappingobjects.services.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class CityViewDto {

    private String name;
    private Set<EmployeeViewDto> employeeViewDtoSet;
}
