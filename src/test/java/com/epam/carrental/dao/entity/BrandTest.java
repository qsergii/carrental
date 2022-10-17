package com.epam.carrental.dao.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BrandTest {
    Brand brand;

    @BeforeEach
    void before() {
        brand = new Brand();
    }

    @Test
    void constructors() {
        brand = new Brand(1);
        Assertions.assertEquals(1, brand.getId());

        brand = new Brand("one");
        Assertions.assertEquals(brand.getName(), "one");
    }

    @Test
    void getId() {
        Assertions.assertEquals(0, brand.getId());
    }

    @Test
    void setId() {
        brand.setId(1);
        Assertions.assertEquals(1, brand.getId());
    }

    @Test
    void getName() {
        Assertions.assertEquals(null, brand.getName());
    }

    @Test
    void setName() {
        brand.setName("one");
        Assertions.assertEquals("one", brand.getName());
    }

    @Test
    void testHashCode() {
        brand.setId(1);
        brand.setName("one");
        Assertions.assertNotEquals(0, brand.hashCode());
    }

    @Test
    void testEquals() {
        Assertions.assertTrue(brand.equals(brand));

        brand.setId(1);
        brand.setName("one");

        Brand brand2 = new Brand(1, "one");
        Assertions.assertTrue(brand.equals(brand2));
    }
}
