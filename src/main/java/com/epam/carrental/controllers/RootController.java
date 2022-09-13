package com.epam.carrental.controllers;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class RootController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try{
            handleGetRequest(request, response);
        }catch (IOException e){
            e.printStackTrace(); //TODO
        }
    }

    private void handleGetRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getServletPath().equals("/WEB-INF/builder.jsp")){
            return; // request finished
        }
        if (request.getServletPath().equals("/index.html")) {
            response.sendRedirect("home");
        }
    }
}
