package com.epam.carrental.controllers.filters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import static org.junit.jupiter.api.Assertions.*;

class SecurityManagerFilterTest {

    @Test
    void doFilter() {
        ServletRequest request = Mockito.mock(ServletRequest.class);
        ServletResponse response = Mockito.mock(ServletResponse.class);
        FilterChain chain = Mockito.mock(FilterChain.class);

        SecurityManagerFilter filter = new SecurityManagerFilter();
        Assertions.assertThrows(ClassCastException.class, () -> filter.doFilter(request, response, chain));
    }
}
