package com.capstone.AninPringleOfori.controller;

import com.capstone.AninPringleOfori.dao.GameDao;
import com.capstone.AninPringleOfori.model.item.Game;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
public class GameControllerTest {

    @MockBean
    GameDao gameDao;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();
    final String token = "529de249-8f2d-46dd-b713-1012bacae8f8";

    Game game1;
    Game game2;
    Game game3;
    List<Game> games;

    @Before
    public void setUp() throws Exception {
        game1 = new Game();
        game1.setId(1);
        game1.setTitle("Forespoken");
        game1.setStudio("Square Enix");
        game1.setDescription("Action Adventure");
        game1.setPrice(69.99);
        game1.setEsrbRating("PG");
        game1.setOrderQuantity(400);

        game2 = new Game();
        game2.setId(2);
        game2.setTitle("Watch Dogs");
        game2.setStudio("Ubisoft");
        game2.setDescription("Open World");
        game2.setPrice(19.99);
        game2.setEsrbRating("M");
        game2.setOrderQuantity(300);

        game3 = new Game();
        game3.setId(3);
        game3.setTitle("New Game");
        game3.setStudio("New Studio");
        game3.setDescription("Open World");
        game3.setPrice(19.49);
        game3.setEsrbRating("E");
        game3.setOrderQuantity(2999);

        games = Arrays.asList(game1, game2);
    }


    @Test
    public void findByIdShouldReturnOkResponseAndGame() throws Exception {
//        ARRANGE
        int id = 1;
        String expectedJsonResult = objectMapper.writeValueAsString(game1);
        when(gameDao.findById(id)).thenReturn(game1);

//        ACT & ASSERT
        MvcResult result = mockMvc.perform(
                get("/game/id/" + id))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJsonResult))
                .andDo(print())
                .andReturn();
    }


    @Test
    public void findByStudioShouldReturnOkResponseAndGameWithStudio() throws Exception {
//        ARRANGE
        String studio = game2.getStudio();
        List<Game> expectedGameResult = Collections.singletonList(game2);
        when(gameDao.findGamesByStudio(studio)).thenReturn(expectedGameResult);

        String expectedResultJson = objectMapper.writeValueAsString(expectedGameResult);

//        ACT & ASSERT
        MvcResult result = mockMvc.perform(
                get("/game/studio/" + studio))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResultJson))
                .andDo(print())
                .andReturn();
    }


    @Test
    public void findByTitleShouldReturnOkResponseAndGameWithTitle() throws Exception {
//        ARRANGE
        String title = game1.getTitle();
        List<Game> expectedGameResult = Collections.singletonList(game1);
        when(gameDao.findGamesByTitle(title)).thenReturn(expectedGameResult);

        String expectedResultJson = objectMapper.writeValueAsString(expectedGameResult);

//        ACT & ASSERT
        MvcResult result = mockMvc.perform(
                get("/game/title/" + title))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResultJson))
                .andDo(print())
                .andReturn();
    }


    @Test
    public void findByEsrbRatingShouldReturnOkResponseAndGameWithEsrbRating() throws Exception {
//        ARRANGE
        String esrb = game1.getEsrbRating();
        List<Game> expectedGameResult = Collections.singletonList(game1);
        when(gameDao.findGamesByEsrbRating(esrb)).thenReturn(expectedGameResult);

        String expectedResultJson = objectMapper.writeValueAsString(expectedGameResult);

//        ACT & ASSERT
        MvcResult result = mockMvc.perform(
                get("/game/esrb/" + esrb))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResultJson))
                .andDo(print())
                .andReturn();
    }


    @Test
    public void findAllGamesShouldReturnOKResponseAndListOfGames() throws Exception {
        when(gameDao.findAllGames()).thenReturn(games);

        String expectedGameListJson = objectMapper.writeValueAsString(games);

        MvcResult result = mockMvc.perform(
                get("/game/all"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedGameListJson))
                .andDo(print())
                .andReturn();
    }


    @Test
    public void addGameShouldReturnCreatedResponseAndNewGame() throws Exception {
        when(gameDao.addGame(game3)).thenReturn(game3);

        String gameJson = objectMapper.writeValueAsString(game3);

        MvcResult result = mockMvc.perform(
                post("/game/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gameJson))
                .andExpect(status().isCreated())
                .andExpect(content().json(gameJson))
                .andDo(print())
                .andReturn();
    }


    @Test
    public void updateGameShouldReturnAcceptedResponse() throws Exception {
        game1.setStudio("updated studio");

        String updatedGameJson = objectMapper.writeValueAsString(game1);

        mockMvc.perform(
                post("/game/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedGameJson))
                .andExpect(status().isAccepted())
                .andDo(print());
    }


    @Test
    public void deleteGameShouldReturnNoContentResponse() throws Exception {
        mockMvc.perform(
                delete("/game/delete/1"))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}
