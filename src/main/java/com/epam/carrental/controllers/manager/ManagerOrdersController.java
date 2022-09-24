package com.epam.carrental.controllers.manager;

import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.dao.entity.Invoice;
import com.epam.carrental.dao.entity.Order;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/manager/orders")
public class ManagerOrdersController extends HttpServlet {
    private final Logger log = LogManager.getLogger(getClass());

    /* GET */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
    private void handleGetRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderIdString = request.getParameter("id");
        if (orderIdString == null || orderIdString.isEmpty()) {
            printOrders(request, response);
        } else {
            printOrder(orderIdString, request, response);
        }
    }
    private void printOrders(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("orders", DAOFactory.getInstance().getOrderDAO().getAll());
            request.getRequestDispatcher("/WEB-INF/manager/orders.jsp").forward(request, response);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
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
        request.getRequestDispatcher("/WEB-INF/manager/order-confirm.jsp").forward(request, response);
    }

    /* POST */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
            handlePostRequest(request, response);
        } catch (Exception e) {
            log.error(e.getStackTrace() +", "+ e.getMessage());
            try {
                response.sendError(500);
            } catch (IOException e2) {
                log.error(e2.getMessage());
            }
        }
    }

    private void handlePostRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String action = request.getParameter("action");
        if(action == null){
            response.sendError(400);
            return;
        }
        switch (action){
            case "reject":
                reject(request, response);
                break;
            case "return":
                returnCar(request, response);
                break;
            default:
                response.sendError(400);
        }
    }

    private void reject(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String orderIdString = request.getParameter("id");
        String reason = request.getParameter("reason");
        if (orderIdString == null || orderIdString.isEmpty()) {
            response.sendError(400);
            return;
        }

        int id = Integer.parseInt(orderIdString);
        Order order = DAOFactory.getInstance().getOrderDAO().getById(id);
        order.setRejected(true);
        order.setRejectReason(reason);
        DAOFactory.getInstance().getOrderDAO().update(order);

        response.sendRedirect("orders");
    }

    private void returnCar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String orderIdString = request.getParameter("id");
        String damage = request.getParameter("reason");
        String amount = request.getParameter("amount");
        if (orderIdString == null || orderIdString.isEmpty()) {
            response.sendError(400);
            return;
        }

        int id = Integer.parseInt(orderIdString);
        Order order = DAOFactory.getInstance().getOrderDAO().getById(id);
        order.setDateReturn(new Date());
        order.setReturnDamage(damage);
        DAOFactory.getInstance().getOrderDAO().update(order);
        if(amount != null && !amount.isEmpty()){
            Invoice invoice = new Invoice();
            invoice.setType(Invoice.Type.DAMAGE);
            invoice.setUser(order.getUser());
            invoice.setOrder(order);
            invoice.setAmount(Float.parseFloat(amount));
            DAOFactory.getInstance().getInvoiceDAO().insert(invoice);
        }

        response.sendRedirect("orders");
    }

}
