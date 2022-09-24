package com.epam.carrental.dao.entity;

import java.util.Objects;

public class Quality {
    int id;
    String name;

    public Quality() {
    }

    public Quality(int id) {
        this.id = id;
    }

    public Quality(String name) {
        this.name = name;
    }

    public Quality(int id, String name) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quality quality = (Quality) o;
        return id == quality.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
