package com.epam.carrental.dao;

import com.epam.carrental.entity.Car;
import java.sql.SQLException;
import java.util.List;

public abstract class CarDao {
    public void create(Car car) throws SQLException {}
    public abstract List<Car> getAll();
}
