package com.epam.carrental;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    private final Logger log = LogManager.getLogger(getClass());
    @Override
    public void contextInitialized(ServletContextEvent event) {

        log.info("servlet initialize");

        ServletContext context = event.getServletContext();
        String path = context.getContextPath();
        context.setAttribute("path", path);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        log.info("servlet destroy");
    }

}
