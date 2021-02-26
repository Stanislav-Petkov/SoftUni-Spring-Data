package com.example.demo.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    @Column(unique = true)
    private String email;
    private String fullName;
    private String password;
    @Enumerated(EnumType.ORDINAL)
    private Role role;
//    @OneToMany
//    private Set<Order> orders;
    @ManyToMany
    private Set<Game> games;

}






















