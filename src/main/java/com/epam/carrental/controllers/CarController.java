package com.epam.carrental.controllers;

import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.entity.Car;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/car")
public class CarController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(Optional.ofNullable(request.getParameter("id")).orElse("0"));
        if (id > 0){
            printCar(id, request, response);
        }else {
            response.sendError(404);
        }
    }

    private void printCar(int id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Car car = DAOFactory.getInstance().getCarDAO().getById(id);
        request.setAttribute("car", car);
//        request.setAttribute("title", car.getName());
        request.getRequestDispatcher("/WEB-INF/car.jsp").forward(request, response);
    }
}
