package com.epam.carrental.dao.mysql;

import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.dao.OrderDao;
import com.epam.carrental.dao.UserDao;

public class MysqlDAOFactory extends DAOFactory {

    private UserDao userDao;
    private OrderDao orderDao;

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
}
