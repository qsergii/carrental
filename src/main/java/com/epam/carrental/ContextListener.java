package com.epam.carrental;

import com.epam.carrental.dao.Database;
import org.apache.commons.dbutils.DbUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Log start and finish of application
 * load user instance from DB
 * */
@WebListener
public class ContextListener implements ServletContextListener {

    private final Logger log = LogManager.getLogger(getClass());

    @Override
    public void contextInitialized(ServletContextEvent event) {

        log.info("servlet initialize");

        ServletContext context = event.getServletContext();
        context.setAttribute("path", context.getContextPath());

        checkDbConnection();
    }

    private void checkDbConnection() {
        Connection connection = null;
        try {
            connection = Database.dataSource.getConnection();
            connection.isValid(1);
        } catch (SQLException e) {
            log.error("Can't connect to database, check settings and reload application");
            throw new RuntimeException("Can't connect to database, check settings and reload application", e);
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        log.info("servlet destroy");
    }

}
