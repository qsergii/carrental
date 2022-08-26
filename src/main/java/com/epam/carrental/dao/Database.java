package com.epam.carrental.dao;

import com.epam.carrental.AppSettings;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

import javax.sql.DataSource;
import java.sql.PreparedStatement;

public class Database {

    public static DataSource dataSource = getPooledConnectionDataSource();

//    private int query(String query, Object[] params) throws SQLException{
//        PreparedStatement ps = connection.prepareStatement(query);
//        if (params != null){
//            int index = 1;
//            for(Object param : params){
//                ps.setObject(index, param);
//                index++;
//            }
//        }
//        return ps.executeUpdate();
//    }

    public static DataSource getPooledConnectionDataSource(){
        MysqlConnectionPoolDataSource dataSource1 = new MysqlConnectionPoolDataSource();

        String URL = AppSettings.PROPERTIES.getProperty("dataSourceURL");
        String USER = AppSettings.PROPERTIES.getProperty("dataSourceUser");
        String PASSWORD = AppSettings.PROPERTIES.getProperty("dataSourcePassword");

//        String FULL_URL = URL + "?" + "user=" + USER + "&password=" + PASSWORD;
//        dataSource1.setURL(FULL_URL);

        dataSource1.setURL(URL);
        dataSource1.setUser(USER);
        dataSource1.setPassword(PASSWORD);
        return dataSource1;
    }
}
