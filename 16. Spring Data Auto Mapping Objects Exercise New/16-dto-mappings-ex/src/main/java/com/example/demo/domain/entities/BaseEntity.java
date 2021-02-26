package com.example.demo.domain.entities;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {

   // @Column(name = "id", unique = true, updatable = false)
    private Long id;

    public BaseEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
