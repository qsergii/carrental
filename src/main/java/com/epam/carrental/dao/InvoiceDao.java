package com.epam.carrental.dao;

import com.epam.carrental.dao.entity.Invoice;
import com.epam.carrental.dao.entity.User;

import java.util.List;

public abstract class InvoiceDao {
    public abstract void insert (Invoice invoice);
    public abstract void update (Invoice invoice);
    public abstract List<Invoice> getAll ();
    public abstract Invoice getById (int id);
    public abstract Invoice getByIdAndUser (int id, User user);
}
