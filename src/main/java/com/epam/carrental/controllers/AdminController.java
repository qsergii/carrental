package com.epam.carrental.controllers;

import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.entity.Brand;
import com.epam.carrental.entity.Car;
import com.epam.carrental.entity.CarQuality;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/admin")
public class AdminController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = Optional.ofNullable(request.getParameter("page")).orElse("");
        String jspName = null;
        switch (page) {
            case "cars":
                String action = Optional.ofNullable(request.getParameter("action")).orElse("");
                switch (action){
                    case "add":
                        jspName = "WEB-INF/admin-cars-add.jsp";
                        break;
                    default:
                        jspName = "WEB-INF/admin-cars.jsp";
                }
                break;
            case "users":
                jspName = "WEB-INF/admin-users.jsp";
                break;
            case "managers":
                jspName = "WEB-INF/admin-managers.jsp";
                break;
            default:
                jspName = "WEB-INF/admin-cars.jsp";
        }
        request.getRequestDispatcher(jspName).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = Optional.ofNullable(request.getParameter("page")).orElse("");
        if(page.equals("cars")){
            String action = Optional.ofNullable(request.getParameter("action")).orElse("");
            if(action.equals("add")){
                String name = request.getParameter("name");
                float price = Float.valueOf(request.getParameter("price"));
                String description = request.getParameter("description");

                Car car = new Car(0, name, "", price, new Brand(), new CarQuality());

                try {
                    DAOFactory.getInstance().getCarDAO().create(car);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }else {
                response.sendRedirect("admin");
            }
        }else{
            response.sendRedirect("admin");
        }
    }
}
