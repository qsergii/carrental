package com.epam.carrental.controllers.admin;

import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.entity.Brand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/admin/brands")
public class AdminBrandsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = Optional.ofNullable(request.getParameter("action")).orElse("");
        if (action.equals("add")) {
            try {
                request.setAttribute("brand", new Brand());
                request.getRequestDispatcher("/WEB-INF/admin/brands/card.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(action.equals("edit")){
            try {
                int id = Integer.parseInt(Optional.ofNullable(request.getParameter("id")).orElse(""));
                Brand brand = DAOFactory.getInstance().getBrandDAO().getById(id);
                request.setAttribute("brand", brand);
                request.getRequestDispatcher("/WEB-INF/admin/brands/card.jsp").forward(request, response);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        else if(action.equals("delete")){
            try {
                int id = Integer.parseInt(Optional.ofNullable(request.getParameter("id")).orElse(""));
                DAOFactory.getInstance().getBrandDAO().delete(new Brand(id));
                response.sendRedirect("brands");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else {
            try {
                request.setAttribute("brands", DAOFactory.getInstance().getBrandDAO().getAll());
                request.getRequestDispatcher("/WEB-INF/admin/brands/list.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        Brand brand = new Brand(name);
        try {
            DAOFactory.getInstance().getBrandDAO().create(brand);
            response.sendRedirect("brands");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
