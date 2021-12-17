package com.capstone.AninPringleOfori.service;

import com.capstone.AninPringleOfori.dao.ConsoleDao;
import com.capstone.AninPringleOfori.dao.GameDao;
import com.capstone.AninPringleOfori.dao.TShirtDao;
import com.capstone.AninPringleOfori.model.item.Console;
import com.capstone.AninPringleOfori.model.item.Game;
import com.capstone.AninPringleOfori.model.item.TShirt;
import com.capstone.AninPringleOfori.model.order.Invoice;
import com.capstone.AninPringleOfori.view.OrderViewModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ServiceLayerTest {
    
    @Autowired
    ServiceLayer serviceLayer;
    
    @Autowired
    GameDao gameDao;
    
    @Autowired
    ConsoleDao consoleDao;
    
    @Autowired
    TShirtDao tShirtDao;
            
    
    TShirt tShirt;
    Console console;
    Game game;
    
    
    @Before
    public void setUp(){
        tearDown();
        tShirt = new TShirt();
        tShirt.setColor("green");
        tShirt.setSize("M");
        tShirt.setDescription("polo");
        tShirt.setPrice(3.99);
        tShirt.setOrderQuantity(230);

        console = new Console();
        console.setManufacturer("Sony");
        console.setMemoryAmount("825 gb");
        console.setModel("PlayStation5");
        console.setPrice(499);
        console.setProcessor("AMD XYZ");
        console.setOrderQuantity(230);

        game = new Game();
        game.setTitle("Forespoken");
        game.setStudio("Square Enix");
        game.setDescription("Action Adventure");
        game.setPrice(69.99);
        game.setEsrbRating("PG");
        game.setOrderQuantity(400);
    }

    void populateItems() {
        game = gameDao.addGame(game);
        console = consoleDao.addConsole(console);
        tShirt = tShirtDao.addtShirt(tShirt);
    }

    @After
    public void tearDown() {
        List<Game> allGames = gameDao.findAllGames();
        allGames.forEach(game -> gameDao.deleteGame(game.getId()));
    }
    
    
    @Test
    public void generateInvoiceShouldReturnTShirtInvoiceAndUpdateRemainingQuantity() {
//        ARRANGE
        populateItems();

        final String name = "Marcus Holloway";
        final String street = "2242 Bay Street";
        final String city = "Los Angeles";
        final String state = "CA";
        final String zipCode = "12832"; 
        Invoice expectedInvoice = new Invoice();
        expectedInvoice.setName(name);
        expectedInvoice.setStreet(street);
        expectedInvoice.setCity(city);
        expectedInvoice.setState(state);
        expectedInvoice.setZipCode(zipCode);
        expectedInvoice.setItemType("tshirt");
        expectedInvoice.setItemId(tShirt.getId());
        expectedInvoice.setUnitPrice(tShirt.getPrice());
        expectedInvoice.setOrderQuantity(3);
        expectedInvoice.setSubTotal(11.97);
        expectedInvoice.setTax(0.7182000000000001);
        expectedInvoice.setProcessingFee(1.98);
        expectedInvoice.setTotal(14.6682);
        
        OrderViewModel orderViewModel = new OrderViewModel();
        orderViewModel.setName(name);
        orderViewModel.setStreet(street);
        orderViewModel.setCity(city);
        orderViewModel.setState(state);
        orderViewModel.setZipCode(zipCode);
        orderViewModel.setItemType("tshirt");
        orderViewModel.setItem(tShirt);
        orderViewModel.setOrderQuantity(3);
        
//        ACT
        Invoice generatedInvoice = serviceLayer.generateInvoice(orderViewModel);

//        ASSERT
        assertEquals(expectedInvoice, generatedInvoice);
    }


    @Test
    public void generateInvoiceShouldReturnGameInvoiceAndUpdateRemainingQuantity() {
//        ARRANGE
        populateItems();

        final String name = "Marcus Holloway";
        final String street = "2242 Bay Street";
        final String city = "Los Angeles";
        final String state = "CA";
        final String zipCode = "12832";
        Invoice expectedInvoice = new Invoice();
        expectedInvoice.setName(name);
        expectedInvoice.setStreet(street);
        expectedInvoice.setCity(city);
        expectedInvoice.setState(state);
        expectedInvoice.setZipCode(zipCode);
        expectedInvoice.setItemType("game");
        expectedInvoice.setItemId(game.getId());
        expectedInvoice.setUnitPrice(game.getPrice());
        expectedInvoice.setOrderQuantity(3);
        expectedInvoice.setSubTotal(209.96999999999997);
        expectedInvoice.setTax(12.598199999999999);
        expectedInvoice.setProcessingFee(1.49);
        expectedInvoice.setTotal(224.05819999999997);

        OrderViewModel orderViewModel = new OrderViewModel();
        orderViewModel.setName(name);
        orderViewModel.setStreet(street);
        orderViewModel.setCity(city);
        orderViewModel.setState(state);
        orderViewModel.setZipCode(zipCode);
        orderViewModel.setItemType("game");
        orderViewModel.setItem(game);
        orderViewModel.setOrderQuantity(3);

//        ACT
        Invoice generatedInvoice = serviceLayer.generateInvoice(orderViewModel);

//        ASSERT
        assertEquals(expectedInvoice, generatedInvoice);
    }


    @Test
    public void generateInvoiceShouldReturnConsoleInvoiceAndUpdateRemainingQuantity() {
//        ARRANGE
        populateItems();

        final String name = "Marcus Holloway";
        final String street = "2242 Bay Street";
        final String city = "Los Angeles";
        final String state = "CA";
        final String zipCode = "12832";
        Invoice expectedInvoice = new Invoice();
        expectedInvoice.setName(name);
        expectedInvoice.setStreet(street);
        expectedInvoice.setCity(city);
        expectedInvoice.setState(state);
        expectedInvoice.setZipCode(zipCode);
        expectedInvoice.setItemType("console");
        expectedInvoice.setItemId(console.getId());
        expectedInvoice.setUnitPrice(console.getPrice());
        expectedInvoice.setOrderQuantity(3);
        expectedInvoice.setSubTotal(1497);
        expectedInvoice.setTax(89.82);
        expectedInvoice.setProcessingFee(14.99);
        expectedInvoice.setTotal(1601.81);

        OrderViewModel orderViewModel = new OrderViewModel();
        orderViewModel.setName(name);
        orderViewModel.setStreet(street);
        orderViewModel.setCity(city);
        orderViewModel.setState(state);
        orderViewModel.setZipCode(zipCode);
        orderViewModel.setItemType("console");
        orderViewModel.setItem(console);
        orderViewModel.setOrderQuantity(3);

//        ACT
        Invoice generatedInvoice = serviceLayer.generateInvoice(orderViewModel);

//        ASSERT
        assertEquals(expectedInvoice, generatedInvoice);
    }
    
    
    @Test
    public void invalidItemTypeShouldThrowIllegalArgumentException() {
//      ARRANGE
        populateItems();

        final String name = "Marcus Holloway";
        final String street = "2242 Bay Street";
        final String city = "Los Angeles";
        final String state = "CA";
        final String zipCode = "12832";
        Invoice expectedInvoice = new Invoice();
        expectedInvoice.setName(name);
        expectedInvoice.setStreet(street);
        expectedInvoice.setCity(city);
        expectedInvoice.setState(state);
        expectedInvoice.setZipCode(zipCode);
        expectedInvoice.setItemType("tshirt");
        expectedInvoice.setItemId(tShirt.getId());
        expectedInvoice.setUnitPrice(tShirt.getPrice());
        expectedInvoice.setOrderQuantity(3);
        expectedInvoice.setSubTotal(11.97);
        expectedInvoice.setTax(0.7182000000000001);
        expectedInvoice.setProcessingFee(1.98);
        expectedInvoice.setTotal(14.6682);

        OrderViewModel orderViewModel = new OrderViewModel();
        orderViewModel.setName(name);
        orderViewModel.setStreet(street);
        orderViewModel.setCity(city);
        orderViewModel.setState(state);
        orderViewModel.setZipCode(zipCode);
        orderViewModel.setItemType("unknown");
        orderViewModel.setItem(tShirt);
        orderViewModel.setOrderQuantity(3);

//        ASSERT
        assertThrows(IllegalArgumentException.class, ()-> serviceLayer.generateInvoice(orderViewModel));
    }
}
