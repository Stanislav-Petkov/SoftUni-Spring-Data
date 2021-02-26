package com.example.automappingobjects.services.service;

import com.example.automappingobjects.services.dto.UserLoginDto;
import com.example.automappingobjects.services.dto.UserSeedDto;

public interface UserService {

    void save(UserSeedDto userSeedDto);

    boolean login(UserLoginDto userLoginDto);
}
