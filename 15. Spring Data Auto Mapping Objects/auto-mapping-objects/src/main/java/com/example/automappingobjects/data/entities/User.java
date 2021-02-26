package com.example.automappingobjects.data.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Data
public class User extends BaseEntity{
    private String username;
    private String password;
    private int age;
    private String email;
}
