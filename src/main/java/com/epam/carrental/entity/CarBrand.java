package com.epam.carrental.entity;

import java.util.List;

public class CarBrand {
    int id;
    String name;

    public CarBrand() {
    }

    public CarBrand(int id) {
        this.id = id;
    }

    public CarBrand(String name) {
        this.name = name;
    }

    public CarBrand(int id, String name) {
        this.id = id;
        this.name = name;
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
}
