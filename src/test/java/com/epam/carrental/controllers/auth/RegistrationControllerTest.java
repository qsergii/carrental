package com.epam.carrental.controllers.auth;

import com.epam.carrental.Captcha;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

class RegistrationControllerTest {

    @Test
    void doGet() throws ServletException, IOException {
        RegistrationController controller = new RegistrationController();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        String file = "/WEB-INF/auth/signup.jsp";
        when(request.getRequestDispatcher(file))
                .thenReturn(dispatcher);

        controller.doGet(request, response);
        verify(request, times(1)).getRequestDispatcher(file);
    }

    @Test
    void doPost() throws Exception {
        RegistrationController controller = new RegistrationController();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        String captchaString = "123";
        when(request.getParameter("captcha")).thenReturn(captchaString);
        when(request.getParameter("login")).thenReturn("barak");
        when(request.getParameter("phone")).thenReturn("barak");
        when(request.getParameter("email")).thenReturn("barak");
        when(request.getParameter("first_name")).thenReturn("barak");
        when(request.getParameter("last_name")).thenReturn("barak");
        when(request.getParameter("password")).thenReturn("barak");
        when(request.getParameter("password2")).thenReturn("barak");

        try (MockedStatic<Captcha> captchaStatic = Mockito.mockStatic(Captcha.class)) {
            captchaStatic.when(() -> Captcha.isCorrect(request, captchaString)).thenReturn(true);

            Assertions.assertThrows(NullPointerException.class, () -> controller.doPost(request, response));
        }
    }
}
