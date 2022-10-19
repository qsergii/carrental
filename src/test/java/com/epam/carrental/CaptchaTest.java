package com.epam.carrental;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

class CaptchaTest {

    @Test
    void doGet() throws IOException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Captcha().doGet(request, response));
    }

    @Test
    void isCorrect() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Assertions.assertThrows(NullPointerException.class, () -> new Captcha().isCorrect(request, "123"));
    }
}
