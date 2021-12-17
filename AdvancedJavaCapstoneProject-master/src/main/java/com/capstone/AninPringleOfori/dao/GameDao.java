package com.capstone.AninPringleOfori.dao;

import com.capstone.AninPringleOfori.model.item.Game;

import java.util.List;

public interface GameDao extends ItemDao {
    public Game addGame(Game game);
    public void deleteGame(int id);
    public Game findById(int id);
    public void updateGame(Game game);
    public List<Game> findAllGames();
    public List<Game> findGamesByStudio(String studio);
    public List<Game> findGamesByEsrbRating(String esrbRating);
    public List<Game> findGamesByTitle(String title);
}
