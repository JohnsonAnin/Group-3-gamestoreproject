package com.capstone.AninPringleOfori.dao;

import com.capstone.AninPringleOfori.model.order.SalesTaxRate;

public interface SalesTaxRateDao {
    public SalesTaxRate findSalesTax(String state);
}
