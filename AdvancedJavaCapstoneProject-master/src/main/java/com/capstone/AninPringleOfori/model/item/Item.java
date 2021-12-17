package com.capstone.AninPringleOfori.model.item;

import javax.validation.constraints.Min;

public interface Item {

    public int getId();

    public void setId(int id);

    public double getPrice();

    public void setPrice(double price);

    public int getOrderQuantity();

    public void setOrderQuantity(int quantity);
}
