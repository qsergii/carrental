package com.epam.carrental.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DatabaseTest {

    @Test
    void classCreate() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            Database.dataSource.getConnection();
        });
    }

}
