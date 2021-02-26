package com.example.automappingobjects.services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSeedDto {
    private String username;
    private String password;
    private int age;
    private String email;
}
