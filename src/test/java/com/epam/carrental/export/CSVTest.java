package com.epam.carrental.export;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class CSVTest {

    @Test
    void export() throws IOException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        ServletOutputStream outputStream = Mockito.mock(ServletOutputStream.class);
        Mockito.when(response.getOutputStream())
                .thenReturn(outputStream);
        List list = new ArrayList();

        Assertions.assertThrows(
                NullPointerException.class,
                () -> new CSV().export(request, response, list)
        );
    }

}
