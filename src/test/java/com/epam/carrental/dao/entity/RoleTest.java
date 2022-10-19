package com.epam.carrental.dao.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
    void testCount() {
        Assertions.assertEquals(Role.values().length, 3);
    }

    @Test
    void testValues() {
        Assertions.assertEquals(Role.ADMIN, Role.valueOf("ADMIN"));
        Assertions.assertEquals(Role.MANAGER, Role.valueOf("MANAGER"));
        Assertions.assertEquals(Role.CLIENT, Role.valueOf("CLIENT"));
    }
}
