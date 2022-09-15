package com.epam.carrental.controllers.user;

import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.entity.Car;
import com.epam.carrental.entity.Invoice;
import com.epam.carrental.entity.Order;
import com.epam.carrental.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private final Logger log = LogManager.getLogger(this.getClass());

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
        if (carIdString != null) {
            get_PrintCar(carIdString, request, response);
            return;
        }

        String orderIdString = request.getParameter("id");
        if (orderIdString != null) {
            get_PrintOrder(orderIdString, request, response);
            return;
        }

        HttpSession session = request.getSession();
        if (session.getAttribute("car") != null) {
            request.setAttribute("car", session.getAttribute("car"));
            session.removeAttribute("car");
            request.getRequestDispatcher("/WEB-INF/order.jsp").forward(request, response);
            return;
        }

        response.sendError(400);

    }

    private void get_PrintCar(String idString, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int carId = Integer.parseInt(idString);
        if (carId > 0) {
            Car car = DAOFactory.getInstance().getCarDAO().getById(carId);
            if (car == null) {
                response.sendError(400);
                return;
            }

            User user = (User) request.getAttribute("user");
            if (user == null) {
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

    private void get_PrintOrder(String idString, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(idString);
        if (id > 0) {
            Order order = DAOFactory.getInstance().getOrderDAO().getById(id);
            Invoice invoice = DAOFactory.getInstance().getInvoiceDAO().getById(Integer.parseInt(request.getParameter("invoice_id")));
            if (order == null) {
                response.sendError(400);
                return;
            }
            request.setAttribute("order", order);
            request.setAttribute("invoice", invoice);
            request.getRequestDispatcher("/WEB-INF/order-new.jsp").forward(request, response);
        } else {
            response.sendError(400);
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
    private void handlePostRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
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
        if (user == null) {
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

        // create order
        Order order = new Order();
        order.setUser(user);
        order.setWithDriver(withDriver);
        order.setLeaseTerm(leaseTerm);
        order.setPassportNumber(passportNumber);
        order.setPassportValid(passportValid);
        order.setCar(car);
        order.setPrice(price);
        DAOFactory.getInstance().getOrderDAO().insert(order);

        Invoice invoice = new Invoice();
        invoice.setType(Invoice.Type.RENT);
        invoice.setUser(order.getUser());
        invoice.setOrder(order);
        invoice.setAmount(order.getPrice());
        DAOFactory.getInstance().getInvoiceDAO().insert(invoice);

        response.sendRedirect("order?id=" + order.getId()+"&invoice_id="+invoice.getId());
    }
}