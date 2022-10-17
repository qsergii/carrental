package com.epam.carrental.controllers.user;

import com.epam.carrental.controllers.Controller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvoicesControllerTest {

    @Test
    void doGet() {
        Controller controller = new InvoicesController();
        Assertions.assertThrows(NullPointerException.class, () -> {
            controller.doGet(null, null);
        });
    }

    @Test
    void doPost() {
        Controller controller = new InvoicesController();
        Assertions.assertThrows(NullPointerException.class, () -> {
            controller.doPost(null, null);
        });
    }
}
