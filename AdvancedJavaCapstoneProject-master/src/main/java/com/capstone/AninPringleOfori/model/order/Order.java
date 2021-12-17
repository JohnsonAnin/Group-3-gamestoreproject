package com.capstone.AninPringleOfori.model.order;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Order {
    @NotNull(message = "name must be specified.")
    public String name;

    @NotNull(message = "street must be specified.")
    public String street;

    @NotNull(message = "city must be specified.")
    public String city;

    @NotNull(message = "state must be specified.")
    public String state;

    @NotNull(message = "zipCode must be specified.")
    public String zipCode;

    @NotNull(message = "itemType must be specified.")
    public String itemType;

    @NotNull(message = "itemId must be specified.")
    public int itemId;

    @Min(value = 1, message = "orderQuantity cannot be less than 1.")
    public int orderQuantity;

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

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
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
        Order order = (Order) o;
        return getItemId() == order.getItemId() && getOrderQuantity() == order.getOrderQuantity() && getName().equals(order.getName()) && getStreet().equals(order.getStreet()) && getCity().equals(order.getCity()) && getState().equals(order.getState()) && getZipCode().equals(order.getZipCode()) && getItemType().equals(order.getItemType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getStreet(), getCity(), getState(), getZipCode(), getItemType(), getItemId(), getOrderQuantity());
    }

    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", itemType='" + itemType + '\'' +
                ", itemId=" + itemId +
                ", orderQuantity=" + orderQuantity +
                '}';
    }
}
