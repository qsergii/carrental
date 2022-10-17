package com.epam.carrental.dao.entity;

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

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !obj.getClass().equals(this.getClass()))
            return false;
        if (obj == this)
            return true;

        Brand that = (Brand) obj;

        return this.getId() == that.getId()
                && this.getName().equals(that.getName());
    }
}
