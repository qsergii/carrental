package com.epam.carrental.filters;

import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommonFilter extends HttpFilter {
    private String encoding;

    @Override
    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(request.getCharacterEncoding() == null){
            request.setCharacterEncoding(encoding);
        }
        identificateUser(request, response);
        chain.doFilter(request, response);
    }

    private void identificateUser(ServletRequest request, ServletResponse response){

        User user = null;

        HttpSession session = ((HttpServletRequest)request).getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if(userId != null){
            user = DAOFactory.getInstance().getUserDAO().getUserById(userId);
        }

        request.setAttribute("user", user);
    }
}
