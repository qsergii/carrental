package com.epam.carrental.controllers.user;

import com.epam.carrental.controllers.Controller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrdersControllerTest {

    @Test
    void doGet() {
        Controller controller = new OrdersController();
        Assertions.assertThrows(NullPointerException.class, () -> {
            controller.doGet(null, null);
        });
    }

    @Test
    void doPost() {
        Controller controller = new OrdersController();
        Assertions.assertThrows(NullPointerException.class, () -> {
            controller.doGet(null, null);
        });
    }
}
