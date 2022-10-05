package com.epam.carrental.controllers;

import com.epam.carrental.dao.DBException;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ControllerTest {

    @Test
    void testDoGet() throws ServletException, DBException, SQLException, IOException {
        Controller controller = new Controller() {
            @Override
            public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException, DBException {
                Controller.super.doGet(request, response);
            }
        };
        controller.doGet(null, null);
    }

    @Test
    void testDoPost() throws Exception {
        Controller controller = new Controller() {
            @Override
            public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException, DBException {
                Controller.super.doGet(request, response);
            }
        };
        controller.doPost(null, null);
    }
}
