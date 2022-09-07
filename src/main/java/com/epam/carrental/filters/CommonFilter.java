package com.epam.carrental.filters;

import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.entity.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class CommonFilter extends HttpFilter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
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
