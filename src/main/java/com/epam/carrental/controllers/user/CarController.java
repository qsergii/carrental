package com.epam.carrental.controllers.user;

import com.epam.carrental.Logging;
import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.dao.entity.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/car")
public class CarController extends HttpServlet {
    public final Logger log = LogManager.getLogger(this.getClass());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            handleGetRequest(request, response);
        }catch (Exception e){
            log.error(Logging.makeDescription(e));
            try{
                response.sendError(500);
            }catch (Exception e2){
                log.error(Logging.makeDescription(e2));
            }
        }
    }

    private void handleGetRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(Optional.ofNullable(request.getParameter("id")).orElse("0"));
        if (id > 0){
            printCar(id, request, response);
        }else {
            response.sendError(404);
        }
    }
    private void printCar(int id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Car car = DAOFactory.getInstance().getCarDAO().getById(id);
        request.setAttribute("car", car);
        request.setAttribute("title", car.getName());
        request.getRequestDispatcher("/WEB-INF/user/car.jsp").forward(request, response);
    }
}
