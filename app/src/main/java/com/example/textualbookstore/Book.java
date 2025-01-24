package com.example.textualbookstore;

public class Book {
    private String title;
    private int image;
    private double price;
    private String description;

    public Book(String title, int image, double price, String description) {
        this.title = title;
        this.image = image;
        this.price = price;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}