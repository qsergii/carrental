package com.epam.carrental.dao;

import com.epam.carrental.AppSettings;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Database {

    public static DataSource dataSource;// = getPooledConnectionDataSource();

    static {
        synchronized (Database.class) {
            try {
                Context initContext = new InitialContext();
                Context envContext = (Context) initContext.lookup("java:/comp/env");
                dataSource = (DataSource) envContext.lookup("jdbc/carrental");
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
    }

    public static DataSource getPooledConnectionDataSource() {
        MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();

        String URL = AppSettings.PROPERTIES.getProperty("dataSourceURL");
        String USER = AppSettings.PROPERTIES.getProperty("dataSourceUser");
        String PASSWORD = AppSettings.PROPERTIES.getProperty("dataSourcePassword");

        ds.setURL(URL);
        ds.setUser(USER);
        ds.setPassword(PASSWORD);
        return ds;
    }
}
