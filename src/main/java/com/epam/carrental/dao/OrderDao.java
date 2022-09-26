package com.epam.carrental.dao;

import com.epam.carrental.dao.entity.Order;
import com.epam.carrental.dao.entity.User;

import java.sql.Connection;
import java.util.List;

public abstract class OrderDao {
    public abstract List<Order> getAll();
    public abstract Order getById(int id);

    public abstract boolean insert(Order order, Connection connection);
    public abstract boolean update(Order order);

    public abstract List<Order> getByUser(User user);

    public abstract float getAmountSummaryByUser(User user) throws DBException;
}
