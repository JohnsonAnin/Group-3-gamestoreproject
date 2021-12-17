package com.capstone.AninPringleOfori.dao;

import com.capstone.AninPringleOfori.model.item.Item;

public interface ItemDao {
    public void decrementQuantity(Item item, int orderQuantity);
    public Item findById(int itemId);
    }


