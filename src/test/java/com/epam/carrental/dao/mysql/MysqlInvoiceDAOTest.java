package com.epam.carrental.dao.mysql;

import com.epam.carrental.dao.DBException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MysqlInvoiceDAOTest {
    MysqlInvoiceDAO dao;

    @BeforeEach
    void setUp() {
        dao = new MysqlInvoiceDAO();
    }

    @Test
    void insert() {
        Assertions.assertThrows(NullPointerException.class, () -> dao.insert(null, null));
    }

    @Test
    void update() {
        Assertions.assertThrows(NullPointerException.class, () -> dao.update(null));
    }

    @Test
    void getAll() {
        Assertions.assertThrows(NullPointerException.class, () -> dao.getAll());
    }

    @Test
    void getById() {
        Assertions.assertThrows(NullPointerException.class, () -> dao.getById(0));
    }

    @Test
    void getByIdAndUser() {
        Assertions.assertThrows(NullPointerException.class, () -> dao.getByIdAndUser(0, null));
    }

    @Test
    void getByUser() {
        Assertions.assertThrows(NullPointerException.class, () -> dao.getByUser(null));
    }

    @Test
    void getByOrder() throws DBException {
        Assertions.assertThrows(NullPointerException.class, () -> dao.getByOrder(null));
    }
}
