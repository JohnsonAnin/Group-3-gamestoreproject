package com.capstone.AninPringleOfori.dao;

import com.capstone.AninPringleOfori.model.item.Game;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GameDaoImplTest {

    Game game1;
    Game game2;
    @Autowired
    GameDao gameDao;

    @Before
    public void setUp() {
        tearDown();

        game1 = new Game();
        game1.setTitle("Forespoken");
        game1.setStudio("Square Enix");
        game1.setDescription("Action Adventure");
        game1.setPrice(69.99);
        game1.setEsrbRating("PG");
        game1.setOrderQuantity(400);

        game2 = new Game();
        game2.setTitle("Watch Dogs");
        game2.setStudio("Ubisoft");
        game2.setDescription("Open World");
        game2.setPrice(19.99);
        game2.setEsrbRating("M");
        game2.setOrderQuantity(300);
    }

    void populateGames() {
        game1 = gameDao.addGame(game1);
        game2 = gameDao.addGame(game2);
    }

    @After
    public void tearDown() {
        List<Game> allGames = gameDao.findAllGames();

        allGames.forEach(game -> gameDao.deleteGame(game.getId()));
    }


    @Test
    public void addedGameShouldExist() {
//        ACT
        game1 = gameDao.addGame(game1);

//        ASSERT
        assertEquals(game1, gameDao.findById(game1.getId()));
    }

    @Test
    public void deletedGameShouldNotExist() {
//        ARRANGE
        populateGames();

//        ACT
        gameDao.deleteGame(game1.getId());

//        ASSERT
        assertNull(gameDao.findById(game1.getId()));
    }

    @Test
    public void findAllGamesShouldReturnAllGames() {
//        ARRANGE
        populateGames();

//        ACT
        final List<Game> allGames = gameDao.findAllGames();

//        ASSERT
        assertEquals(2, allGames.size());
    }


    @Test
    public void findByTitleShouldReturnGamesByTitle() {
//        ARRANGE
        populateGames();
        String title = game1.getTitle();

//        ACT
        final List<Game> results = gameDao.findGamesByTitle(title);


//        ASSERT
        results.forEach(game -> assertEquals(title, game.getTitle()));
    }


    @Test
    public void findByStudioShouldReturnGamesByStudio() {
//        ARRANGE
        populateGames();

//        ACT
        final List<Game> squareEnix = gameDao.findGamesByStudio("Square Enix");
        final List<Game> ubisoftGames = gameDao.findGamesByStudio("Ubisoft");
        final List<Game> santaMonicaGames = gameDao.findGamesByStudio("Santa Monica"); //Should be empty

//        ASSERT
        assertEquals(1, squareEnix.size());
        assertEquals(1, ubisoftGames.size());
        assertEquals(0, santaMonicaGames.size());
    }

    @Test
    public void findByEsrbRatingShouldReturnGamesByEsrbRating() {
//        ARRANGE
        populateGames();

//        ACT
        final List<Game> matureGames = gameDao.findGamesByEsrbRating("M");
        final List<Game> pgGames = gameDao.findGamesByEsrbRating("PG");
        final List<Game> everyoneGames = gameDao.findGamesByEsrbRating("E"); //Should be empty


//        ASSERT
        assertEquals(1, matureGames.size());
        assertEquals(1, pgGames.size());
        assertEquals(0, everyoneGames.size());
    }


    @Test
    public void updatedGameShouldBeUpdated() {
//        ARRANGE
        populateGames();
        final String newStudio = "Naughty Dog";
//        ACT
        game1.setStudio(newStudio);
        gameDao.updateGame(game1);
        final Game updatedGame = gameDao.findById(game1.getId());

//        ASSERT
        assertEquals(game1, updatedGame);
    }

    @Test
    public void decrementQuantityShouldReduceQuantityBySpecifiedAmount() {
//        ARRANGE
        populateGames();
        final int decrementAmount = 3;
        final int initialQuantity = game1.getOrderQuantity();
//        ACT
        gameDao.decrementQuantity(game1, 3);
        Game updatedGame = gameDao.findById(game1.getId());

//        ASSERT
        assertEquals(initialQuantity - decrementAmount, updatedGame.getOrderQuantity());
    }
}
