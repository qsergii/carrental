package com.epam.carrental.entity;

public class Car {
    int id;
    String name;
    String description;
    boolean blocked;
    float price;
    Brand brand;
    Quality quality;

    public Car() {}

    public Car(
            int id,
            String name,
            String description,
            boolean blocked,
            float price,
            Quality quality,
            Brand brand) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.blocked = blocked;
        this.price = price;
        this.quality = quality;
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Quality getQuality() {
        return quality;
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
