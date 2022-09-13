package com.epam.carrental.controllers;

import com.epam.carrental.dao.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/home"})
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String sort = request.getParameter("sort");
        String brand = request.getParameter("quality");
        String quality = request.getParameter("brand");

        request.setAttribute("cars", DAOFactory.getInstance().getCarDAO().getAll(brand, sort, quality));

        request.setAttribute("brands", DAOFactory.getInstance().getBrandDAO().getAllAvailible());
        request.setAttribute("qualities", DAOFactory.getInstance().getQualityDAO().getAllAvailible());
        request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
    }
}

//        ResourceBundle bundle = ResourceBundle.getBundle("resources");
//        ResourceBundle bundleUa = ResourceBundle.getBundle("resources_ua", new Locale("uk", "UA"));
//        request.setAttribute("auth", bundle.getString("auth"));
