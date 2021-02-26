package com.example.demo.domain.dtos;

import javax.validation.constraints.Min;

public class DeleteGameDto {


    private Long id;

    public DeleteGameDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
