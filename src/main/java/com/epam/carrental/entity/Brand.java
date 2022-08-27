package com.epam.carrental.entity;

public class Brand {
    int id;
    String name;

    public Brand() {
    }

    public Brand(int id) {
        this.id = id;
    }

    public Brand(String name) {
        this.name = name;
    }

    public Brand(int id, String name) {
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
