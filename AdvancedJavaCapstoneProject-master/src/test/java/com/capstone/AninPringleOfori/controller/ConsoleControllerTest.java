package com.capstone.AninPringleOfori.controller;

import com.capstone.AninPringleOfori.dao.ConsoleDao;
import com.capstone.AninPringleOfori.model.item.Console;
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
@WebMvcTest(ConsoleController.class)
public class ConsoleControllerTest {

    @MockBean
    ConsoleDao consoleDao;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();
    final String token = "529de249-8f2d-46dd-b713-1012bacae8f8";

    Console console1;
    Console console2;
    Console console3;
    List<Console> consoles;

    @Before
    public void setUp() {
        console1 = new Console();
        console1.setManufacturer("Sony");
        console1.setMemoryAmount("825 gb");
        console1.setModel("PlayStation5");
        console1.setPrice(499);
        console1.setProcessor("AMD XYZ");
        console1.setOrderQuantity(230);

        console2 = new Console();
        console2.setManufacturer("Microsoft");
        console2.setMemoryAmount("1024 gb");
        console2.setModel("XBox One X");
        console2.setPrice(499);
        console2.setProcessor("AMD ABC");
        console2.setOrderQuantity(300);

        consoles = Arrays.asList(console1, console2);
    }


    @Test
    public void findByIdShouldReturnOkResponseAndConsole() throws Exception {
//        ARRANGE
        int id = 1;
        String expectedJsonResult = objectMapper.writeValueAsString(console1);
        when(consoleDao.findById(id)).thenReturn(console1);

//        ACT & ASSERT
        MvcResult result = mockMvc.perform(
                get("/console/id/" + id))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJsonResult))
                .andDo(print())
                .andReturn();
    }


    @Test
    public void findByManufacturerShouldReturnOkResponseAndConsoleWithManufacturer() throws Exception {
//        ARRANGE
        String manufacturer = console2.getManufacturer();
        List<Console> expectedConsoleResult = Collections.singletonList(console2);
        when(consoleDao.findConsolesByManufacturer(manufacturer)).thenReturn(expectedConsoleResult);

        String expectedResultJson = objectMapper.writeValueAsString(expectedConsoleResult);

//        ACT & ASSERT
        MvcResult result = mockMvc.perform(
                get("/console/manufacturer/" + manufacturer))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResultJson))
                .andDo(print())
                .andReturn();
    }


    @Test
    public void findAllConsolesShouldReturnOKResponseAndListOfConsoles() throws Exception {
        when(consoleDao.findAllConsoles()).thenReturn(consoles);

        String expectedConsoleListJson = objectMapper.writeValueAsString(consoles);

        MvcResult result = mockMvc.perform(
                get("/console/all"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedConsoleListJson))
                .andDo(print())
                .andReturn();
    }


    @Test
    public void addConsoleShouldReturnCreatedResponseAndNewConsole() throws Exception {
        when(consoleDao.addConsole(console1)).thenReturn(console1);

        String consoleJson = objectMapper.writeValueAsString(console1);

        MvcResult result = mockMvc.perform(
                post("/console/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(consoleJson))
                .andExpect(status().isCreated())
                .andExpect(content().json(consoleJson))
                .andDo(print())
                .andReturn();
    }


    @Test
    public void updateConsoleShouldReturnAcceptedResponse() throws Exception {
        console1.setManufacturer("Nintendo");

        String updatedConsoleJson = objectMapper.writeValueAsString(console1);

        mockMvc.perform(
                post("/console/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedConsoleJson))
                .andExpect(status().isAccepted())
                .andDo(print());
    }


    @Test
    public void deleteConsoleShouldReturnNoContentResponse() throws Exception {
        mockMvc.perform(
                delete("/console/delete/1"))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}
