package com.capstone.AninPringleOfori.dao;

import com.capstone.AninPringleOfori.model.item.Console;
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
public class ConsoleDaoImplTest {

    Console console1;
    Console console2;
    @Autowired
    ConsoleDao consoleDao;

    @Before
    public void setUp() {
        tearDown();

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
    }

    void populateConsoles() {
        console1 = consoleDao.addConsole(console1);
        console2 = consoleDao.addConsole(console2);
    }

    @After
    public void tearDown() {
        List<Console> allConsoles = consoleDao.findAllConsoles();

        allConsoles.forEach(console -> consoleDao.deleteConsole(console.getId()));
    }


    @Test
    public void addedConsoleShouldExist() {
//        ACT
        console1 = consoleDao.addConsole(console1);

//        ASSERT
        assertEquals(console1, consoleDao.findById(console1.getId()));
    }

    @Test
    public void deletedConsoleShouldNotExist() {
//        ARRANGE
        populateConsoles();

//        ACT
        consoleDao.deleteConsole(console1.getId());

//        ASSERT
        assertNull(consoleDao.findById(console1.getId()));
    }

    @Test
    public void findAllConsolesShouldReturnAllConsoles() {
//        ARRANGE
        populateConsoles();

//        ACT
        final List<Console> allConsoles = consoleDao.findAllConsoles();

//        ASSERT
        assertEquals(2, allConsoles.size());
    }

    @Test
    public void findByManufacturerShouldReturnConsolesByManufacturer() {
//        ARRANGE
        populateConsoles();;

//        ACT
        final List<Console> sonyConsoles = consoleDao.findConsolesByManufacturer("Sony");
        final List<Console> microsoftConsoles = consoleDao.findConsolesByManufacturer("Microsoft");
        final List<Console> nintendoConsoles = consoleDao.findConsolesByManufacturer("Nintendo"); //Should be empty

//        ASSERT
        assertEquals(1, sonyConsoles.size());
        assertEquals(1, microsoftConsoles.size());
        assertEquals(0, nintendoConsoles.size());
    }

    @Test
    public void updatedConsoleShouldBeUpdated() {
//        ARRANGE
        populateConsoles();
        final String newProcessor = "intel";
//        ACT
        console1.setProcessor(newProcessor);
        consoleDao.updateConsole(console1);
        final Console updatedConsole = consoleDao.findById(console1.getId());

//        ASSERT
        assertEquals(console1, updatedConsole);
    }

    @Test
    public void decrementQuantityShouldReduceQuantityBySpecifiedAmount() {
//        ARRANGE
        populateConsoles();
        final int decrementAmount = 3;
        final int initialQuantity = console1.getOrderQuantity();
//        ACT
        consoleDao.decrementQuantity(console1, 3);
        Console updatedConsole = consoleDao.findById(console1.getId());

//        ASSERT
        assertEquals(initialQuantity - decrementAmount, updatedConsole.getOrderQuantity());
    }
}
