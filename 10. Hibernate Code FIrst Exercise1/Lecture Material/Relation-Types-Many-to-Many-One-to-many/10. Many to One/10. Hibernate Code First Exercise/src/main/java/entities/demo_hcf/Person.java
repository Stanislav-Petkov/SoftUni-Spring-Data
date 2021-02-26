package entities.demo_hcf;

import entities.BaseEntity;

import javax.persistence.*;


public abstract class Person extends BaseEntity {

    private String name;

    public Person(){

    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }
}
