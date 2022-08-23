package com.epam.carrental.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet("/")
public class MainController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        Locale[] locales = Locale.getAvailableLocales();
//        Arrays.stream(locales).forEach(System.out::println);

        ResourceBundle bundleDefault = ResourceBundle.getBundle("resources");
        ResourceBundle bundleUa = ResourceBundle.getBundle("resources_ua", new Locale("uk", "UA"));
        request.setAttribute("auth", bundleUa.getString("auth"));
        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }
}
