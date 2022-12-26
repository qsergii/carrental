package com.epam.carrental.controllers.user;

import com.epam.carrental.CarDriver;
import com.epam.carrental.Logging;
import com.epam.carrental.controllers.Controller;
import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.dao.DBException;
import com.epam.carrental.dao.Database;
import com.epam.carrental.dao.entity.Car;
import com.epam.carrental.dao.entity.Invoice;
import com.epam.carrental.dao.entity.Order;
import com.epam.carrental.dao.entity.User;
import org.apache.commons.dbutils.DbUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class OrdersController implements Controller {
    private final Logger log = LogManager.getLogger(getClass());

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, DBException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null && session.getAttribute("car") != null) {
            Car car = (Car) session.getAttribute("car");
            session.removeAttribute("car");
            response.sendRedirect("orders?car_id=" + car.getId());
        } else if (request.getParameter("id") != null) {
            printOrder(request, response);
        } else if (request.getParameter("car_id") != null) {
            printNewOrder(request, response);
        } else {
            printList(request, response);
        }
    }

    private void printOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
        String orderIdString = request.getParameter("id");
        Order order;
        int id = Integer.parseInt(orderIdString);
        if (id > 0) {
            order = DAOFactory.getInstance().getOrderDAO().getById(id);
            request.setAttribute("order", order);
            request.setAttribute("invoices", DAOFactory.getInstance().getInvoiceDAO().getByOrder(order));
            request.getRequestDispatcher("/WEB-INF/user/order.jsp").forward(request, response);
        } else {
            order = new Order();
            request.setAttribute("order", order);
            request.getRequestDispatcher("/WEB-INF/user/order-new.jsp").forward(request, response);
        }
    }

    private void printList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getAttribute("authUser");
        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        try {
            request.setAttribute("orders", DAOFactory.getInstance().getOrderDAO().getByUser(user));
            request.getRequestDispatcher("/WEB-INF/user/orders.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
            log.error(Logging.makeDescription(e));
        }
    }

    private void printNewOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String idString = request.getParameter("car_id");
        int carId = Integer.parseInt(idString);
        if (carId > 0) {
            Car car = DAOFactory.getInstance().getCarDAO().getById(carId, true);
            if (car == null) {
                response.sendError(400);
                return;
            }

            User user = (User) request.getAttribute("authUser");
            if (user == null) {
                HttpSession session = request.getSession();
                session.setAttribute("car", car);
                response.sendRedirect("login");
                return;
            }

            request.setAttribute("car", car);
            request.setAttribute("user", user);
            request.setAttribute("driverPrice", CarDriver.getPrice());
            request.getRequestDispatcher("/WEB-INF/user/order-new.jsp").forward(request, response);
        } else {
            response.sendError(400);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException, SQLException {

        User user;
        Date leaseBegin;
        Date leaseFinish;
        int leaseTerm;
        boolean withDriver;
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
        car = DAOFactory.getInstance().getCarDAO().getById(carId, false);
        user = (User) request.getAttribute("authUser");
        if (user == null) {
            HttpSession session = request.getSession();
            session.setAttribute("car", car);
            response.sendRedirect("login");
            return;
        }
        withDriver = request.getParameter("with-driver").equals("with-driver");
        leaseBegin = parseDate(request.getParameter("lease_begin"));
        leaseFinish = parseDate(request.getParameter("lease_finish"));
        leaseTerm = Integer.parseInt(request.getParameter("lease-term"));
        passportNumber = request.getParameter("passport-number");
        passportValid = parseDate(request.getParameter("passport-valid"));

        price = Float.parseFloat(request.getParameter("price"));
        if (price != car.getPrice()) {
            response.sendError(400);
            return;
        }

        float ammount = price;
        if (withDriver) {
            ammount += CarDriver.getPrice();
        }
        ammount *= leaseTerm;

        Order order = new Order();
        order.setUser(user);
        order.setLeaseBegin(leaseBegin);
        order.setLeaseFinish(leaseFinish);
        order.setLeaseTerm(leaseTerm);
        order.setWithDriver(withDriver);
        order.setPassportNumber(passportNumber);
        order.setPassportValid(passportValid);
        order.setCar(car);
        order.setPrice(ammount);

        Connection connection = null;
        try {
            connection = Database.dataSource.getConnection();
            connection.setAutoCommit(false);

            DAOFactory.getInstance().getOrderDAO().insert(order, connection);

            Invoice invoice = new Invoice(order, Invoice.Type.RENT);
            DAOFactory.getInstance().getInvoiceDAO().insert(invoice, connection);
            connection.commit();
            savePassportInformationToUser(user, passportNumber, passportValid);
            response.sendRedirect("orders?id=" + order.getId());
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            log.error(Logging.makeDescription(e));
            response.sendError(500);
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    private void savePassportInformationToUser(User user, String passportNumber, Date passportValid) {
        user.setPassportNumber(passportNumber);
        user.setPassportValid(passportValid);
        DAOFactory.getInstance().getUserDAO().update(user);
    }

    private Date parseDate(String input) throws ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        return parser.parse(input);
    }

}
