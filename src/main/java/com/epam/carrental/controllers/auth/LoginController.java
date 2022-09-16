package com.epam.carrental.controllers.auth;

import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = Optional.ofNullable(request.getParameter("login")).orElse("");
        if(login.isEmpty()){
            // TODO show error login empty
            response.sendRedirect("login");
            return;
        }
        String password = Optional.ofNullable(request.getParameter("password")).orElse("");
        if(password.isEmpty()){
            // TODO show error password empty
            response.sendRedirect("login");
            return;
        }
        User user = new User();
        user.setLogin(login);
        user.setPasswordAndSecure(password);
        user = DAOFactory.getInstance().getUserDAO().validate(user);
        if(user == null){
            response.sendRedirect("login?message=Login or password is incorrect");
        }else {
            // success
            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getId());
            if(session.getAttribute("car") != null){
                response.sendRedirect("order");
            }else {
                response.sendRedirect("home");
            }
        }
    }
}
