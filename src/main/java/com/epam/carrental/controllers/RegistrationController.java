package com.epam.carrental.controllers;

import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.entity.Role;
import com.epam.carrental.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/registration")
public class RegistrationController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = Optional.ofNullable(request.getParameter("login")).orElse("");
        if(login.isEmpty()){
            // TODO show error login empty
            response.sendRedirect("registration");
            return;
        }
        String password = Optional.ofNullable(request.getParameter("password")).orElse("");
        if(password.isEmpty()){
            // TODO show error password empty
            response.sendRedirect("registration");
            return;
        }
        String password2 = Optional.ofNullable(request.getParameter("password2")).orElse("");
        if(password2.isEmpty()){
            // TODO show error password empty
            response.sendRedirect("registration");
            return;
        }
        if(!password.equals(password2)){
            // TODO show error password empty
            response.sendRedirect("registration");
            return;

        }
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(Role.CLIENT);
        user.setBlocked(false);

        if(!DAOFactory.getInstance().getUserDAO().insert(user)){
            // TODO show message login/password incorrect
            response.sendRedirect("registration");
        }else {
            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getId());
            response.sendRedirect("main");
        }
    }
}
