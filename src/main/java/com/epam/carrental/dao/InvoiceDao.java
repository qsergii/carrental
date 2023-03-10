package com.epam.carrental.dao;

import com.epam.carrental.dao.entity.Invoice;
import com.epam.carrental.dao.entity.Order;
import com.epam.carrental.dao.entity.User;

import java.sql.Connection;
import java.util.List;

/**
 * Abstract class to invoice method
 */
public abstract class InvoiceDao {
    public abstract void insert(Invoice invoice, Connection connection);

    public abstract void update(Invoice invoice);

    public abstract List<Invoice> getAll();

    public abstract Invoice getById(int id);

    public abstract Invoice getByIdAndUser(int id, User user);

    public abstract List<Invoice> getByUser(User user);

    public abstract List<Invoice> getByOrder(Order order) throws DBException;
}
