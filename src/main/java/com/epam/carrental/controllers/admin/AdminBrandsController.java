package com.epam.carrental.controllers.admin;

import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.entity.Brand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/admin/brands")
public class AdminBrandsController extends HttpServlet {
    private final Logger log = LogManager.getLogger(getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = Optional.ofNullable(request.getParameter("action")).orElse("");
        if (action.equals("add")) {
            try {
                request.setAttribute("brand", new Brand());
                request.getRequestDispatcher("/WEB-INF/admin/brand.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (action.equals("edit")) {
            try {
                int id = Integer.parseInt(Optional.ofNullable(request.getParameter("id")).orElse(""));
                Brand brand = DAOFactory.getInstance().getBrandDAO().getById(id);
                request.setAttribute("brand", brand);
                request.getRequestDispatcher("/WEB-INF/admin/brand.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                request.setAttribute("page", "brands");
                request.setAttribute("brands", DAOFactory.getInstance().getBrandDAO().getAll());
                request.getRequestDispatcher("/WEB-INF/admin/brands.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        try {
            handlePost(request, response);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    private void handlePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
            return;
        }

        String name = request.getParameter("name");
        if (name == null || name.isEmpty()) {
            response.sendRedirect("brand?message=name can't be empty");
            return;
        }
        brand.setName(name);
        if (brand.getId() == 0) {
            DAOFactory.getInstance().getBrandDAO().create(brand);
        } else {
            DAOFactory.getInstance().getBrandDAO().update(brand);
        }
        response.sendRedirect("brands");
    }

    private void deleteBrand(HttpServletRequest request, HttpServletResponse response, Brand brand) throws IOException {
        DAOFactory.getInstance().getBrandDAO().delete(brand);
        response.sendRedirect("brands");
    }

}
