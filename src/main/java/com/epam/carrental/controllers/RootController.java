package com.epam.carrental.controllers;

import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.entity.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/", "/main"})
public class RootController extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.isNew()){
            request.setAttribute("authNeeded", true);
        }
        Integer userId = (Integer) session.getAttribute("userId");
        User user = null;
        if(userId == null){
            // non authenticated
        }else{
            user = DAOFactory.getInstance().getUserDAO().getUserById(userId);
        }
        request.setAttribute("user", user);

        ResourceBundle bundle = ResourceBundle.getBundle("resources");
        ResourceBundle bundleUa = ResourceBundle.getBundle("resources_ua", new Locale("uk", "UA"));
        request.setAttribute("auth", bundle.getString("auth"));
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }
}
