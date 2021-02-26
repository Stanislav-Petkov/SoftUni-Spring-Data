package com.example.demo.services;

import com.example.demo.domain.dtos.UserDto;
import com.example.demo.domain.dtos.UserLoginDto;
import com.example.demo.domain.dtos.UserRegisterDto;
import com.example.demo.domain.entities.Game;
import com.example.demo.domain.entities.Role;
import com.example.demo.domain.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.utils.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final GameService gameService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;
    private UserDto loggedUser;

    public UserServiceImpl(GameService gameService, UserRepository userRepository,
                           ModelMapper modelMapper, ValidatorUtil validatorUtil) {
        this.gameService = gameService;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public String registerUser(UserRegisterDto userRegisterDto) {
        StringBuilder sb = new StringBuilder();
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            sb.append("Passwords don't match");
        } else if (this.validatorUtil.isValid(userRegisterDto)) {
            User user = modelMapper.map(userRegisterDto, User.class);

            if (this.userRepository.count() == 0) {
                user.setRole(Role.ADMIN);
            } else {
                user.setRole(Role.USER);
            }
            sb.append(String.format("%s was registered", userRegisterDto.getFullName()));
            this.userRepository.saveAndFlush(user);
        } else {
            this.validatorUtil.violations(userRegisterDto)
                    .stream()
                    .forEach(e -> sb.append(String.format("%s%n", e.getMessage())));
        }

        return sb.toString().trim();
    }

    @Override
    public String loginUser(UserLoginDto loginDto) {
        StringBuilder sb = new StringBuilder();
        Optional<User> user = this.userRepository
                .findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
        if(user.isPresent()){
            // We have found the user from the database
            if(this.loggedUser != null){
                sb.append("User is already logged in.");
            }else {
                this.loggedUser = this.modelMapper.map(user.get(), UserDto.class);
                this.gameService.setLoggedUser(this.loggedUser);
                sb.append(String.format("Successfully logged in %s",user.get().getFullName()));
            }

        }else {
            sb.append("Incorrect email / password");
        }

        return sb.toString();
    }

    @Override
    public String logout() {
        if(this.loggedUser == null){
            return "Cannot log out. No user was logged in.";
        }else {
            String message = String.format("User %s successfully logged out",
                    this.loggedUser.getFullName());
            this.loggedUser = null;
            return message;
        }
    }

    //Exercise 4 game purchase method
    // Step 2
    @Override
    public String getOwnedGames() {
        String firstGameName = this.gamePurchaseMethod();
        StringBuilder sb = new StringBuilder();
        Set<Game> games = this.loggedUser.getGames();
        games.forEach(g -> sb.append(g.getTitle()));
        return sb.toString();
    }

    // 1. User buys the first game from the gameService
    @Override
    public String gamePurchaseMethod() {
//        String firstGameName = this.gameService.getFirstGameName();
        Game firstGame = this.gameService.getFirstGame();

        // Set the user to the users of the game
        firstGame.getUsers().add(this.modelMapper.map(loggedUser,User.class));
        // Set the game to the games of the userDto
        String email = this.loggedUser.getEmail();
        User user = this.userRepository.findByEmail(email);
        // Set the game to the games of the user with the same email
        user.getGames().add(firstGame);
        this.loggedUser.getGames().add(firstGame);
        this.userRepository.saveAndFlush(user);
        return firstGame.getTitle();
    }
}
