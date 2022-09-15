package com.epam.carrental.controllers.admin;

import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.entity.Brand;
import com.epam.carrental.entity.Car;
import com.epam.carrental.entity.Quality;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/admin/cars")
public class AdminCarsController extends HttpServlet {

    private final Logger log = LogManager.getLogger(this.getClass());

    /* EXTERNAL */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            handleGet(request, response);
        } catch (Exception e) {
            log.error(e.getMessage());
            response.sendError(500);
        }
    }

    private void handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paramId = request.getParameter("id");
        if (paramId == null || paramId.isEmpty()) {
            printCars(request, response);
        } else {
            printCar(paramId, request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try{
            handlePost(request, response);
        }catch (Exception e){
            log.error(e.getMessage());
            try {
                response.sendError(500);
            }catch (IOException ex) {
                log.error(ex.getMessage());
            }
        }
    }

    private void handlePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // properties
        int id = Integer.valueOf(Optional.ofNullable(request.getParameter("id")).orElse("-1"));
        String name = request.getParameter("name");
        String brandId = request.getParameter("brand");
        String qualityId = request.getParameter("quality");
        Brand brand = DAOFactory.getInstance().getBrandDAO().getById(Integer.parseInt(brandId));
        Quality quality = DAOFactory.getInstance().getQualityDAO().getById(Integer.parseInt(qualityId));
        String description = request.getParameter("description");
        boolean blocked = Optional.ofNullable(request.getParameter("blocked")).orElse("").equals("on") ? true : false;
        float price = Float.valueOf(request.getParameter("price"));

        Car car;
        if (id == 0 ) {
            car = new Car();
        } else {
            car = DAOFactory.getInstance().getCarDAO().getById(id);
            if(car == null){
                response.sendError(400);
                return;
            }
        }
        car.setName(name);
        car.setDescription(description);
        car.setBlocked(blocked);
        car.setPrice(price);
        car.setQuality(new Quality());
        car.setBrand(brand);
        car.setQuality(quality);

        if(car.getId() == 0){
            DAOFactory.getInstance().getCarDAO().insert(car);
        }else{
            DAOFactory.getInstance().getCarDAO().update(car);
        }

        response.sendRedirect("cars");
    }

    /* INTERNAL */

    private void printCars(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("page", "cars");
            request.setAttribute("cars", DAOFactory.getInstance().getCarDAO().getAll());
            request.getRequestDispatcher("/WEB-INF/admin/cars.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printCar(String paramId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Car car;
        int id = Integer.parseInt(paramId);
        if (id > 0) {
            car = DAOFactory.getInstance().getCarDAO().getById(id);
        } else {
            car = new Car();
        }
        request.setAttribute("car", car);
        request.setAttribute("brands", DAOFactory.getInstance().getBrandDAO().getAll());
        request.setAttribute("qualities", DAOFactory.getInstance().getQualityDAO().getAll());
        request.getRequestDispatcher("/WEB-INF/admin/car.jsp").forward(request, response);
    }

}
