package com.epam.carrental.controllers.user;

import com.epam.carrental.controllers.Controller;
import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.dao.entity.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class CarsController implements Controller {
    public final Logger log = LogManager.getLogger(this.getClass());

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id") != null) {
            writeCar(request, response);
        } else {
            response.sendError(404);
        }
    }

    private void writeCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(Optional.ofNullable(request.getParameter("id")).orElse("0"));
        Car car = DAOFactory.getInstance().getCarDAO().getById(id, true);
        request.setAttribute("car", car);
        request.getRequestDispatcher("/WEB-INF/user/car.jsp").forward(request, response);
    }
}
