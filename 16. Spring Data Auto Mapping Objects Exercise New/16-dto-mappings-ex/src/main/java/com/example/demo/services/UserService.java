package com.example.demo.services;

import com.example.demo.domain.dtos.UserLoginDto;
import com.example.demo.domain.dtos.UserRegisterDto;

public interface UserService {

    String registerUser(UserRegisterDto user);

    String loginUser(UserLoginDto loginDto);

    String logout();

    String gamePurchaseMethod();

        String getOwnedGames();
    //String getFirstGameName();
}
