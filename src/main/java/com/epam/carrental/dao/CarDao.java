package com.epam.carrental.dao;

import com.epam.carrental.dao.entity.Car;

import java.util.List;

public abstract class CarDao {
    public abstract void insert(Car car);
    public abstract boolean update(Car car);
    public abstract List<Car> getAll();
    public abstract Object[] getAll(String brandId, String qualityId, String sortName, int page);
//    public abstract HomeController.CarsInfo getAll(String brandId, String qualityId, String sortName, int page);
    public abstract Car getById(int id);
}
