package com.epam.carrental.controllers;

import com.epam.carrental.Logging;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
* Main controller
 * receive all request, map dispatcher, take processing to it
 * */
@WebServlet("/")
public class FrontController extends HttpServlet {

    private static final Map<String, Class<?>> dispatchers = new HashMap<>();
    private final Logger log = LogManager.getLogger(getClass());

    static {
        dispatchers.put("/", com.epam.carrental.controllers.user.HomeController.class);

        // user
        dispatchers.put("/home", com.epam.carrental.controllers.user.HomeController.class);
        dispatchers.put("/user", com.epam.carrental.controllers.user.UserController.class);
        dispatchers.put("/car", com.epam.carrental.controllers.user.CarController.class);
        dispatchers.put("/create-order", com.epam.carrental.controllers.user.CreateOrderController.class);
        dispatchers.put("/orders", com.epam.carrental.controllers.user.UserOrdersController.class);
        dispatchers.put("/invoices", com.epam.carrental.controllers.user.InvoicesController.class);

        // auth
        dispatchers.put("/login", com.epam.carrental.controllers.auth.LoginController.class);
        dispatchers.put("/logout", com.epam.carrental.controllers.auth.LogoutController.class);
        dispatchers.put("/registration", com.epam.carrental.controllers.auth.RegistrationController.class);

        // manager
        dispatchers.put("/manager/orders", com.epam.carrental.controllers.manager.ManagerOrdersController.class);

        // admin
        dispatchers.put("/admin", com.epam.carrental.controllers.admin.AdminController.class);
        dispatchers.put("/admin/cars", com.epam.carrental.controllers.admin.AdminCarsController.class);
        dispatchers.put("/admin/brands", com.epam.carrental.controllers.admin.AdminBrandsController.class);
        dispatchers.put("/admin/qualities", com.epam.carrental.controllers.admin.AdminQualityController.class);
        dispatchers.put("/admin/driver", com.epam.carrental.controllers.admin.AdminDriverController.class);
        dispatchers.put("/admin/users", com.epam.carrental.controllers.admin.AdminUsersController.class);
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        doMethod((HttpServletRequest) req, (HttpServletResponse)res);
    }

    private void doMethod(HttpServletRequest request, HttpServletResponse response){
        log.trace("service");

        String uri = StringUtils.difference((String) getServletContext().getAttribute("path"), request.getRequestURI());
        request.setAttribute("uri", uri);

        Class<?> dispatcher = dispatchers.get(uri);
        if (dispatcher != null) {
            try {
                Controller instance = (Controller) dispatcher.getDeclaredConstructor().newInstance();
                switch (request.getMethod()) {
                    case "GET":
                        instance.doGet(request, response);
                        break;
                    case "POST":
                        instance.doPost(request, response);
                        break;
                    default:
                        response.sendError(405);
                }
            } catch (Exception exception) {
                ProcessInternalError(response, exception);
            }
        } else {
            try {
                response.sendError(404);
            } catch (IOException exception) {
                ProcessInternalError(response, exception);
            }
        }
    }

    public void ProcessInternalError(HttpServletResponse response, Exception exception){
        log.error(Logging.makeDescription(exception));
        try {
            response.sendError(500);
        }catch (IOException exceptionSendError){
            log.error(Logging.makeDescription(exceptionSendError));
        }
    }
}
