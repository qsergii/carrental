package com.epam.carrental.export;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface Exporter {
    void export(HttpServletRequest request, HttpServletResponse response, List invoices);
}
