package com.example.demo.domain.dtos;

import com.example.demo.domain.entities.Game;
import com.example.demo.domain.entities.Role;

import java.util.Set;

public class UserDto {
    private String fullName;
    private String email;
    private Role role;
    private Set<Game> games;

    public UserDto() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }
}
