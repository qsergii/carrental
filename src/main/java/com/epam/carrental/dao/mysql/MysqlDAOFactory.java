package com.epam.carrental.dao.mysql;

import com.epam.carrental.dao.*;

public class MysqlDAOFactory extends DAOFactory {

    private UserDao userDao;
    private OrderDao orderDao;
    private CarDao carDao;
    private BrandDao brandDao;

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
    public synchronized BrandDao getBrandDAO() {
        if(brandDao == null){
            brandDao = new MysqlBrandDAO();
        }
        return brandDao;
    }
}
