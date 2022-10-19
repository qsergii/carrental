package com.epam.carrental.dao.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QualityTest {

    @Test
    void fields() {
        Quality instance = new Quality();

        int id = 1;
        instance.setId(id);
        assertEquals(id, instance.getId());

        String name = "1";
        instance.setName(name);
        assertEquals(name, instance.getName());

    }


    @Test
    void testEquals() {
        Quality instance1 = new Quality();
        Quality instance2 = new Quality();
        instance1.setId(1);
        instance2.setId(1);
        instance1.setName("one");
        instance2.setName("one");
        assertTrue(instance1.equals(instance2));
    }

    @Test
    void testHashCode() {
        Quality instance1 = new Quality();
        int hash = instance1.hashCode();
        assertEquals(31, hash);
    }
}
