package com.epam.carrental.controllers;

import com.epam.carrental.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet("/")
public class MainController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.isNew()){
            request.setAttribute("authNeeded", true);
        }
        session.getId();

        User user = (User) session.getAttribute("user");
        session.setAttribute("user", user);
//        session.setAttribute("userRole", userRole);
        if(user == null){
            request.setAttribute("authNeeded", true);
        }



        // TODO
        ServletContext servletContext = request.getServletContext();
        servletContext = getServletContext();


        ResourceBundle bundleDefault = ResourceBundle.getBundle("resources");
        ResourceBundle bundleUa = ResourceBundle.getBundle("resources_ua", new Locale("uk", "UA"));
        request.setAttribute("auth", bundleUa.getString("auth"));
        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }
}
