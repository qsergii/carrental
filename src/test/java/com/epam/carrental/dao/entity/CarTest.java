package com.epam.carrental.dao.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    Car car;

    @BeforeEach
    void setUp() {
        car = new Car();
    }

    @Test
    void testFields() {
        int id = 1;
        car.setId(id);
        assertEquals(id, car.getId());

        String name = "one";
        car.setName(name);
        assertEquals(name, car.getName());

        float price = 0.01F;
        car.setPrice(price);
        assertEquals(price, car.getPrice());

        Brand brand = new Brand();
        car.setBrand(brand);
        assertEquals(brand, car.getBrand());

        Quality quality = new Quality();
        car.setQuality(quality);
        assertEquals(quality, car.getQuality());

        String description = "one";
        car.setDescription(description);
        assertEquals(description, car.getDescription());

        boolean blocked = true;
        car.setBlocked(blocked);
        assertEquals(blocked, car.isBlocked());

        String filename = "one";
        car.setImageFileName(filename);
        assertEquals(filename, car.getImageFileName());
    }
    
}
