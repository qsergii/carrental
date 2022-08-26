package com.epam.carrental.dao.mysql;

import com.epam.carrental.AppSettings;
import com.epam.carrental.dao.*;
import com.epam.carrental.entity.Car;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

import javax.sql.DataSource;

public class MysqlDAOFactory extends DAOFactory {

    private UserDao userDao;
    private OrderDao orderDao;
    private CarDao carDao;
    private CarBrandDao carBrandDao;

    @Override
    public synchronized UserDao getUserDAO() {
        if(userDao == null) {
            userDao = new MysqlUserDAO();
        }
        return userDao;
    }

    @Override
    public synchronized OrderDao getOrderDAO() {
        if(orderDao == null){
            orderDao = new MysqlOrderDAO();
        }
        return orderDao;
    }

    @Override
    public synchronized CarDao getCarDAO() {
        if(carDao == null){
            carDao = new MysqlCarDAO();
        }
        return carDao;
    }

    @Override
    public synchronized CarBrandDao getCarBrandDAO() {
        if(carBrandDao == null){
            carBrandDao = new MysqlCarBrandDAO();
        }
        return carBrandDao;
    }
}
