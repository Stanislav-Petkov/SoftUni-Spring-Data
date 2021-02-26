package com.example.demo.services;

import com.example.demo.domain.dtos.AddGameDto;
import com.example.demo.domain.dtos.DeleteGameDto;
import com.example.demo.domain.dtos.GameViewDetailDto;
import com.example.demo.domain.dtos.UserDto;
import com.example.demo.domain.entities.Game;

public interface GameService {
    String addGame(AddGameDto addGameDto);

    void setLoggedUser(UserDto userDto);
    String deleteGame(DeleteGameDto deleteGameDto);
    String printTitlesAndPriceForAllGames();

    String printDetailsForAGame(GameViewDetailDto gameDto);

    String getFirstGameName();
    Game getFirstGame();
}
