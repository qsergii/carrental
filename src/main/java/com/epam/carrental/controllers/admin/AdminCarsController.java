package com.epam.carrental.controllers.admin;

import com.epam.carrental.Logging;
import com.epam.carrental.controllers.Controller;
import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.dao.DBException;
import com.epam.carrental.dao.entity.Car;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AdminCarsController implements Controller {

    private final Logger log = LogManager.getLogger(this.getClass());

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, DBException {
        log.trace("get");
        if (request.getParameter("id") != null) {
            printCar(request, response);
        } else {
            printCars(request, response);
        }
    }

    private void printCar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, DBException {
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

        request.setAttribute("orders", DAOFactory.getInstance().getOrderDAO().getByCar(car));

        request.getRequestDispatcher("/WEB-INF/admin/car.jsp").forward(request, response);
    }

    private void printCars(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("page", "cars");
            request.setAttribute("cars", DAOFactory.getInstance().getCarDAO().getAll());
            request.getRequestDispatcher("/WEB-INF/admin/cars.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
            log.error(Logging.makeDescription(e));
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getContentType().contains("multipart/form-data")) {
            create(request, response);
            return;
        }
        String action = request.getParameter("action");
        if (action == null) {
            response.sendError(400);
            return;
        }
        if(action.equals("delete")) {
            delete(request, response);
        };
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int maxMemSize = 5_000_000;
        int maxFileSize = 5_000_000;

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(maxMemSize);
        factory.setRepository(new File(request.getRealPath("/WEB-INF/uploads")));

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(maxFileSize);

        List<FileItem> requestItems = upload.parseRequest(request);
        Car car = new Car();
        for (FileItem element : requestItems) {
            String fieldName = element.getFieldName();
            if (element.isFormField()) {
                switch (fieldName) {
                    case "id":
                        car.setId(Integer.parseInt(element.getString()));
                        break;
                    case "name":
                        car.setName(element.getString());
                        break;
                    case "description":
                        car.setDescription(element.getString());
                        break;
                    case "blocked":
                        car.setBlocked(Optional.ofNullable(element.getString()).orElse("").equals("on"));
                        break;
                    case "price":
                        car.setPrice(Float.parseFloat(element.getString()));
                        break;
                    case "quality":
                        car.setQuality(DAOFactory.getInstance().getQualityDAO().getById(Integer.parseInt(element.getString())));
                        break;
                    case "brand":
                        car.setBrand(DAOFactory.getInstance().getBrandDAO().getById(Integer.parseInt(element.getString())));
                }
            } else {
                ServletContext context = request.getServletContext();
                String filePath = context.getInitParameter("file-upload");

                File file;

                String fieldNameIn = element.getFieldName();
                String fileName = element.getName();
                boolean isInMemory = element.isInMemory();
                long sizeInBytes = element.getSize();
                if(sizeInBytes == 0){
                    continue;
                }

                // Write the file
                int index;
                if (fileName.lastIndexOf("\\") >= 0) {
                    index = fileName.lastIndexOf("\\");
                } else {
                    index = fileName.lastIndexOf("\\") + 1;
                }
                file = new File(filePath + fileName.substring(index));
                String extension = FilenameUtils.getExtension(file.getAbsolutePath());
                fileName = UUID.randomUUID().toString() + "." + extension;
                file = new File(context.getRealPath("/") + "/assets/cars/" + fileName);
                element.write(file);
                car.setImageFileName(fileName);
            }
        }
        write(car);
        response.sendRedirect("cars");
    }

    private void write(Car car) {
        if (car.getId() == 0) {
            DAOFactory.getInstance().getCarDAO().insert(car);
        } else {
            if(car.getImageFileName() == null) {
                Car carFromDB = DAOFactory.getInstance().getCarDAO().getById(car.getId());
                car.setImageFileName(carFromDB.getImageFileName());
            }
            DAOFactory.getInstance().getCarDAO().update(car);
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, DBException {
        int id = Integer.parseInt(request.getParameter("id"));
        Car car = DAOFactory.getInstance().getCarDAO().getById(id);
        if(car == null){
            response.sendRedirect("cars");
            return;
        }
        try {
            DAOFactory.getInstance().getCarDAO().delete(car);
//            response.sendRedirect("cars");
            response.getWriter().write("ok");
        }catch (DBException e){
            log.error(Logging.makeDescription(e));
            String error = e.getMessage();
//            response.sendRedirect("cars?id=" + id + "&message=" + error);
            response.getWriter().write(error);
        }
    }

}
