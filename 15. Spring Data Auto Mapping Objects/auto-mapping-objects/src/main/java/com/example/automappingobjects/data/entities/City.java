package com.example.automappingobjects.data.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cities")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class City extends BaseEntity {

    @Column
    private String name;

    @OneToMany(mappedBy = "city", fetch = FetchType.EAGER)
    private Set<Employee> employees;
}
