package com.epam.carrental.controllers.filters;

import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.dao.entity.Order;
import com.epam.carrental.dao.entity.Role;
import com.epam.carrental.dao.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/orders")
public class UserOrderFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String orderIdString = request.getParameter("id");
        if (orderIdString != null) {

            HttpServletResponse httpServletResponse = (HttpServletResponse) response;

            int orderId = Integer.parseInt(orderIdString);
            Order order = null;
            order = DAOFactory.getInstance().getOrderDAO().getById(orderId);
            if (order == null) {
                httpServletResponse.sendError(400);
                return;
            }

            User user = (User) ((HttpServletRequest) request).getAttribute("authUser");
            if (user == null) {
                httpServletResponse.sendError(403);
                return;
            }

            Role role = user.getRole();
            if (role == Role.ADMIN || role == Role.MANAGER) {
                chain.doFilter(request, response);
                return;
            }

            if (!order.getUser().equals(user)) {
                httpServletResponse.sendError(403);
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
