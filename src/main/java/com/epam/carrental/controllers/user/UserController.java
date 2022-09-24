package com.epam.carrental.controllers.user;

import com.epam.carrental.Logging;
import com.epam.carrental.dao.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class UserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            printUser(request, response);
        }catch (Exception e){
            Logging.makeDescription(e);
            try{
                response.sendError(500);
            }catch (Exception e2){
                Logging.makeDescription(e2);
            }
        }
    }

    private void printUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getAttribute("user");
        if(user == null){
            response.sendRedirect("login");
            return;
        }
        request.setAttribute("user", user);
        request.getRequestDispatcher("/WEB-INF/user/user.jsp").forward(request, response);
    }
}
