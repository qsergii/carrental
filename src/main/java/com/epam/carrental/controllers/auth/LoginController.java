package com.epam.carrental.controllers.auth;

import com.epam.carrental.Captcha;
import com.epam.carrental.LanguageBundle;
import com.epam.carrental.controllers.Controller;
import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.dao.entity.Car;
import com.epam.carrental.dao.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class LoginController implements Controller {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/auth/login.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String captcha = request.getParameter("captcha");
        if (!Captcha.isCorrect(request, captcha)) {
            response.setCharacterEncoding("UTF-8");
            response.sendRedirect("login?message=" + encodeValue(LanguageBundle.getString("auth.captcha_incorrect")));
            return;
        }

        String login = Optional.ofNullable(request.getParameter("login")).orElse("");
        if (login.isEmpty()) {
            response.sendRedirect("login?message=" + encodeValue(LanguageBundle.getString("auth.login_empty")));
            return;
        }
        String password = Optional.ofNullable(request.getParameter("password")).orElse("");
        if (password.isEmpty()) {
            response.sendRedirect("login?message=" + encodeValue(LanguageBundle.getString("auth.password_empty")));
            return;
        }

        User user = new User();
        user.setLogin(login);
        user.setPasswordAndSecure(password);
        user = DAOFactory.getInstance().getUserDAO().validate(user);

        if (user == null) {
            response.sendRedirect("login?message=" + encodeValue(LanguageBundle.getString("auth.login_password_incorrect")));
            return;
        }
        request.getSession().setAttribute("userId", user.getId());

        afterLoginRedirect(request, response);
    }

    public static void afterLoginRedirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Car car = (Car) request.getSession().getAttribute("car");
        if (car != null) {
            response.sendRedirect("orders?car_id=" + car.getId());
        } else {
            response.sendRedirect("home");
        }
    }

    private String encodeValue(String value) throws UnsupportedEncodingException {
        return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
    }
}
