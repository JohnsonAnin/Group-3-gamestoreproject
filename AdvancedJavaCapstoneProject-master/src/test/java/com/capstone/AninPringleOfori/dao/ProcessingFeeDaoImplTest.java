package com.capstone.AninPringleOfori.dao;

import com.capstone.AninPringleOfori.model.order.ProcessingFee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ProcessingFeeDaoImplTest {

    @Autowired
    ProcessingFeeDao processingFeeDao;


    @Test
    public void validProductTypeShouldReturnFee() {
//        ARRANGE
        final String productType = "game";
        final ProcessingFee expectedFee = new ProcessingFee(productType, 1.49);

//        ACT
        final ProcessingFee processingFee = processingFeeDao.getFee(productType);

//        ASSERT
        assertEquals(expectedFee, processingFee);
    }

    @Test
    public void invalidProductTypeShouldThrowIllegalArgumentException() {
//        ARRANGE
        final String productType = "invalid_type";
        final ProcessingFee expectedFee = new ProcessingFee(productType, 1.49);

//        ASSERT
       assertThrows(IllegalArgumentException.class, () -> processingFeeDao.getFee(productType));
    }
}
