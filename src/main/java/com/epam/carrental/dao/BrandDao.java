package com.epam.carrental.dao;

import com.epam.carrental.dao.entity.Brand;

import java.util.List;

public abstract class BrandDao {
    public abstract void create(Brand carBrand);
    public abstract boolean update(Brand carBrand);
    public abstract List<Brand> getAll();
    public abstract List<Brand> getAllAvailible();
    public abstract Brand getById(int id);
    public abstract boolean delete(Brand carBrand);
}
