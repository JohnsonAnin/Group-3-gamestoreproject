package com.capstone.AninPringleOfori.controller;

import com.capstone.AninPringleOfori.dao.GameDao;
import com.capstone.AninPringleOfori.model.item.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GameController {

    @Autowired
    GameDao gameDao;

    @RequestMapping(value = "/game/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Game createGame(@RequestBody @Valid Game game) {
        return gameDao.addGame(game);
    }


    @RequestMapping(value = "/game/id/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Game getGame(@PathVariable int id) {
        return gameDao.findById(id);
    }


    @RequestMapping(value = "/game/all", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getAllGames() {
        return gameDao.findAllGames();
    }


    @RequestMapping(value = "/game/title/{title}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGamesByTitle(@PathVariable String title) {
        System.out.println(title);
        return gameDao.findGamesByTitle(title);
    }


    @RequestMapping(value = "/game/esrb/{esrb}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGamesByEsrb(@PathVariable String esrb) {
        return gameDao.findGamesByEsrbRating(esrb);
    }

    @RequestMapping(value = "/game/studio/{studio}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGamesByStudio(@PathVariable String studio) {
        return gameDao.findGamesByStudio(studio);
    }

    @RequestMapping(value = "/game/update", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateGame(@RequestBody @Valid Game game) {
        gameDao.updateGame(game);
    }

    @RequestMapping(value = "/game/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable int id) {
        gameDao.deleteGame(id);
    }
}
