package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id; // Only this property is included in hash code and equals

    @NonNull
    private String username;

    @NonNull
    private int age;

    @OneToMany(mappedBy = "user")
    private Set<Account> accounts = new HashSet<>();
}


























