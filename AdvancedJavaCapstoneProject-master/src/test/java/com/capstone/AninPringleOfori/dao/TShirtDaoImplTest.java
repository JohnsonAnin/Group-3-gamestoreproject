package com.capstone.AninPringleOfori.dao;

import com.capstone.AninPringleOfori.model.item.TShirt;
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
public class TShirtDaoImplTest {

    TShirt tShirt1;
    TShirt tShirt2;
    @Autowired
    TShirtDao tShirtDao;

    @Before
    public void setUp() {
        tearDown();

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
    }

    void populateTShirts() {
        tShirt1 = tShirtDao.addtShirt(tShirt1);
        tShirt2 = tShirtDao.addtShirt(tShirt2);
    }

    @After
    public void tearDown() {
        List<TShirt> allTShirts = tShirtDao.findAlltShirts();

        allTShirts.forEach(tShirt -> tShirtDao.deletetShirt(tShirt.getId()));
    }


    @Test
    public void addedTShirtShouldExist() {
//        ACT
        tShirt1 = tShirtDao.addtShirt(tShirt1);

//        ASSERT
        assertEquals(tShirt1, tShirtDao.findById(tShirt1.getId()));
    }

    @Test
    public void deletedTShirtShouldNotExist() {
//        ARRANGE
        populateTShirts();

//        ACT
        tShirtDao.deletetShirt(tShirt1.getId());

//        ASSERT
        assertNull(tShirtDao.findById(tShirt1.getId()));
    }

    @Test
    public void findAlltShirtsShouldReturnAllTShirts() {
//        ARRANGE
        populateTShirts();

//        ACT
        final List<TShirt> allTShirts = tShirtDao.findAlltShirts();

//        ASSERT
        assertEquals(2, allTShirts.size());
    }

    @Test
    public void findBySizeShouldReturnTShirtsBySize() {
//        ARRANGE
        populateTShirts();

//        ACT
        final List<TShirt> mediumtShirts = tShirtDao.findtShirtBySize("M");
        final List<TShirt> smalltShirts = tShirtDao.findtShirtBySize("S");
        final List<TShirt> largetShirts = tShirtDao.findtShirtBySize("L"); //Should be empty

//        ASSERT
        assertEquals(1, mediumtShirts.size());
        assertEquals(1, smalltShirts.size());
        assertEquals(0, largetShirts.size());
    }


    @Test
    public void findByColorShouldReturnTShirtsByColor() {
//        ARRANGE
        populateTShirts();

//        ACT
        final List<TShirt> greentShirts = tShirtDao.findtShirtByColor("green");
        final List<TShirt> bluetShirts = tShirtDao.findtShirtByColor("blue");
        final List<TShirt> blacktShirts = tShirtDao.findtShirtByColor("black"); //Should be empty

//        ASSERT
        assertEquals(1, greentShirts.size());
        assertEquals(1, bluetShirts.size());
        assertEquals(0, blacktShirts.size());
    }
    
    
    @Test
    public void updatedTShirtShouldBeUpdated() {
//        ARRANGE
        populateTShirts();
        final String newColor = "white";
//        ACT
        tShirt1.setColor(newColor);
        tShirtDao.updatetShirt(tShirt1);
        final TShirt updatedTShirt = tShirtDao.findById(tShirt1.getId());

//        ASSERT
        assertEquals(tShirt1, updatedTShirt);
    }

    @Test
    public void decrementQuantityShouldReduceQuantityBySpecifiedAmount() {
//        ARRANGE
        populateTShirts();
        final int decrementAmount = 3;
        final int initialQuantity = tShirt1.getOrderQuantity();
//        ACT
        tShirtDao.decrementQuantity(tShirt1, 3);
        TShirt updatedTShirt = tShirtDao.findById(tShirt1.getId());

//        ASSERT
        assertEquals(initialQuantity - decrementAmount, updatedTShirt.getOrderQuantity());
    }
}
