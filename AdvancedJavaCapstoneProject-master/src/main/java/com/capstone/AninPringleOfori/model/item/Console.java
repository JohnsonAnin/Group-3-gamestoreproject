package com.capstone.AninPringleOfori.model.item;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Console implements Item {
    public int id;

    @Min(value = 0, message = "price cannot be negative.")
    public double price;

    @Min(value = 0, message = "orderQuantity cannot be negative.")
    public int orderQuantity;

    @NotNull(message = "Title cannot be empty")
    public String model;

    @NotNull(message = "Game rating must be specified.")
    public String manufacturer;

    public String memoryAmount;

    @NotNull(message = "Studio must be specified.")
    public String processor;

    public Console() {
    }

    public Console(int id, String model, String manufacturer, String memoryAmount, String processor, double price, int orderQuantity) {
        this.id = id;
        this.price = price;
        this.orderQuantity = orderQuantity;
        this.model = model;
        this.manufacturer = manufacturer;
        this.memoryAmount = memoryAmount;
        this.processor = processor;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMemoryAmount() {
        return memoryAmount;
    }

    public void setMemoryAmount(String memoryAmount) {
        this.memoryAmount = memoryAmount;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int getOrderQuantity() {
        return orderQuantity;
    }

    @Override
    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Console console = (Console) o;
        return Double.compare(console.getPrice(), getPrice()) == 0 &&
                getOrderQuantity() == console.getOrderQuantity() &&
                getModel().equals(console.getModel()) &&
                getManufacturer().equals(console.getManufacturer()) &&
                getMemoryAmount().equals(console.getMemoryAmount()) &&
                getProcessor().equals(console.getProcessor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrice(), getOrderQuantity(), getModel(), getManufacturer(), getMemoryAmount(), getProcessor());
    }
}
