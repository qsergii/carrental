package com.epam.carrental.entity;

public class CarQuality {
    int id;
    String name;

    public CarQuality() {
    }

    public CarQuality(int id) {
        this.id = id;
    }

    public CarQuality(String name) {
        this.name = name;
    }

    public CarQuality(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
