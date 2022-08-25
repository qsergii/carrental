package com.epam.carrental.dao.mysql;

import com.epam.carrental.AppSettings;
import com.epam.carrental.dao.CarDao;
import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.dao.OrderDao;
import com.epam.carrental.dao.UserDao;
import com.epam.carrental.entity.Car;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

import javax.sql.DataSource;

public class MysqlDAOFactory extends DAOFactory {

    private UserDao userDao;
    private OrderDao orderDao;
    private CarDao carDao;

    @Override
    public synchronized UserDao getUserDAO() {
        if(userDao == null) {
            userDao = new MysqlUserDAO();
        }
        return userDao;
    }

    @Override
    public OrderDao getOrderDAO() {
        if(orderDao == null){
            orderDao = new MysqlOrderDAO();
        }
        return orderDao;
    }

    @Override
    public CarDao getCarDAO() {
        if(carDao == null){
            carDao = new MysqlCarDAO();
        }
        return carDao;
    }
}
