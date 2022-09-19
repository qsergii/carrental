package com.epam.carrental.dao;

import com.epam.carrental.DbException;
import com.epam.carrental.controllers.user.HomeController;
import com.epam.carrental.entity.Car;

import java.util.List;

public abstract class CarDao {
    public abstract void insert(Car car);
    public abstract boolean update(Car car);
    public abstract List<Car> getAll();
    public abstract HomeController.CarsInfo getAll(String brandId, String qualityId, String sortName, int page) throws DbException;
    public abstract Car getById(int id);
}
