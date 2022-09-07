package com.epam.carrental.controllers.admin;

import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.entity.Brand;
import com.epam.carrental.entity.Car;
import com.epam.carrental.entity.CarQuality;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

@WebServlet("/admin")
public class AdminController extends HttpServlet {

    static Logger log = Logger.getLogger(AdminController.class.getName());

    static{
        PropertyConfigurator.configure("log4j.properties");
//        PropertyConfigurator.configure(getClass().getResource("/controlador/log4j.properties"));
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("admin/cars");
        log.info("redirect");
//        request.getRequestDispatcher("admin/cars").forward(request, response);


//        String page = Optional.ofNullable(request.getParameter("page")).orElse("");
//        String jspName = null;
//        switch (page) {
//            case "cars":
//                String action = Optional.ofNullable(request.getParameter("action")).orElse("");
//                switch (action) {
//                    case "add":
//                        try {
//                            request.setAttribute("carsBrands", DAOFactory.getInstance().getCarBrandDAO().getAll());
//                        } catch (Exception e) {
//                            throw new RuntimeException(e);
//                        }
//                        jspName = "WEB-INF/admin-cars-brand.jsp";
//                        break;
//                    case "carsBrands-add":
//                        jspName = "WEB-INF/brand.jsp";
//                        break;
//                    default:
//                        getCars(request, response);
//                        return;
//                }
//                break;
//            case "carsBrands":
//                getCarsBrands(request, response);
//                return;
//            case "users":
//                jspName = "WEB-INF/admin/users.jsp";
//                break;
//            case "managers":
//                jspName = "WEB-INF/admin/admin-managers.jsp";
//                break;
//            default:
//                jspName = "WEB-INF/admin/cars.jsp";
//        }
//        request.getRequestDispatcher(jspName).forward(request, response);
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
            request.getRequestDispatcher("WEB-INF/mainlist.jsp").forward(request, response);
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
            DAOFactory.getInstance().getCarDAO().insert(car);
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
