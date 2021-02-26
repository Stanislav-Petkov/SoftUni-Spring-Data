package com.example.demo.services;

import com.example.demo.domain.dtos.*;
import com.example.demo.domain.entities.Game;
import com.example.demo.domain.entities.Role;
import com.example.demo.repositories.GameRepository;
import com.example.demo.utils.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    private final ValidatorUtil validatorUtil;
    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;
    //    private final UserService userService;
    private UserDto userDto;

    @Autowired
    public GameServiceImpl(ValidatorUtil validatorUtil,
                           GameRepository gameRepository,
                           ModelMapper modelMapper) {
        this.validatorUtil = validatorUtil;
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;

    }


    //Exercise 3 AddGame
    @Override
    public String addGame(AddGameDto addGameDto) {
        StringBuilder sb = new StringBuilder();
        if (this.userDto == null || this.userDto.getRole().equals(Role.USER)) {
            sb.append("Invalid logged in user.");
        } else if (this.validatorUtil.isValid(addGameDto)) {
            Game game = this.modelMapper.map(addGameDto, Game.class);
            this.gameRepository.saveAndFlush(game);
            sb.append(String.format("Added %s", game.getTitle()));

        } else {
            this.validatorUtil.violations(addGameDto)
                    .forEach(e -> sb.append(e.getMessage()).append(System.lineSeparator()));
        }
        return sb.toString();
    }

    @Override
    public void setLoggedUser(UserDto userDto) {
        this.userDto = userDto;
    }

    //Exercise 3 DeleteGame
    @Override
    public String deleteGame(DeleteGameDto deleteGameDto) {
        StringBuilder sb = new StringBuilder();
        if (this.userDto == null || this.userDto.getRole().equals(Role.USER)) {
            sb.append("Invalid logged in user.");
        } else {
            Optional<Game> game = this.gameRepository.findById(deleteGameDto.getId());

            if (game.isPresent()) {
                this.gameRepository.delete(game.get());
                sb.append(String.format("Game %s was deleted", game.get().getTitle()));
            } else {
                sb.append("Cannot find game");
            }
        }
        return sb.toString();


    }

    // Exercise 4 AllGames
    @Override
    public String printTitlesAndPriceForAllGames() {
        StringBuilder sb = new StringBuilder();
        List<AllGameViewDto> gameViewDtos = this.gameRepository.findAll()
                .stream()
                .map(g -> this.modelMapper.map(g, AllGameViewDto.class))
                .collect(Collectors.toList());

        gameViewDtos.forEach(g -> sb.append(String.format("%s %s%n",
                g.getTitle(), g.getPrice())));

        return sb.toString();
    }

    //Exercise 4 DetailsGame
    @Override
    public String printDetailsForAGame(GameViewDetailDto gameDto) {
        StringBuilder sb = new StringBuilder();
        Optional<Game> game = this.gameRepository.findByTitle(gameDto.getTitle());

        if (game.isPresent()) {
            GameViewDetailDto map = this.modelMapper.map(game.get(), GameViewDetailDto.class);
            sb.append(String.format(" Title: %s%n Price: %s%n Description: %s%n Release date: %s%n",
                    map.getTitle(), map.getPrice(), map.getDescription(), map.getReleaseDate()));
        } else {
            sb.append("Cannot find game.");
        }
        return sb.toString();
    }

    @Override
    public String getFirstGameName() {
        Optional<Game> firstGame = this.gameRepository.findById(Long.valueOf(1));
        String foundGameName = "";
        if(firstGame.isPresent()){
            foundGameName = firstGame.get().getTitle();
        }else {
            System.out.println("Enter a game in the games table");
        }
        return foundGameName;
    }

    @Override
    public Game getFirstGame() {
        Optional<Game> game = this.gameRepository.findById(Long.valueOf(1));
        return game.orElse(null);
    }
}
