package com.epam.carrental.controllers.filters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.servlet.*;
import java.io.IOException;
import java.lang.reflect.Field;

import static org.mockito.Mockito.mock;

class CommonFilterTest {

    @Test
    void init() throws ServletException, NoSuchFieldException, IllegalAccessException {
        Field field = CommonFilter.class.getDeclaredField("encoding");
        field.setAccessible(true);

        FilterConfig config = mock(FilterConfig.class);

        CommonFilter filter = new CommonFilter();
        filter.init(config);
        String value = (String) field.get(filter);
        Assertions.assertEquals("UTF-8", value);
    }

    @Test
    void doFilter() throws ServletException, IOException {
        ServletRequest request = mock(ServletRequest.class);
        ServletResponse response = mock(ServletResponse.class);
        FilterChain chain = mock(FilterChain.class);
        CommonFilter filter = new CommonFilter();
        Assertions.assertThrows(ClassCastException.class, () -> {
            filter.doFilter(request, response, chain);
        });
    }


}
