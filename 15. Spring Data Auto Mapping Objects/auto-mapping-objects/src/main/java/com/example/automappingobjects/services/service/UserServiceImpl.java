package com.example.automappingobjects.services.service;

import com.example.automappingobjects.data.entities.User;
import com.example.automappingobjects.data.repositories.UserRepository;
import com.example.automappingobjects.services.dto.UserLoginDto;
import com.example.automappingobjects.services.dto.UserSeedDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public void save(UserSeedDto userSeedDto) {
        this.userRepository.save(this.modelMapper.map(userSeedDto, User.class));
    }

    @Override
    public boolean login(UserLoginDto userLoginDto) {
        User user = this.userRepository.findByUsernameAndPassword(userLoginDto.getUsername(),
                userLoginDto.getPassword()).orElse(null);
        return user != null;
    }
}
