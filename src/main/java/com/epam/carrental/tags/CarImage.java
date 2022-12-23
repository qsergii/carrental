package com.epam.carrental.tags;

import com.epam.carrental.dao.entity.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.File;
import java.io.IOException;

/**
 * Class for tag to generate car image
 * depends on car image exist
 */
public class CarImage extends TagSupport {
    private final Logger log = LogManager.getLogger(getClass());
    private Car car;
    private String classElement = "car";

    public void setCar(Car car) {
        this.car = car;
    }

    public void setClassElement(String classElement) {
        this.classElement = classElement;
    }

    @Override
    public int doStartTag() {
        if (car == null) {
            log.error("car equal null");
            return SKIP_BODY;
        }
        ServletContext context = pageContext.getServletContext();
        String subFolder = "/assets/cars";

        String fileName = car.getImageFileName();
        if (fileName == null || !new File(context.getRealPath("/") + subFolder + "/" + fileName).exists()) {
            fileName = "car_default.png";
        }
        JspWriter out = pageContext.getOut();
        try {
            out.print("<picture class=\"d-flex justify-content-center\"><img class=\"" + classElement + "\" src=\""
                    + context.getAttribute("path") + subFolder + "/" + fileName + "\"></picture>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return SKIP_BODY;
    }
}
