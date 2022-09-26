package com.epam.carrental.controllers.admin;

import com.epam.carrental.controllers.Controller;
import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.dao.entity.Brand;
import com.epam.carrental.dao.entity.Car;
import com.epam.carrental.dao.entity.Quality;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


public class AdminController implements Controller {

    static Logger log = LogManager.getLogger(AdminController.class.getName());

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("admin/cars");
        log.info("redirect");

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = Optional.ofNullable(request.getParameter("page")).orElse("");
        if (page.equals("cars")) {
            String action = Optional.ofNullable(request.getParameter("action")).orElse("");
            if (action.equals("add")) {
                postCarAdd(request, response);
            } else if (action.equals("carsBrands-add")) {
                postCarsBrandsAdd(request, response);
            } else {
                response.sendRedirect("admin");
            }
        } else {
            response.sendRedirect("admin");
        }
    }

    private void postCarAdd(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String carBrandId = request.getParameter("carBrand");
        float price = Float.valueOf(request.getParameter("price"));
        String description = request.getParameter("description");
        boolean blocked = Optional.ofNullable(request.getParameter("blocked")).orElse("").equals("on") ? true : false;

        Car car = new Car(
                0,
                name,
                description,
                blocked,
                price,
                new Quality(),
                new Brand(Integer.parseInt(carBrandId))
        );

        DAOFactory.getInstance().getCarDAO().insert(car);
        try {
            response.sendRedirect("admin?page=cars");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void postCarsBrandsAdd(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        Brand carBrand = new Brand(name);
        try {
            DAOFactory.getInstance().getBrandDAO().create(carBrand);
            response.sendRedirect("admin?page=carsBrands");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
