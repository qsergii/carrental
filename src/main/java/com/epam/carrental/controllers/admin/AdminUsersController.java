package com.epam.carrental.controllers.admin;

import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.entity.*;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/admin/users")
public class AdminUsersController extends HttpServlet {
    private final Logger log = Logger.getLogger(this.getClass());

    /* EXTERNAL */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String paramId = request.getParameter("id");
            if (paramId == null || paramId.isEmpty()) {
                printUsers(request, response);
            } else {
                printUser(paramId, request, response);
            }
        } catch (Exception e) {
            log.error("doGet: " + e.getMessage());
            response.sendError(500);
        }

        request.getRequestDispatcher("/WEB-INF/admin/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // properties
        int id = Integer.valueOf(Optional.ofNullable(request.getParameter("id")).orElse("-1"));
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        int roleId = Integer.parseInt(request.getParameter("role"));
        Role role = Role.getById(roleId);
        boolean blocked = Optional.ofNullable(request.getParameter("blocked")).orElse("").equals("on") ? true : false;

        User user;
        if (id == 0 ) {
            user = new User();
        } else {
            user = DAOFactory.getInstance().getUserDAO().getUserById(id);
            if(user == null){
                response.sendError(400);
                return;
            }
        }
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        user.setBlocked(blocked);

        if(user.getId() == 0){
            DAOFactory.getInstance().getUserDAO().insert(user);
        }else{
            DAOFactory.getInstance().getUserDAO().update(user);
        }

        response.sendRedirect("users");
    }

    /* PRIVATE */

    private void printUsers(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("page", "users");
            request.setAttribute("users", DAOFactory.getInstance().getUserDAO().getAll());
            request.getRequestDispatcher("/WEB-INF/admin/users.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printUser(String paramId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user;
        int id = Integer.parseInt(paramId);
        if (id > 0) {
            user = DAOFactory.getInstance().getUserDAO().getUserById(id);
        } else {
            user = new User();
        }
        request.setAttribute("user", new User());
        request.getRequestDispatcher("/WEB-INF/admin/user.jsp").forward(request, response);

    }

}
