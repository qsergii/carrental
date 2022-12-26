package com.epam.carrental.dao;

import com.epam.carrental.Logging;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class store connection link to database
 */
public class Database {
    private static final Logger log = LogManager.getLogger(Database.class);

    public static DataSource dataSource;

    static {
        synchronized (Database.class) {
            try {
                Context initContext = new InitialContext();
                Context envContext = (Context) initContext.lookup("java:/comp/env");
                dataSource = (DataSource) envContext.lookup("jdbc/mysql.carrental");
            } catch (NamingException e) {
                log.error(Logging.makeDescription(e));
            }
        }
    }

    public static Connection getConnection() throws DBException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            log.error("Can't get connection from datasourse, " + Logging.makeDescription(e));
            throw new DBException("Can't connect to database", e);
        }
    }

}
