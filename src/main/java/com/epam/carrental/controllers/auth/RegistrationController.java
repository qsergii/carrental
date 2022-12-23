package com.epam.carrental.controllers.auth;

import com.epam.carrental.Captcha;
import com.epam.carrental.LanguageBundle;
import com.epam.carrental.Mail;
import com.epam.carrental.controllers.Controller;
import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.dao.DBException;
import com.epam.carrental.dao.entity.Role;
import com.epam.carrental.dao.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Registration new user
 */
public class RegistrationController implements Controller {
    private final Logger log = LogManager.getLogger(getClass());

    /**
     * Get registration form
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/auth/signup.jsp").forward(request, response);
    }

    /**
     * Create user
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String captcha = request.getParameter("captcha");
        if (!Captcha.isCorrect(request, captcha)) {
            response.sendRedirect("registration?message=" + LanguageBundle.getStringEncoded("auth.captcha_incorrect"));
            return;
        }

        String login = Optional.ofNullable(request.getParameter("login")).orElse("");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String password = Optional.ofNullable(request.getParameter("password")).orElse("");

        if (login.isEmpty()) {
            response.sendRedirect("registration?message=Login empty. Type login and try again");
            return;
        }
        if (password.isEmpty()) {
            response.sendRedirect("registration?message=" + LanguageBundle.getStringEncoded("auth.password_empty"));
            return;
        }
        String password2 = Optional.ofNullable(request.getParameter("password2")).orElse("");
        if (password2.isEmpty()) {
            response.sendRedirect("registration?message=" + LanguageBundle.getStringEncoded("auth.password2_empty"));
            return;
        }
        if (!password.equals(password2)) {
            response.sendRedirect("registration?message=" + LanguageBundle.getStringEncoded("auth.password_mismatch"));
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

        try {
            DAOFactory.getInstance().getUserDAO().insert(user);
            request.getSession().setAttribute("userId", user.getId());
            Mail.send(
                    email,
                    LanguageBundle.getString("auth.mail.registration.header"),
                    firstName + "!\n" + LanguageBundle.getString("auth.mail.registration.body")
            );
            LoginController.afterLoginRedirect(request, response);
        } catch (DBException e) {
            log.error(e.getMessage());
            response.sendRedirect("registration?message=" + e.getMessage());
        } catch (MessagingException e) {
            log.error(e.getMessage());
            throw new IOException("Can't send email", e);
        }
    }
}
