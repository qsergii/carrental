package com.epam.carrental.controllers.user;

import com.epam.carrental.controllers.Controller;
import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.dao.entity.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


public class HomeController implements Controller {

    private final Logger log = LogManager.getLogger(this.getClass());


    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        try {
            handleGet(request, response);
        } catch (ServletException | IOException e) {
            log.error(e.getMessage());
            try{
                response.sendError(500);
            }catch (IOException ioException){
                log.error(ioException.getMessage());
            }
        }
    }

    private static void handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sort = request.getParameter("sort");
        String brand = request.getParameter("brand");
        String quality = request.getParameter("quality");

        int page = Integer.parseInt(Optional.ofNullable(request.getParameter("page")).orElse("1"));

        Object[] values = DAOFactory.getInstance().getCarDAO().getAll(brand, quality, sort, page);
        request.setAttribute("pageCount", (int)values[0]);
        request.setAttribute("page", (int)values[1]);
        request.setAttribute("cars", (List<Car>)values[2]);

        request.setAttribute("brands", DAOFactory.getInstance().getBrandDAO().getAllAvailible());
        request.setAttribute("qualities", DAOFactory.getInstance().getQualityDAO().getAllAvailible());
        request.getRequestDispatcher("/WEB-INF/user/home.jsp").forward(request, response);
    }

}


