package com.epam.carrental.controllers.auth;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

class LogoutControllerTest {

    @Test
    void doGet() throws ServletException, IOException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        LogoutController controller = new LogoutController();
        Assertions.assertThrows(NullPointerException.class, () -> controller.doGet(request, response));
    }
}
