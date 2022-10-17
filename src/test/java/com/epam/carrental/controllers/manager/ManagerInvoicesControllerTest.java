package com.epam.carrental.controllers.manager;

import com.epam.carrental.controllers.Controller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ManagerInvoicesControllerTest {

    @Test
    void doGet() {
        Controller controller = new ManagerInvoicesController();
        Assertions.assertThrows(NullPointerException.class, () -> {
            controller.doGet(null, null);
        });
    }

    @Test
    void doPost() {
        Controller controller = new ManagerInvoicesController();
        Assertions.assertThrows(NullPointerException.class, () -> {
            controller.doGet(null, null);
        });
    }
}
