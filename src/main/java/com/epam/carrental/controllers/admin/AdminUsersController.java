package com.epam.carrental.controllers.admin;

import com.epam.carrental.Logging;
import com.epam.carrental.controllers.Controller;
import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.dao.DBException;
import com.epam.carrental.dao.entity.Role;
import com.epam.carrental.dao.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AdminUsersController implements Controller {
    private final Logger log = LogManager.getLogger(this.getClass());

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
        String paramId = request.getParameter("id");
        if (paramId != null && !paramId.isEmpty()) {
            printUser(paramId, request, response);
        } else {
            printUsers(request, response);
        }
    }

    private void printUsers(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("page", "users");
            request.setAttribute("users", DAOFactory.getInstance().getUserDAO().getAll());
            request.getRequestDispatcher("/WEB-INF/admin/users.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
            log.error(Logging.makeDescription(e));
        }
    }

    private void printUser(String paramId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
        User user;
        int id = Integer.parseInt(paramId);
        float amountSummary = 0;
        if (id > 0) {
            user = DAOFactory.getInstance().getUserDAO().getUserById(id);
            amountSummary = DAOFactory.getInstance().getOrderDAO().getAmountSummaryByUser(user);
        } else {
            user = new User();
        }
        request.setAttribute("user", user);
        request.setAttribute("amountSummary", amountSummary);
        request.setAttribute("roles", Role.values());
        request.setAttribute("orders", DAOFactory.getInstance().getOrderDAO().getByUser(user));
        request.getRequestDispatcher("/WEB-INF/admin/user.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, DBException {

        // properties
        int id = Integer.valueOf(Optional.ofNullable(request.getParameter("id")).orElse("-1"));
        String login = request.getParameter("login");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        int roleId = Integer.parseInt(request.getParameter("role"));
        Role role = Role.getById(roleId);
        boolean blocked = Optional.ofNullable(request.getParameter("blocked")).orElse("").equals("on") ? true : false;

        User user;
        if (id == 0) {
            user = new User();
        } else {
            user = DAOFactory.getInstance().getUserDAO().getUserById(id);
            if (user == null) {
                response.sendError(400);
                return;
            }
        }
        user.setLogin(login);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        if (!password.isEmpty()) {
            user.setPasswordAndSecure(password);
        }
        user.setRole(role);
        user.setBlocked(blocked);

        if (user.getId() == 0) {
            DAOFactory.getInstance().getUserDAO().insert(user);
        } else {
            DAOFactory.getInstance().getUserDAO().update(user);
        }

        response.sendRedirect("users");
    }

}
