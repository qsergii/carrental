package com.epam.carrental.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public interface Controller {
    default void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException, ServletException {}
    default void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException, ServletException{}
}
