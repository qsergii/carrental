package com.epam.carrental.dao.mysql;

import com.epam.carrental.dao.entity.Car;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MysqlCarDAOTest {
    MysqlCarDAO dao;

    @BeforeEach
    void setUp() {
        dao = new MysqlCarDAO();
    }

    @Test
    void insert() {
        Car car = new Car();
        Assertions.assertThrows(NullPointerException.class, () -> dao.insert(car));
    }

    @Test
    void update() {
        Assertions.assertThrows(NullPointerException.class, () -> dao.update(null));
    }

    @Test
    void getAll() {
        Assertions.assertThrows(NullPointerException.class, () -> dao.getAll(false));
    }

    @Test
    void getByBrand() {
        Assertions.assertThrows(NullPointerException.class, () -> dao.getByBrand(null));
    }

    @Test
    void getByQuality() {
        Assertions.assertThrows(NullPointerException.class, () -> dao.getByQuality(null));
    }

    @Test
    void testGetAll() {
        Assertions.assertThrows(NullPointerException.class, () -> dao.getAll(null, null, null, 0));
    }

    @Test
    void getById() {
        Assertions.assertThrows(NullPointerException.class, () -> dao.getById(0, false));
    }

    @Test
    void delete() {
        Assertions.assertThrows(NullPointerException.class, () -> dao.delete(null));
    }
}
