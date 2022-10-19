package com.epam.carrental.controllers.user;

import com.epam.carrental.controllers.Controller;
import com.epam.carrental.dao.DBException;
import com.epam.carrental.dao.entity.Car;
import com.epam.carrental.dao.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrdersControllerTest {

    @Test
    void testDoGet() throws ServletException, DBException, SQLException, IOException {
        Controller controller = new OrdersController();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(new User());
        when(session.getAttribute("car")).thenReturn(new Car());
        controller.doGet(request, response);

        when(session.getAttribute("id")).thenReturn("1");
        controller.doGet(request, response);

        when(session.getAttribute("car_id")).thenReturn("1");
        controller.doGet(request, response);

//        Assertions.assertThrows(NullPointerException.class, () -> {
//            controller.doGet(request, response);
//        });
    }

    @Test
    void testDoPost() throws ServletException, DBException, SQLException, IOException {
        Controller controller = new OrdersController();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("car_id")).thenReturn("1");

        controller.doGet(request, response);
//        Assertions.assertThrows(NullPointerException.class, () -> {
//        });
    }

}
