package com.epam.carrental.controllers.user;

import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.entity.Invoice;
import com.epam.carrental.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

@WebServlet("/invoices")
public class InvoicesController extends HttpServlet {
    private final Logger log = LogManager.getLogger(this.getClass());

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
        String idString = request.getParameter("id");
        if (idString == null || idString.isEmpty()) {
            printList(request, response);
        } else {
            printOne(request, response);
        }
    }
    private void printList(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("invoices", DAOFactory.getInstance().getInvoiceDAO().getAll());
            request.getRequestDispatcher("/WEB-INF/user/invoices.jsp").forward(request, response);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
    private void printOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderIdString = request.getParameter("id");
        Invoice invoice;
        int id = Integer.parseInt(orderIdString);
        if (id > 0) {
            invoice = DAOFactory.getInstance().getInvoiceDAO().getByIdAndUser(id, (User) request.getAttribute("user"));
        } else {
            invoice = new Invoice();
        }
        request.setAttribute("invoice", invoice);
        request.getRequestDispatcher("/WEB-INF/user/invoice.jsp").forward(request, response);
    }

    /* POST */

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
        String action = request.getParameter("action");
        if(action == null){
            response.sendError(400);
            return;
        }
        switch (action){
            case "pay":
                pay(request, response);
                break;
            default:
                response.sendError(400);
        }
    }


    private void pay(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String orderIdString = request.getParameter("id");
        if (orderIdString == null || orderIdString.isEmpty()) {
            response.sendError(400);
            return;
        }

        Invoice invoice = DAOFactory.getInstance().getInvoiceDAO().getById(Integer.parseInt(orderIdString));
        invoice.setPayed(true);
        invoice.setPayedDate(new Date());
        DAOFactory.getInstance().getInvoiceDAO().update(invoice);

        response.sendRedirect("invoices?id="+invoice.getId());
    }
}
