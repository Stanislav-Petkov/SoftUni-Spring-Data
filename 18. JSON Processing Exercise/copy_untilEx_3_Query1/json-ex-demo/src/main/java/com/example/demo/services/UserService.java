package com.example.demo.services;

import com.example.demo.models.dtos.UserSeedDto;
import com.example.demo.models.entities.User;

public interface UserService {
    void seedUsers(UserSeedDto[] userSeedDtos);

    User getRandomUser();
}
