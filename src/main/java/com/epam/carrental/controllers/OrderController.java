package com.epam.carrental.controllers;

import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.entity.Car;
import com.epam.carrental.entity.Order;
import com.epam.carrental.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@WebServlet("/order")
public class OrderController extends HttpServlet {
    private final Logger log = Logger.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            handleGetRequest(request, response);
        } catch (Exception e) {
            log.error(e.getMessage());
            try {
                response.sendError(500);
            } catch (IOException e2) {
                log.error(e2.getMessage());
            }
        }
    }

    private void handleGetRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String carIdString = request.getParameter("car-id");
        if(carIdString != null){
            int carId = Integer.parseInt(carIdString);
            if (carId > 0) {
                Car car = DAOFactory.getInstance().getCarDAO().getById(carId);
                if (car == null) {
                    response.sendError(400);
                    return;
                }

                User user = (User) request.getAttribute("user");
                if(user == null){
                    HttpSession session = request.getSession();
                    session.setAttribute("car", car);
                    response.sendRedirect("login");
                    return;
                }

                request.setAttribute("car", car);
                request.getRequestDispatcher("/WEB-INF/order.jsp").forward(request, response);
            } else {
                response.sendError(400);
            }
        }
        else{
            HttpSession session = request.getSession();
            if(session.getAttribute("car") != null){
                request.setAttribute("car", session.getAttribute("car"));
                session.removeAttribute("car");
                request.getRequestDispatcher("/WEB-INF/order.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            handlePostRequest(request, response);
        } catch (Exception e) {
            log.error(e.getMessage());
            try {
                response.sendError(500);
            } catch (IOException e2) {
                log.error(e2.getMessage());
            }
        }
    }

    private void handlePostRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException {
        User user;
        boolean withDriver;
        int leaseTerm;
        String passportNumber;
        Date passportValid;
        Car car;
        float price;

        String carIdString = request.getParameter("car-id");
        int carId = Integer.parseInt(Optional.ofNullable(carIdString).orElse("0"));
        if (carId <= 0) {
            response.sendError(400);
            return;
        }
        car = DAOFactory.getInstance().getCarDAO().getById(carId);
        user = (User) request.getAttribute("user");
        if(user == null){
            HttpSession session = request.getSession();
            session.setAttribute("car", car);
            response.sendRedirect("login");
            return;
        }
        withDriver = request.getParameter("with-driver").equals("with-driver");
        leaseTerm = Integer.parseInt(request.getParameter("lease-term"));
        passportNumber = request.getParameter("passport-number");

        String input = request.getParameter("passport-valid");
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        passportValid = parser.parse(input);

        price = Float.parseFloat(request.getParameter("price"));
        if (price != car.getPrice()) {
            response.sendError(400);
            return;
        }

        Order order = new Order();
        order.setUser(user);
        order.setWithDriver(withDriver);
        order.setLeaseTerm(leaseTerm);
        order.setPassportNumber(passportNumber);
        order.setPassportValid(passportValid);
        order.setCar(car);
        order.setPrice(price);
        if (!DAOFactory.getInstance().getOrderDAO().insert(order)) {
            log.error("can't write order to database");
            response.sendError(500);
        }
        request.setAttribute("order", order);
        request.getRequestDispatcher("/WEB-INF/new-order.jsp").forward(request, response);
    }
}
