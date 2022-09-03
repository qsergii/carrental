package com.epam.carrental.dao;

import com.epam.carrental.entity.Car;

import java.util.List;

public abstract class CarDao {
    public abstract void insert(Car car);
    public abstract boolean update(Car car);
    public abstract List<Car> getAll();
    public abstract Car getById(int id);
}
