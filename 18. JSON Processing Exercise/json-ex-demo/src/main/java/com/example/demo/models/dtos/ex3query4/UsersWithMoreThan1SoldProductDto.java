package com.example.demo.models.dtos.ex3query4;

import com.google.gson.annotations.Expose;

import java.util.Set;

public class UsersWithMoreThan1SoldProductDto {

    @Expose
    private Integer usersCount;

    @Expose
    private Set<UserDetailsDto> users;

    public UsersWithMoreThan1SoldProductDto() {
    }

    public Integer getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(Integer usersCount) {
        this.usersCount = usersCount;
    }

    public Set<UserDetailsDto> getUsers() {
        return users;
    }

    public void setUsers(Set<UserDetailsDto> users) {
        this.users = users;
    }
}
