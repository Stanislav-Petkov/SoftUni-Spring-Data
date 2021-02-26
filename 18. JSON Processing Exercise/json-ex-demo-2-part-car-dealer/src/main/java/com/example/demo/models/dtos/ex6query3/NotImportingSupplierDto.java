package com.example.demo.models.dtos.ex6query3;

import com.google.gson.annotations.Expose;

public class NotImportingSupplierDto {
    @Expose
    private long Id;
    @Expose
    private String Name;
    @Expose
    private int partsCount;

    public NotImportingSupplierDto() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPartsCount() {
        return partsCount;
    }

    public void setPartsCount(int partsCount) {
        this.partsCount = partsCount;
    }
}
