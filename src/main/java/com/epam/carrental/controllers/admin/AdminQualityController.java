package com.epam.carrental.controllers.admin;

import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.dao.entity.Quality;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/admin/qualities")
public class AdminQualityController extends HttpServlet {
    private final Logger log = LogManager.getLogger(getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            handleGet(request, response);
        }catch (IOException e){
            log.error(e.getMessage());
            try {
                response.sendError(500);
            }catch (IOException e2){
                log.error(e2.getMessage());
            }
        }
    }

    private void handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = Optional.ofNullable(request.getParameter("action")).orElse("");
        if (action.equals("add")) {
                request.setAttribute("quality", new Quality());
                request.getRequestDispatcher("/WEB-INF/admin/quality.jsp").forward(request, response);
        } else if (action.equals("edit")) {
                int id = Integer.parseInt(Optional.ofNullable(request.getParameter("id")).orElse(""));
                Quality quality = DAOFactory.getInstance().getQualityDAO().getById(id);
                request.setAttribute("quality", quality);
                request.getRequestDispatcher("/WEB-INF/admin/quality.jsp").forward(request, response);
        } else {
                request.setAttribute("page", "qualities");
                request.setAttribute("brands", DAOFactory.getInstance().getQualityDAO().getAll());
                request.getRequestDispatcher("/WEB-INF/admin/qualities.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            handlePost(request, response);
        } catch (IOException e) {
            log.error(e.getMessage());
            try {
                response.sendError(500);
            }catch (Exception e2){
                log.error(e2.getMessage());
            }
        }
    }

    private void handlePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Quality quality;

        int id = Integer.parseInt(Optional.ofNullable(request.getParameter("id")).orElse("0"));
        if (id > 0) {
            quality = DAOFactory.getInstance().getQualityDAO().getById(id);
            if (quality == null) {
                log.error("can't find quality by id " + id);
                response.sendError(500);
                return;
            }
        } else {
            quality = new Quality();
        }

        String action = Optional.ofNullable(request.getParameter("action")).orElse("");
        if (action.equals("delete")) {
            deleteQuality(request, response, quality);
            return;
        }

        String name = request.getParameter("name");
        if (name == null || name.isEmpty()) {
            response.sendRedirect("quality?message=name can't be empty");
            return;
        }
        quality.setName(name);
        if (quality.getId() == 0) {
            DAOFactory.getInstance().getQualityDAO().create(quality);
        } else {
            DAOFactory.getInstance().getQualityDAO().update(quality);
        }
        response.sendRedirect("qualities");
    }

    private void deleteQuality(HttpServletRequest request, HttpServletResponse response, Quality quality) throws IOException {
        DAOFactory.getInstance().getQualityDAO().delete(quality);
        response.sendRedirect("qualities");
    }

}
