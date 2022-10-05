package com.epam.carrental.controllers.admin;

import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import static org.mockito.Mockito.*;

class AdminControllerTest {

    @Test
    void doGet() throws ServletException, IOException {
        AdminController servlet = new AdminController();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("name"))
                .thenReturn("Obama");

        OutputStream outputStream = new ByteArrayOutputStream();
        PrintWriter printWriter = new PrintWriter(outputStream);
        when(response.getWriter())
                .thenReturn(printWriter);
//        when(response.sendRedirect("admin/cars"))
//                .thenReturn(printWriter);

        servlet.doGet(request, response);

        printWriter.close();

        verify(response).sendRedirect("admin/cars");

//        String expected = "Hi, Obama!";
//        Assertions.assertEquals(expected, outputStream.toString());
    }
}
