package com.epam.carrental.controllers.admin;

import com.epam.carrental.CarDriver;
import com.epam.carrental.controllers.Controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminDriverController implements Controller {

    private final Logger log = LogManager.getLogger(this.getClass());

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("driverPrice", CarDriver.getPrice());
        request.getRequestDispatcher("/WEB-INF/admin/driver.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int price = Integer.parseInt(request.getParameter("price"));
        CarDriver.setPrice(price);
        response.sendRedirect("driver");
    }

}
