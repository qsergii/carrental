package com.epam.carrental.controllers.filters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

class UserOrderFilterTest {

    @Test
    void doFilter() {
        ServletRequest request = mock(ServletRequest.class,
                withSettings().extraInterfaces(HttpServletRequest.class));
        ServletResponse response = mock(ServletResponse.class,
                withSettings().extraInterfaces(HttpServletResponse.class));
        FilterChain chain = mock(FilterChain.class);
        when(request.getParameter("id"))
                .thenReturn("1");

        UserOrderFilter filter = new UserOrderFilter();
        Assertions.assertThrows(NullPointerException.class, () -> filter.doFilter(request, response, chain));
    }
}
