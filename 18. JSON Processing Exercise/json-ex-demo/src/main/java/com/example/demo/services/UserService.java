package com.example.demo.services;

import com.example.demo.models.dtos.UserSeedDto;
import com.example.demo.models.dtos.ex3query2.UserWithSoldProductListDto;
import com.example.demo.models.dtos.ex3query4.UsersWithMoreThan1SoldProductDto;
import com.example.demo.models.entities.User;

import java.util.List;

public interface UserService {
    void seedUsers(UserSeedDto[] userSeedDtos);

    User getRandomUser();

    //Ex3_Query_2
    List<UserWithSoldProductListDto> findAllUsersWithAtLeastOneSoldProduct();

    //Ex3_Query_4
    UsersWithMoreThan1SoldProductDto findAllUsersWithMoreThan1SoldProducts();
}
