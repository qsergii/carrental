package com.epam.carrental.controllers.user;

import com.epam.carrental.Logging;
import com.epam.carrental.controllers.Controller;
import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.dao.entity.Order;
import com.epam.carrental.dao.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserOrdersController implements Controller {
    private final Logger log = LogManager.getLogger(getClass());

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            handleGetRequest(request, response);
        } catch (IOException | ServletException e) {
            log.error(e.getMessage());
            try {
                response.sendError(500);
            } catch (IOException e2) {
                log.error(e2.getMessage());
            }
        }
    }
    private void handleGetRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderIdString = request.getParameter("id");
        if (orderIdString == null || orderIdString.isEmpty()) {
            printOrders(request, response);
        } else {
            printOrder(orderIdString, request, response);
        }
    }

    private void printOrders(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getAttribute("authUser");
        if(user == null){
            response.sendRedirect("login");
            return;
        }

        try {
            request.setAttribute("orders", DAOFactory.getInstance().getOrderDAO().getByUser(user));
            request.getRequestDispatcher("/WEB-INF/user/orders.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
            log.error(e.getMessage());
            log.error(Logging.makeDescription(e));
        }
    }
    private void printOrder(String orderIdString, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order order;
        int id = Integer.parseInt(orderIdString);
        if (id > 0) {
            order = DAOFactory.getInstance().getOrderDAO().getById(id);
        } else {
            order = new Order();
        }
        request.setAttribute("order", order);
        request.getRequestDispatcher("/WEB-INF/user/order-confirm.jsp").forward(request, response);
    }
}
