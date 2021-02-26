package com.example.demo.services.impl;

import com.example.demo.models.dtos.UserSeedDto;
import com.example.demo.models.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import com.example.demo.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.Arrays;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository
            , ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedUsers(UserSeedDto[] userSeedDtos) {
        // First check if the users table does not already have content
        if(this.userRepository.count() != 0){
            return;
        }

        Arrays.stream(userSeedDtos)
                .forEach(userSeedDto -> {
                    if(this.validationUtil.isValid(userSeedDto)){
                        User user = this.modelMapper.map(userSeedDto, User.class);

                        this.userRepository.saveAndFlush(user);
                    }else {
                        this.validationUtil
                                .violations(userSeedDto)
                                .stream()
                                .map(ConstraintViolation::getMessage)
                                .forEach(System.out::println);

                    }
                });
    }

    @Override
    public User getRandomUser() {
        Random random = new Random();
        // nextInt(); returns a random int from 0 to repo.count() not including the last element
        // that is why we add 1 and the random number will be from 1 to the repo.count() including last
        long randomId =  random
                .nextInt((int) this.userRepository.count()) + 1;
        return this.userRepository.getOne(randomId);
    }
}
