package com.epam.carrental.controllers.admin;

import com.epam.carrental.Logging;
import com.epam.carrental.controllers.Controller;
import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.dao.DBException;
import com.epam.carrental.dao.entity.Brand;
import com.epam.carrental.dao.entity.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class AdminBrandsController implements Controller {
    private final Logger log = LogManager.getLogger(getClass());

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id") != null) {
            printCard(request, response);
        } else {
            printList(request, response);
        }
    }

    private void printList(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            request.setAttribute("page", "brands");
            request.setAttribute("brands", DAOFactory.getInstance().getBrandDAO().getAll());
            request.getRequestDispatcher("/WEB-INF/admin/brands.jsp").forward(request, response);
        } catch (IOException e) {
            log.error(Logging.makeDescription(e));
        }
    }

    private void printCard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Brand brand;
        List<Car> cars = null;

        int id = Integer.parseInt(request.getParameter("id"));
        if (id != 0) {
            brand = DAOFactory.getInstance().getBrandDAO().getById(id);
            cars = DAOFactory.getInstance().getCarDAO().getByBrand(brand);
        } else {
            brand = new Brand();
        }
        request.setAttribute("brand", brand);
        request.setAttribute("cars", cars);
        request.getRequestDispatcher("/WEB-INF/admin/brand.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, DBException {
        Brand brand;

        int id = Integer.parseInt(Optional.ofNullable(request.getParameter("id")).orElse("0"));
        if (id > 0) {
            brand = DAOFactory.getInstance().getBrandDAO().getById(id);
            if (brand == null) {
                log.error("can't find brand by id " + id);
                response.sendError(500);
                return;
            }
        } else {
            brand = new Brand();
        }

        String action = Optional.ofNullable(request.getParameter("action")).orElse("");
        if (action.equals("delete")) {
            deleteBrand(request, response, brand);
        } else if (action.equals("add")) {
            String name = request.getParameter("name");
            if (name == null || name.isEmpty()) {
                response.sendRedirect("brand?message=name can't be empty");
                return;
            }
            brand.setName(name);
            try {
                if (brand.getId() == 0) {
                    DAOFactory.getInstance().getBrandDAO().insert(brand);
                } else {
                    DAOFactory.getInstance().getBrandDAO().update(brand);
                }
                response.sendRedirect("brands");
            } catch (DBException e) {
                log.error(Logging.makeDescription(e));
                response.sendRedirect("brands?id=" + brand.getId() + "&message=" + e.getMessage());
            }
        }
    }

    private void deleteBrand(HttpServletRequest request, HttpServletResponse response, Brand brand) throws IOException {
        try {
            DAOFactory.getInstance().getBrandDAO().delete(brand);
            response.getWriter().print("ok");
        } catch (DBException e) {
            log.error("Can't delete brand");
            response.getWriter().print(e.getMessage());
        }
    }

}
