package com.epam.carrental.dao;

import com.epam.carrental.dao.entity.Car;
import com.epam.carrental.dao.entity.Order;
import com.epam.carrental.dao.entity.User;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderDaoTest {

    @Test
    void classCreate() {
        OrderDao orderDao = new OrderDao() {
            @Override
            public List<Order> getAll() {
                return null;
            }

            @Override
            public Order getById(int id) {
                return null;
            }

            @Override
            public List<Order> getByCar(Car car) throws DBException {
                return null;
            }

            @Override
            public boolean insert(Order order, Connection connection) {
                return false;
            }

            @Override
            public boolean update(Order order) {
                return false;
            }

            @Override
            public List<Order> getByUser(User user) {
                return null;
            }

            @Override
            public float getAmountSummaryByUser(User user) throws DBException {
                return 0;
            }
        };
    }
}
