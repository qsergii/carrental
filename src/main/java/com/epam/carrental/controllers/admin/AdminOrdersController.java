package com.epam.carrental.controllers.admin;

import com.epam.carrental.controllers.Controller;
import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.dao.DBException;
import com.epam.carrental.dao.entity.Invoice;
import com.epam.carrental.dao.entity.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class AdminOrdersController implements Controller {
    private final Logger log = LogManager.getLogger(getClass());

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, DBException {
        String dispatcher;
        if (request.getParameter("id") != null) {
            dispatcher = printOrder(request, response);
        } else {
            dispatcher = printList(request, response);
        }
        request.getRequestDispatcher(dispatcher).forward(request, response);
    }

    private String printList(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("orders", DAOFactory.getInstance().getOrderDAO().getAll());
        return "/WEB-INF/admin/orders.jsp";
    }

    private String printOrder(HttpServletRequest request, HttpServletResponse response) throws DBException {
        String orderIdString = request.getParameter("id");
        Order order;
        int id = Integer.parseInt(orderIdString);
        if (id > 0) {
            order = DAOFactory.getInstance().getOrderDAO().getById(id);
        } else {
            order = new Order();
        }
        request.setAttribute("order", order);
        request.setAttribute("invoices", DAOFactory.getInstance().getInvoiceDAO().getByOrder(order));

        return "/WEB-INF/admin/order.jsp";
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        if (action == null) {
            response.sendError(400);
            return;
        }
        switch (action) {
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
        if (amount != null && !amount.isEmpty()) {
            Invoice invoice = new Invoice();
            invoice.setType(Invoice.Type.DAMAGE);
            invoice.setUser(order.getUser());
            invoice.setOrder(order);
            invoice.setAmount(Float.parseFloat(amount));
            DAOFactory.getInstance().getInvoiceDAO().insert(invoice, null);
        }

        response.sendRedirect("orders");
    }

}
