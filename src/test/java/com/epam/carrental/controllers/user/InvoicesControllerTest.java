package com.epam.carrental.controllers.user;

import com.epam.carrental.controllers.Controller;
import com.epam.carrental.dao.DBException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class InvoicesControllerTest {

    @Test
    void doGet() throws ServletException, DBException, SQLException, IOException {
        Controller controller = new InvoicesController();

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        Assertions.assertThrows(NullPointerException.class, () -> {
            controller.doGet(request, response);
        });

        when(request.getParameter("id")).thenReturn("1");
        Assertions.assertThrows(NullPointerException.class, () -> {
            controller.doGet(request, response);
        });

        when(request.getParameter("export")).thenReturn("export");
        Assertions.assertThrows(NullPointerException.class, () -> {
            controller.doGet(request, response);
        });


    }

    @Test
    void doPost() throws Exception {
        Controller controller = new InvoicesController();

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        controller.doPost(request, response);

        when(request.getParameter("action")).thenReturn("pay");
        controller.doPost(request, response);

//        Assertions.assertThrows(NullPointerException.class, () -> {
//        });
    }

}
