package com.epam.carrental.controllers.admin;

import com.epam.carrental.dao.DBException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AdminBrandsControllerTest {

    @Test
    void doGet() throws ServletException, IOException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        AdminBrandsController controller = new AdminBrandsController();
        Assertions.assertThrows(NullPointerException.class, () -> controller.doGet(request, response));
    }

    @Test
    void doPost() throws DBException, IOException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        AdminBrandsController controller = new AdminBrandsController();
//        Assertions.assertThrows(IOException.class, () -> controller.doPost(request, response));
        controller.doPost(request, response);
    }
}
