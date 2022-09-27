package com.epam.carrental.controllers.admin;

import com.epam.carrental.controllers.Controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AdminController implements Controller {

    static Logger log = LogManager.getLogger(AdminController.class.getName());

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("redirect");
        response.sendRedirect("admin/cars");
    }
//
//    @Override
//    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String page = Optional.ofNullable(request.getParameter("page")).orElse("");
//        if (page.equals("cars")) {
//            String action = Optional.ofNullable(request.getParameter("action")).orElse("");
//            if (action.equals("add")) {
//                postCarAdd(request, response);
//            } else if (action.equals("carsBrands-add")) {
//                postCarsBrandsAdd(request, response);
//            } else {
//                response.sendRedirect("admin");
//            }
//        } else {
//            response.sendRedirect("admin");
//        }
//    }
//
//    private void postCarAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String name = request.getParameter("name");
//        String carBrandId = request.getParameter("carBrand");
//        float price = Float.valueOf(request.getParameter("price"));
//        String description = request.getParameter("description");
//        boolean blocked = Optional.ofNullable(request.getParameter("blocked")).orElse("").equals("on") ? true : false;
//
//        Car car = new Car();
//        car.setId(0);
//        car.setName(name);
//        car.setDescription(description);
//        car.setBlocked(blocked);
//        car.setPrice(price);
//        car.setQuality(new Quality());
//        car.setBrand(new Brand(Integer.parseInt(carBrandId)));
//
//        DAOFactory.getInstance().getCarDAO().insert(car);
//        response.sendRedirect("admin?page=cars");
//    }
//
//    private void postCarsBrandsAdd(HttpServletRequest request, HttpServletResponse response) {
//        String name = request.getParameter("name");
//        Brand carBrand = new Brand(name);
//        try {
//            DAOFactory.getInstance().getBrandDAO().create(carBrand);
//            response.sendRedirect("admin?page=carsBrands");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
