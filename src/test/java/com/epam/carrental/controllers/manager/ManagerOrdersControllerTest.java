package com.epam.carrental.controllers.manager;

import com.epam.carrental.controllers.Controller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.when;

class ManagerOrdersControllerTest {

    @Test
    void testDoGet() {

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        Controller controller = new ManagerOrdersController();
        Assertions.assertThrows(NullPointerException.class, () -> {
            controller.doGet(request, response);
        });

        // with parameter
        when(request.getParameter("id"))
                .thenReturn("1");
        Assertions.assertThrows(NullPointerException.class, () -> {
            controller.doGet(request, response);
        });

    }

    @Test
    void testDoPost() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        Controller controller = new ManagerOrdersController();

        when(request.getParameter("action"))
                .thenReturn("reject");

//        Assertions.assertThrows(NullPointerException.class, () -> {
//            controller.doPost(request, response);
//        });
        controller.doPost(request, response);

        when(request.getParameter("action"))
                .thenReturn("return");
        controller.doPost(request, response);

    }

}
