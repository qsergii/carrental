package com.epam.carrental.controllers.auth;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.verify;

class LoginControllerTest {

    @Test
    void doGet() throws ServletException, IOException {

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = Mockito.mock(RequestDispatcher.class);

        Mockito.when(request.getRequestDispatcher("/WEB-INF/auth/login.jsp"))
                .thenReturn(requestDispatcher);

        LoginController controller = new LoginController();
        controller.doGet(request, response);
        
        verify(requestDispatcher).forward(request, response);
    }
}
