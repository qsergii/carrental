package com.epam.carrental.dao;

import com.epam.carrental.AppSettings;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

import javax.sql.DataSource;

public abstract class DAOFactory {

    private static DAOFactory instance;
    private static String daoFactoryFCN = AppSettings.PROPERTIES.getProperty("daoFactoryFCN");
    public static DataSource dataSource = getPooledConnectionDataSource();;

    public static synchronized DAOFactory getInstance() throws Exception{ // TODO Exception
        if(instance == null){
            Class<?> classExmp = Class.forName((DAOFactory.daoFactoryFCN));
            instance = (DAOFactory) classExmp.getDeclaredConstructor().newInstance();
        }
        return instance;
    }

    protected DAOFactory(){}

    String daoFactoryFCN(){
        return null; // TODO
    }

    void setDaoFactoryFCN(String daoFactoryFCN){
        instance = null;
        DAOFactory.daoFactoryFCN = daoFactoryFCN;
    }
    public abstract UserDao getUserDAO();
    public abstract OrderDao getOrderDAO();
    public abstract CarDao getCarDAO();

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
