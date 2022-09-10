package com.epam.carrental.dao;

import com.epam.carrental.entity.Order;

public abstract class OrderDao {
    public abstract Order getById(int id);
    public abstract boolean insert(Order order);
}
