package com.epam.carrental.export;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

class PrinterTest {

    @Test
    void export() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        List list = new ArrayList();

        Printer export = new Printer();
        Assertions.assertThrows(RuntimeException.class, () -> export.export(request, response, list));
    }
}
