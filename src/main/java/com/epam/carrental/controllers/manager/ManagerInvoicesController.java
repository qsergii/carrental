package com.epam.carrental.controllers.manager;

import com.epam.carrental.controllers.Controller;
import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.dao.entity.Invoice;
import com.epam.carrental.export.Export;
import com.epam.carrental.export.Exporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ManagerInvoicesController implements Controller {
    private final Logger log = LogManager.getLogger(this.getClass());

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(request.getParameter("export") != null){
            export(request, response);
        }else if(request.getParameter("id") != null){
            printOne(request, response);
        } else {
            printList(request, response);
        }
    }

    private void export(HttpServletRequest request, HttpServletResponse response) {
        String export = request.getParameter("export");
        if(export != null){
            List<Invoice> invoices = DAOFactory.getInstance().getInvoiceDAO().getAll();
            Exporter exporter = Export.export(export);
            exporter.export(request, response, invoices);
        }
    }

    private void printOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderIdString = request.getParameter("id");
        Invoice invoice;
        int id = Integer.parseInt(orderIdString);
        if (id > 0) {
            invoice = DAOFactory.getInstance().getInvoiceDAO().getById(id);
        } else {
            invoice = new Invoice();
        }
        request.setAttribute("invoice", invoice);
        request.getRequestDispatcher("/WEB-INF/manager/invoice.jsp").forward(request, response);
    }

    private void printList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("invoices", DAOFactory.getInstance().getInvoiceDAO().getAll());
        request.getRequestDispatcher("/WEB-INF/manager/invoices.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String action = request.getParameter("action");
        if (action == null) {
            response.sendError(400);
            return;
        }
        switch (action) {
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

        response.sendRedirect("invoices?id=" + invoice.getId());
    }
}
