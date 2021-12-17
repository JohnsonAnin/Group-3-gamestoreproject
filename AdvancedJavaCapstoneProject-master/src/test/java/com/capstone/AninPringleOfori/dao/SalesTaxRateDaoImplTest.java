package com.capstone.AninPringleOfori.dao;

import com.capstone.AninPringleOfori.model.order.SalesTaxRate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SalesTaxRateDaoImplTest {

    @Autowired
    SalesTaxRateDao salesTaxRateDao;

    @Test
    public void validStateShouldReturnTaxRate() {
//        ARRANGE
        final String state = "AR";
        final SalesTaxRate expectedTaxRate = new SalesTaxRate(state, 0.06);

//        ACT
        final SalesTaxRate taxRate = salesTaxRateDao.findSalesTax(state);

//        ASSERT
        assertEquals(expectedTaxRate, taxRate);
    }

    @Test
    public void invalidStateShouldThrowIllegalArgumentException(){
//        ARRANGE
        final String invalidState = "INVALID_STATE";

//        ASSERT
        assertThrows(IllegalArgumentException.class, () -> salesTaxRateDao.findSalesTax(invalidState));
    }
}
