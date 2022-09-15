package com.epam.carrental.filters;

import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.entity.Order;
import com.epam.carrental.entity.Role;
import com.epam.carrental.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/order")
public class OrderFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String orderIdString = request.getParameter("id");
        if(orderIdString != null) {

            HttpServletResponse httpServletResponse = (HttpServletResponse) response;

            int orderId = Integer.parseInt(orderIdString);
            Order order = DAOFactory.getInstance().getOrderDAO().getById(orderId);
            if(order == null){
                httpServletResponse.sendError(400);
                return;
            }

            User user = (User)((HttpServletRequest)request).getAttribute("user");
            if(user == null){
                httpServletResponse.sendError(403);
                return;
            }

            Role role = user.getRole();
            if(role == Role.ADMIN || role == Role.MANAGER){
                chain.doFilter(request, response);
                return;
            }

            if(!order.getUser().equals(user)){
                httpServletResponse.sendError(403);
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
