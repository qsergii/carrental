package com.epam.carrental.controllers.user;

import com.epam.carrental.DbException;
import com.epam.carrental.controllers.Controller;
import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.entity.Car;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


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

        CarsInfo carsInfo = DAOFactory.getInstance().getCarDAO().getAll(brand, quality, sort, page);

        request.setAttribute("page", carsInfo.getPage());
        request.setAttribute("pageCount", carsInfo.getPageCount());
        request.setAttribute("cars", carsInfo.getCars());

        request.setAttribute("brands", DAOFactory.getInstance().getBrandDAO().getAllAvailible());
        request.setAttribute("qualities", DAOFactory.getInstance().getQualityDAO().getAllAvailible());
        request.getRequestDispatcher("/WEB-INF/user/home.jsp").forward(request, response);
    }

    public class CarsInfo{
        int pageCount;
        int page;
        List<Car> cars;

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getPage() {
            return Math.min(page, pageCount);
        }

        public void setPage(int page) {
            this.page = page;
        }

        public List<Car> getCars() {
            return cars;
        }

        public void setCars(List<Car> cars) {
            this.cars = cars;
        }
    }
}

//        ResourceBundle bundle = ResourceBundle.getBundle("resources");
//        ResourceBundle bundleUa = ResourceBundle.getBundle("resources_ua", new Locale("uk", "UA"));
//        request.setAttribute("auth", bundle.getString("auth"));
