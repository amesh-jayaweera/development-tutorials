package com.tutorial.restapidesign.dto;

public class ProductDTO {

    private long id;
    private String name;
    private String description;
    private int qty;
    private double price;

    public ProductDTO() {
    }

    public ProductDTO(String name, String description, int qty, double price) {
        this.name = name;
        this.description = description;
        this.qty = qty;
        this.price = price;
    }

    public ProductDTO(long id, String name, String description, int qty, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.qty = qty;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}