package com.epam.carrental.controllers.user;

import com.epam.carrental.controllers.Controller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @Test
    void doGet() {
        Controller controller = new UserController();
        Assertions.assertThrows(NullPointerException.class, () -> {
            controller.doGet(null, null);
        });
    }
}
