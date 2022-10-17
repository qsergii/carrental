package com.epam.carrental.controllers.manager;

import com.epam.carrental.controllers.Controller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ManagerOrdersControllerTest {

    @Test
    void doGet() {
        Controller controller = new ManagerOrdersController();
        Assertions.assertThrows(NullPointerException.class, () -> {
            controller.doGet(null, null);
        });
    }

    @Test
    void doPost() {
        Controller controller = new ManagerOrdersController();
        Assertions.assertThrows(NullPointerException.class, () -> {
            controller.doGet(null, null);
        });
    }
}
