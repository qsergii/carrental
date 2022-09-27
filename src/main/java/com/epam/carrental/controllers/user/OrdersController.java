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

public class OrdersController implements Controller {
    private final Logger log = LogManager.getLogger(getClass());

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getParameter("id") != null) {
            printOrder(request, response);
        } else {
            printOrders(request, response);
        }
    }

    private void printOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderIdString = request.getParameter("id");
        Order order;
        int id = Integer.parseInt(orderIdString);
        if (id > 0) {
            order = DAOFactory.getInstance().getOrderDAO().getById(id);
            request.setAttribute("order", order);
            request.getRequestDispatcher("/WEB-INF/user/order.jsp").forward(request, response);
        } else {
            order = new Order();
            request.setAttribute("order", order);
            request.getRequestDispatcher("/WEB-INF/user/order-confirm.jsp").forward(request, response);
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
}
