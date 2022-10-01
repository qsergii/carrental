package com.epam.carrental.controllers.admin;

import com.epam.carrental.Logging;
import com.epam.carrental.controllers.Controller;
import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.dao.DBException;
import com.epam.carrental.dao.entity.Car;
import com.epam.carrental.dao.entity.Quality;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class AdminQualityController implements Controller {
    private final Logger log = LogManager.getLogger(getClass());

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id") != null) {
            printCard(request, response);
        } else {
            printList(request, response);
        }
    }

    private static void printCard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Quality quality;
        List<Car> cars;

        if (id > 0) {
            quality = DAOFactory.getInstance().getQualityDAO().getById(id);
            cars = DAOFactory.getInstance().getCarDAO().getByQuality(quality);
        } else {
            quality = new Quality();
            cars = null;
        }
        request.setAttribute("quality", quality);
        request.setAttribute("cars", cars);
        request.getRequestDispatcher("/WEB-INF/admin/quality.jsp").forward(request, response);
    }

    private static void printList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("qualities", DAOFactory.getInstance().getQualityDAO().getAll());
        request.getRequestDispatcher("/WEB-INF/admin/qualities.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, DBException {
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
        } else {
            insertUpdate(request, response, quality);
        }
    }

    private static void insertUpdate(HttpServletRequest request, HttpServletResponse response, Quality quality) throws IOException {
        String name = request.getParameter("name");
        if (name == null || name.isEmpty()) {
            response.sendRedirect("quality?message=name can't be empty");
            return;
        }
        quality.setName(name);
        try {
            if (quality.getId() == 0) {
                DAOFactory.getInstance().getQualityDAO().insert(quality);
            } else {
                DAOFactory.getInstance().getQualityDAO().update(quality);
            }
            response.sendRedirect("qualities");
        } catch (DBException e) {
            response.sendRedirect("qualities?id=" + quality.getId() + "&message=" + e.getMessage());
        }
    }

    private void deleteQuality(HttpServletRequest request, HttpServletResponse response, Quality quality) throws IOException {
        try {
            DAOFactory.getInstance().getQualityDAO().delete(quality);
            response.getWriter().print("ok");
        } catch (DBException e) {
            log.error(Logging.makeDescription(e));
            response.getWriter().print(e.getMessage());
        }
    }

}
