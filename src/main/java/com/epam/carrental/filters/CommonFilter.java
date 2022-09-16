package com.epam.carrental.filters;

import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class CommonFilter extends HttpFilter {
    private String encoding;

    @Override
    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        setEncoding(request);
        authorizeUser(request, response);
        chain.doFilter(request, response);
    }

    private void setEncoding(ServletRequest request) throws UnsupportedEncodingException {
        if(request.getCharacterEncoding() == null){
            request.setCharacterEncoding(encoding);
        }
    }

    private void authorizeUser(ServletRequest request, ServletResponse response){

        User user = null;

        HttpSession session = ((HttpServletRequest)request).getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if(userId != null){
            user = DAOFactory.getInstance().getUserDAO().getUserById(userId);
        }

        request.setAttribute("user", user);
    }

    @Override
    public void destroy() {
        // not needed
    }
}
