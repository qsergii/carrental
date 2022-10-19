package com.epam.carrental.controllers.admin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AdminDriverControllerTest {

    @Test
    void doGet() throws ServletException, IOException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        AdminDriverController controller = new AdminDriverController();
        Assertions.assertThrows(NullPointerException.class, () -> controller.doGet(request, response));
    }

    @Test
    void doPost() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        AdminDriverController controller = new AdminDriverController();
        Assertions.assertThrows(NumberFormatException.class, () -> controller.doPost(request, response));
    }
}
