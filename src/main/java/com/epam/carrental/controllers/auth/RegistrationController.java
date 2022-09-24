package com.epam.carrental.controllers.auth;

import com.epam.carrental.Exceptions;
import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.dao.entity.Role;
import com.epam.carrental.dao.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private final Logger log = LogManager.getLogger(getClass());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/auth/signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try{
            handlePost(request, response);
        }catch (IOException exception){
            Exceptions.ProcessInternalError(response, exception);
        }
    }

    private void handlePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = Optional.ofNullable(request.getParameter("login")).orElse("");
        if(login.isEmpty()){
            // TODO show error login empty
            response.sendRedirect("registration");
            return;
        }
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String password = Optional.ofNullable(request.getParameter("password")).orElse("");
        if(password.isEmpty()){
            // TODO show error password empty
            response.sendRedirect("registration?message=Password empty");
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
        user.setPhone(phone);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPasswordAndSecure(password);
        user.setRole(Role.CLIENT);
        user.setBlocked(false);

        if(!DAOFactory.getInstance().getUserDAO().insert(user)){
            // TODO show message login/password incorrect
            response.sendRedirect("registration");
        }else {
            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getId());
            response.sendRedirect("home");
        }
    }
}
