package com.epam.carrental.dao;

import com.epam.carrental.entity.CarBrand;

import java.util.List;

public abstract class CarBrandDao {
    public abstract void create(CarBrand carBrand);
    public abstract List<CarBrand> getAll();
    public abstract CarBrand getById(int id);
}
