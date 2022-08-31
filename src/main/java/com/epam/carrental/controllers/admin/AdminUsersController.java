package com.epam.carrental.controllers.admin;

import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.entity.Car;
import com.epam.carrental.entity.Brand;
import com.epam.carrental.entity.CarQuality;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/admin/users")
public class AdminUsersController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/admin/users.jsp").forward(request, response);
    }

    private void getCars(HttpServletRequest request, HttpServletResponse response) {

        try {
            request.setAttribute("cars", DAOFactory.getInstance().getCarDAO().getAll());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            request.getRequestDispatcher("WEB-INF/cars.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void getCarsBrands(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("carsBrands", DAOFactory.getInstance().getBrandDAO().getAll());
            request.getRequestDispatcher("WEB-INF/list.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                new CarQuality(),
                new Brand(Integer.parseInt(carBrandId))
        );

        try {
            DAOFactory.getInstance().getCarDAO().create(car);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
