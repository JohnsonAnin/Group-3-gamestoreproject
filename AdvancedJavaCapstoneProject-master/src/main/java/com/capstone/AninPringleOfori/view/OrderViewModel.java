package com.capstone.AninPringleOfori.view;

import com.capstone.AninPringleOfori.model.item.Item;

import java.util.Objects;

public class OrderViewModel {
    private String name;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String itemType;
    private Item item;
    private int orderQuantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderViewModel that = (OrderViewModel) o;
        return getOrderQuantity() == that.getOrderQuantity() && getName().equals(that.getName()) && getStreet().equals(that.getStreet()) && getCity().equals(that.getCity()) && getState().equals(that.getState()) && getZipCode().equals(that.getZipCode()) && getItemType().equals(that.getItemType()) && getItem().equals(that.getItem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getStreet(), getCity(), getState(), getZipCode(), getItemType(), getItem(), getOrderQuantity());
    }
}
