package com.example.demo;

import com.example.demo.domain.dtos.*;
import com.example.demo.services.GameService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class Runner implements CommandLineRunner {
    private final UserService userService;
    private final GameService gameService;

    @Autowired
    public Runner(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }


    @Override
    public void run(String... args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String[] tokens = reader.readLine().split("\\|");
            switch (tokens[0]) {
                case "RegisterUser":
                    UserRegisterDto registerDto = new UserRegisterDto(tokens[1], tokens[2], tokens[3], tokens[4]);
                    System.out.println(this.userService.registerUser(registerDto));
                    break;
                case "LoginUser":
                    UserLoginDto loginDto = new UserLoginDto(tokens[1], tokens[2]);
                    System.out.println(this.userService.loginUser(loginDto));
                    break;
                case "Logout":
                    System.out.println(this.userService.logout());
                    break;
                case "AddGame":
                    LocalDate date = LocalDate.parse(tokens[7], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    AddGameDto addGameDto = new AddGameDto(tokens[1], new BigDecimal(tokens[2]),
                            Double.parseDouble(tokens[3]),tokens[4], tokens[5], tokens[6], date);
                    System.out.println(this.gameService.addGame(addGameDto));
                    break;
                case "DeleteGame":
                    DeleteGameDto deleteGameDto = new DeleteGameDto(Long.parseLong(tokens[1]));
                    System.out.println(this.gameService.deleteGame(deleteGameDto));
                    break;
                case "AllGames":
//                    GameViewDto allGamesDto = new GameViewDto();
                    System.out.println(this.gameService.printTitlesAndPriceForAllGames());
                    break;
                case "DetailGame":
                    GameViewDetailDto gameDto = new GameViewDetailDto(tokens[1]);
                    System.out.println(this.gameService.printDetailsForAGame(gameDto));
                    break;
                case "OwnedGames":
                    // Instruction, add 1 user in the users table, then add 1 game
                    // in the games table, Login with the user, automatically the user
                    // will buy the game with id 1, the change will be saved in the mapping
                    // table users_games and the name of the game will be printed
                    System.out.println(this.userService.getOwnedGames());
                    break;
            }
        }
    }
}
