package com.example.demo.service.impl;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Every business method like register is in a separate transaction which is committed when the
// method execution has finished, if there is no Exception there is a commit
// if there is a Runtime Exception there is a rollback
@Transactional
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepo;

    @Autowired
    public UserServiceImpl(UserRepository userRepo){
        this.userRepo = userRepo;
    }
    @Override
    public User register(User user) {
        return userRepo.save(user);
    }
}
