package com.epam.carrental.controllers;

import com.epam.carrental.Exceptions;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
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
@MultipartConfig
public class FrontController extends HttpServlet {

    private static final Map<String, Class<?>> dispatchers = new HashMap<>();
    private final Logger log = LogManager.getLogger(getClass());

    static {
        dispatchers.put("/", com.epam.carrental.controllers.user.HomeController.class);
        dispatchers.put("/home", com.epam.carrental.controllers.user.HomeController.class);
        dispatchers.put("/admin/cars", com.epam.carrental.controllers.admin.AdminCarsController.class);
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
//        super.service(req, res);
        doMethod((HttpServletRequest) req, (HttpServletResponse)res);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
    }

    private void doMethod(HttpServletRequest request, HttpServletResponse response){
        log.trace("service");

        String uri = StringUtils.difference((String) getServletContext().getAttribute("path"), request.getRequestURI());

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
                Exceptions.ProcessInternalError(response, exception);
            }
        } else {
            try {
                response.sendError(404);
            } catch (IOException exception) {
                Exceptions.ProcessInternalError(response, exception);
            }
        }
    }
}
