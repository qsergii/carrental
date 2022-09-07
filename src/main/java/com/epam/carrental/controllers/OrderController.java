package com.epam.carrental.controllers;

import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.entity.Car;
import com.epam.carrental.entity.Order;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.desktop.OpenFilesEvent;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/order")
public class OrderController extends HttpServlet {
    private Logger log = Logger.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int carId = Integer.parseInt(Optional.ofNullable(request.getParameter("carid")).orElse("0"));
        if(carId > 0){
            Car car = DAOFactory.getInstance().getCarDAO().getById(carId);
            if(car == null){
                response.sendError(400);
                return;
            }

            Order order = new Order();
            order.setCar(car);
            if(! DAOFactory.getInstance().getOrderDAO().insert(order)){
                log.error("can't write order to database");
                response.sendError(500);
                return;
            }
        }

    }
}
