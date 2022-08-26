package com.epam.carrental.entity;

import java.util.List;

public class Car {
    int id;
    String name;
    String description;
    boolean blocked;
    float price;
    CarBrand brand;
    CarQuality quality;

    public Car(
            int id,
            String name,
            String description,
            boolean blocked,
            float price,
            CarQuality quality,
            CarBrand brand) {
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

    public CarBrand getBrand() {
        return brand;
    }

    public void setBrand(CarBrand brand) {
        this.brand = brand;
    }

    public CarQuality getQuality() {
        return quality;
    }

    public void setQuality(CarQuality quality) {
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
