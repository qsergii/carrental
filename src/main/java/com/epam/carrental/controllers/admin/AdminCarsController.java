package com.epam.carrental.controllers.admin;

import com.epam.carrental.controllers.Controller;
import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.entity.Brand;
import com.epam.carrental.entity.Car;
import com.epam.carrental.entity.Quality;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class AdminCarsController implements Controller {

    private final Logger log = LogManager.getLogger(this.getClass());

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.trace("get");
        if (request.getParameter("id") != null) {
            printCar(request, response);
        } else {
            printCars(request, response);
        }
    }

    private void printCar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String paramId = request.getParameter("id");
        Car car;
        int id = Integer.parseInt(paramId);
        if (id > 0) {
            car = DAOFactory.getInstance().getCarDAO().getById(id);
        } else {
            car = new Car();
        }
        request.setAttribute("car", car);
        request.setAttribute("brands", DAOFactory.getInstance().getBrandDAO().getAll());
        request.setAttribute("qualities", DAOFactory.getInstance().getQualityDAO().getAll());
        request.getRequestDispatcher("/WEB-INF/admin/car.jsp").forward(request, response);
    }

    private void printCars(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("page", "cars");
            request.setAttribute("cars", DAOFactory.getInstance().getCarDAO().getAll());
            request.getRequestDispatcher("/WEB-INF/admin/cars.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getContentType().contains("multipart/form-data")) {
            doPostFile(request, response);
            return;
        }
        String action = request.getParameter("action");
        if (action == null) {
            log.error("Wrong parameters");
            response.sendError(400);
            return;
        }
        switch (action) {
            case "file":
                doPostFile(request, response);
                break;
            case "order":
                doPostOrder(request, response);
                break;
            default:
                log.error("param action wrong: " + action);
                response.sendError(400);
        }

    }

    private void doPostFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int maxMemSize = 5_000_000;
        int maxFileSize = 5 * 1000 * 1000;
        String file_name = null;
        String file_name2="";
        File file;
        ServletContext context = request.getServletContext();
        String filePath = context.getInitParameter("file-upload");
        response.setContentType("text/html");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(maxMemSize);
        factory.setRepository(new File("c:\\temp"));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(maxFileSize);

        try {
            // Parse the request to get file items.
            List requestItems = upload.parseRequest(request);

            // Process the uploaded file items
            Iterator i = requestItems.iterator();

            PrintWriter out = response.getWriter();

            while (i.hasNext()) {
                FileItem fileItem = (FileItem) i.next();
                if (!fileItem.isFormField()) {
                    // Get the uploaded file parameters
                    String fieldName = fileItem.getFieldName();
                    String fileName = fileItem.getName();
                    boolean isInMemory = fileItem.isInMemory();
                    long sizeInBytes = fileItem.getSize();

                    // Write the file
                    if (fileName.lastIndexOf("\\") >= 0) {
                        file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\")));
                    } else {
                        file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\") + 1));
                    }
                    fileItem.write(file);
                    out.println("Uploaded Filename: " + filePath + fileName + "<br>");
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

//        response.sendRedirect("cars?id=0&img=" + fileId);
    }

    private void doPostOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // properties
        int id = Integer.parseInt(Optional.ofNullable(request.getParameter("id")).orElse("-1"));
        String name = request.getParameter("name");
        String brandId = request.getParameter("brand");
        String qualityId = request.getParameter("quality");

        Brand brand = DAOFactory.getInstance().getBrandDAO().getById(Integer.parseInt(brandId));
        Quality quality = DAOFactory.getInstance().getQualityDAO().getById(Integer.parseInt(qualityId));
        String description = request.getParameter("description");
        boolean blocked = Optional.ofNullable(request.getParameter("blocked")).orElse("").equals("on");
        float price = Float.parseFloat(request.getParameter("price"));

        Car car;
        if (id == 0) {
            car = new Car();
        } else {
            car = DAOFactory.getInstance().getCarDAO().getById(id);
            if (car == null) {
                response.sendError(400);
                return;
            }
        }
        car.setName(name);
        car.setDescription(description);
        car.setBlocked(blocked);
        car.setPrice(price);
        car.setQuality(new Quality());
        car.setBrand(brand);
        car.setQuality(quality);

        if (car.getId() == 0) {
            DAOFactory.getInstance().getCarDAO().insert(car);
        } else {
            DAOFactory.getInstance().getCarDAO().update(car);
        }

        response.sendRedirect("cars");
    }

}
