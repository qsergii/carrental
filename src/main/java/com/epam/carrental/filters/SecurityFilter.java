package com.epam.carrental.filters;

import com.epam.carrental.entity.Role;
import com.epam.carrental.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/admin", "/admin/*"})
public class SecurityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // TODO temporary
//        User user = (User)((HttpServletRequest)request).getAttribute("user");
//        if(user == null || user.getRole() != Role.ADMIN){
//            ((HttpServletResponse)response).sendError(403);
//            return;
//        }
        chain.doFilter(request, response);
    }
}
