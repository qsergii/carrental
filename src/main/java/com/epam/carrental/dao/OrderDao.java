package com.epam.carrental.dao;

import com.epam.carrental.dao.entity.Car;
import com.epam.carrental.dao.entity.Order;
import com.epam.carrental.dao.entity.User;

import java.sql.Connection;
import java.util.List;

/**
 * Abstract class to order methods
 */
public abstract class OrderDao {
    public abstract List<Order> getAll();

    public abstract Order getById(int id);

    public abstract List<Order> getByCar(Car car) throws DBException;

    public abstract boolean insert(Order order, Connection connection);

    public abstract boolean update(Order order);

    public abstract List<Order> getByUser(User user);

    public abstract float getAmountSummaryByUser(User user) throws DBException;
}
