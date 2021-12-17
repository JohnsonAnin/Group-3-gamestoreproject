package com.capstone.AninPringleOfori.controller;

import com.capstone.AninPringleOfori.dao.TShirtDao;
import com.capstone.AninPringleOfori.model.item.TShirt;
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
@WebMvcTest(TShirtController.class)
public class TShirtControllerTest {

    @MockBean
    TShirtDao tShirtDao;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();
    final String token = "529de249-8f2d-46dd-b713-1012bacae8f8";

    TShirt tShirt1;
    TShirt tShirt2;
    TShirt tShirt3;
    List<TShirt> tShirts;

    @Before
    public void setUp() {
        tShirt1 = new TShirt();
        tShirt1.setColor("green");
        tShirt1.setSize("M");
        tShirt1.setDescription("polo");
        tShirt1.setPrice(3.99);
        tShirt1.setOrderQuantity(230);

        tShirt2 = new TShirt();
        tShirt2.setColor("blue");
        tShirt2.setSize("S");
        tShirt2.setDescription("tank top");
        tShirt2.setPrice(1.99);
        tShirt2.setOrderQuantity(280);

        tShirts = Arrays.asList(tShirt1, tShirt2);
    }


    @Test
    public void findByIdShouldReturnOkResponseAndTShirt() throws Exception {
//        ARRANGE
        int id = 1;
        String expectedJsonResult = objectMapper.writeValueAsString(tShirt1);
        when(tShirtDao.findById(id)).thenReturn(tShirt1);

//        ACT & ASSERT
        MvcResult result = mockMvc.perform(
                get("/tshirt/id/" + id))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJsonResult))
                .andDo(print())
                .andReturn();
    }


    @Test
    public void findBySizeShouldReturnOkResponseAndTShirtWithSize() throws Exception {
//        ARRANGE
        String size = tShirt2.getSize();
        List<TShirt> expectedTShirtResult = Collections.singletonList(tShirt2);
        when(tShirtDao.findtShirtBySize(size)).thenReturn(expectedTShirtResult);

        String expectedResultJson = objectMapper.writeValueAsString(expectedTShirtResult);

//        ACT & ASSERT
        MvcResult result = mockMvc.perform(
                get("/tshirt/size/" + size))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResultJson))
                .andDo(print())
                .andReturn();
    }


    @Test
    public void findByColorShouldReturnOkResponseAndTShirtWithColor() throws Exception {
//        ARRANGE
        String color = tShirt2.getColor();
        List<TShirt> expectedTShirtResult = Collections.singletonList(tShirt2);
        when(tShirtDao.findtShirtByColor(color)).thenReturn(expectedTShirtResult);

        String expectedResultJson = objectMapper.writeValueAsString(expectedTShirtResult);

//        ACT & ASSERT
        MvcResult result = mockMvc.perform(
                get("/tshirt/color/" + color))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResultJson))
                .andDo(print())
                .andReturn();
    }


    @Test
    public void findAllTShirtsShouldReturnOKResponseAndListOfTShirts() throws Exception {
        when(tShirtDao.findAlltShirts()).thenReturn(tShirts);

        String expectedTShirtListJson = objectMapper.writeValueAsString(tShirts);

        MvcResult result = mockMvc.perform(
                get("/tshirt/all"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedTShirtListJson))
                .andDo(print())
                .andReturn();
    }


    @Test
    public void addTShirtShouldReturnCreatedResponseAndNewTShirt() throws Exception {
        when(tShirtDao.addtShirt(tShirt1)).thenReturn(tShirt1);

        String tShirtJson = objectMapper.writeValueAsString(tShirt1);

        MvcResult result = mockMvc.perform(
                post("/tshirt/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(tShirtJson))
                .andExpect(status().isCreated())
                .andExpect(content().json(tShirtJson))
                .andDo(print())
                .andReturn();
    }


    @Test
    public void updateTShirtShouldReturnAcceptedResponse() throws Exception {
        tShirt1.setColor("White");

        String updatedTShirtJson = objectMapper.writeValueAsString(tShirt1);

        mockMvc.perform(
                post("/tshirt/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedTShirtJson))
                .andExpect(status().isAccepted())
                .andDo(print());
    }


    @Test
    public void deleteTShirtShouldReturnNoContentResponse() throws Exception {
        mockMvc.perform(
                delete("/tshirt/delete/1"))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}
