package com.epam.carrental.dao;

import com.epam.carrental.dao.entity.Invoice;
import com.epam.carrental.dao.entity.Order;
import com.epam.carrental.dao.entity.User;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceDaoTest {

    @Test
    void classCreate() {
        InvoiceDao invoiceDao = new InvoiceDao() {
            @Override
            public void insert(Invoice invoice, Connection connection) {

            }

            @Override
            public void update(Invoice invoice) {

            }

            @Override
            public List<Invoice> getAll() {
                return null;
            }

            @Override
            public Invoice getById(int id) {
                return null;
            }

            @Override
            public Invoice getByIdAndUser(int id, User user) {
                return null;
            }

            @Override
            public List<Invoice> getByUser(User user) {
                return null;
            }

            @Override
            public List<Invoice> getByOrder(Order order) throws DBException {
                return null;
            }
        };
    }
}
