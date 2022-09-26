package com.epam.carrental.controllers.user;

import com.epam.carrental.Logging;
import com.epam.carrental.controllers.Controller;
import com.epam.carrental.dao.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserController implements Controller {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            printUser(request, response);
        }catch (IOException e){
            Logging.makeDescription(e);
            try{
                response.sendError(500);
            }catch (Exception e2){
                Logging.makeDescription(e2);
            }
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    public void printUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getAttribute("authUser");
        if(user == null){
            response.sendRedirect("login");
            return;
        }
        request.setAttribute("user", user);
        request.getRequestDispatcher("/WEB-INF/user/user.jsp").forward(request, response);
    }
}
