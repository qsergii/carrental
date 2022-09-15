package com.epam.carrental;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        String baseDir = context.getRealPath("/");
        context.setAttribute("baseDir", baseDir);


//        String path = context.getContextPath();
//        context.setAttribute("app", path); // set for whole servlet
//        System.out.println(path);

        // initialize log4j
//        ServletContext context = event.getServletContext();
//        String log4jConfigFile = context.getInitParameter("log4j-config-location");
//        String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;

//        Properties props = new Properties();
//        props.load(new FileInputStream("-log4j.properties"));
//        PropertyConfigurator.configure("-log4j.properties");
//        ClassLoader loader = Thread.currentThread().getContextClassLoader();
//        URL url = loader.getResource("-log4j.properties");
//        PropertyConfigurator.configure(url);
//        Logger.getLogger(this.getClass()).error("TEST");
//        PropertyConfigurator.configure(getClass().getResource("-log4j.properties"));
//        PropertyConfigurator.configure("-log4j.properties");

    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // do nothing
    }

}
