package com.epam.carrental.filters;

import com.epam.carrental.dao.entity.Role;
import com.epam.carrental.dao.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/manager/*")
public class SecurityManagerFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        User user = (User)request.getAttribute("user");
        if(user == null || (user.getRole() != Role.ADMIN && user.getRole() != Role.MANAGER)){
            ((HttpServletResponse)response).sendError(403);
        }else{
            chain.doFilter(request, response);
        }
    }
}
