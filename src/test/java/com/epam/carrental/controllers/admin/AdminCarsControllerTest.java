package com.epam.carrental.controllers.admin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.when;

class AdminCarsControllerTest {

    @Test
    void doGet() throws ServletException, IOException {
        AdminCarsController controller = new AdminCarsController();

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        Assertions.assertThrows(NullPointerException.class, () -> controller.doGet(request, response));

        when(request.getParameter("id"))
                .thenReturn("1");
        Assertions.assertThrows(NullPointerException.class, () -> controller.doGet(request, response));
    }

    @Test
    void doPost() throws Exception {
        AdminCarsController controller = new AdminCarsController();

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        when(request.getContentType()).thenReturn("");
        controller.doPost(request, response);
//        Assertions.assertThrows(NullPointerException.class, () -> controller.doPost(request, response));

        when(request.getContentType()).thenReturn("multipart/form-data");
        Assertions.assertThrows(NullPointerException.class, () -> controller.doPost(request, response));

        when(request.getParameter("action")).thenReturn("delete");
        Assertions.assertThrows(NullPointerException.class, () -> controller.doPost(request, response));

    }
}
