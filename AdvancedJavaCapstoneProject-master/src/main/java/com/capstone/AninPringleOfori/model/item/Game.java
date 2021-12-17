package com.capstone.AninPringleOfori.model.item;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Game implements Item {
    public int id;

    @Min(value = 0, message = "price cannot be negative.")
    public double price;

    @Min(value = 0, message = "orderQuantity cannot be negative.")
    public int orderQuantity;

    @NotNull(message = "Title cannot be empty")
    public String title;

    @NotNull(message = "Game rating must be specified.")
    public String esrbRating;

    public String description;

    @NotNull(message = "Studio must be specified.")
    public String studio;

    public Game() { }

    public Game (int gameId, String title, String esrbRating, String description, double price, String studio, int orderQuantity) {
        this.id = gameId;
        this.title = title;
        this.esrbRating = esrbRating;
        this.description = description;
        this.price = price;
        this.studio = studio;
        this.orderQuantity = orderQuantity;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    public int getOrderQuantity() { return orderQuantity; }

    public void setOrderQuantity(int orderQuantity) { this.orderQuantity = orderQuantity; }

    public String getTitle() { return title; }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEsrbRating() {
        return esrbRating;
    }

    public void setEsrbRating(String esrbRating) {
        this.esrbRating = esrbRating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Double.compare(game.getPrice(), getPrice()) == 0 && getOrderQuantity() == game.getOrderQuantity() && getTitle().equals(game.getTitle()) && getEsrbRating().equals(game.getEsrbRating()) && getDescription().equals(game.getDescription()) && getStudio().equals(game.getStudio());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getEsrbRating(), getDescription(), getPrice(), getStudio(), getOrderQuantity());
    }
}
