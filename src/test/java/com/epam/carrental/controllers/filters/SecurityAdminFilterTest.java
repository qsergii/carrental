package com.epam.carrental.controllers.filters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

class SecurityAdminFilterTest {

    @Test
    void doFilter() throws ServletException, IOException {
        ServletRequest request = Mockito.mock(ServletRequest.class);
        ServletResponse response = Mockito.mock(ServletResponse.class);
        FilterChain chain = Mockito.mock(FilterChain.class);

        SecurityAdminFilter filter = new SecurityAdminFilter();
        Assertions.assertThrows(ClassCastException.class, () -> filter.doFilter(request, response, chain));
    }
}
