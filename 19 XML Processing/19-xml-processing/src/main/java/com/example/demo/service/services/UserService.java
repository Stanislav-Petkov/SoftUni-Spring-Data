package com.example.demo.service.services;

import com.example.demo.service.dtos.UserDto;
import com.example.demo.service.dtos.UserSeedDto;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.List;

public interface UserService {

    void save(UserSeedDto userSeedDto);

//    void seedUsersUserRootDtoOld() throws JAXBException, FileNotFoundException;

    void seedUsers() throws JAXBException, FileNotFoundException;

    List<UserDto> findAll();

    UserDto findById(long id);

    void exportUsers() throws JAXBException;
}
