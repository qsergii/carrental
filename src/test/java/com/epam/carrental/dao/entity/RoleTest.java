package com.epam.carrental.dao.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    /**
     * Check if roles present
     */
    @Test
    void present() {
        Assertions.assertDoesNotThrow(() -> {
            Role role;
            role = Role.ADMIN;
            role = Role.MANAGER;
            role = Role.CLIENT;
        });
    }

    /**
     * check if roles have not being added
     */
    @Test
    void checkNumber() {
        Assertions.assertEquals(Role.values().length, 3);
    }
}
