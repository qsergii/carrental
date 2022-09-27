package com.epam.carrental.controllers;

import org.junit.Assert;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import static org.mockito.Mockito.*;

public class FrontControllerTest {

    @Test(expected = IllegalStateException.class)
    public void doGetGeneral() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("login")).thenReturn("admin");

        PipedReader pr = new PipedReader();
        PipedWriter pw = new PipedWriter(pr);
        PrintWriter out = new PrintWriter(pw, true);
        when(response.getWriter()).thenReturn(out);

        new FrontController().service(request, response);

        Scanner s = new Scanner(pr);
        Assert.assertEquals("Hi, admin", s.nextLine());
        s.close();
    }

    @Test
    public void testDispatcherAndForwarding() throws ServletException, IOException {
//        String path = "/WEB-INF/view/index.jsp";
//        FrontController servlet = new FrontController();
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
//
//        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
//
//        servlet.doGet(request, response);
//
//        verify(request, times(1)).getRequestDispatcher(path);
//        verify(request, never()).getSession();
//        verify(dispatcher).forward(request, response);
    }

}