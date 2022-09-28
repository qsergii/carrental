package com.epam.carrental.controllers.filters;

import com.epam.carrental.Logging;
import com.epam.carrental.dao.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/invoices/*")
public class UserInvoicesFilter implements Filter {

    private final Logger log = LogManager.getLogger(getClass());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {

        try {
            User user = (User)request.getAttribute("authUser");
            if(user == null){
                ((HttpServletResponse)response).sendRedirect("login");
            }else{
                chain.doFilter(request, response);
            }
        } catch (IOException | ServletException e ) {
            log.error(Logging.makeDescription(e));
        }
    }
}
