package com.epam.carrental.dao;

import com.epam.carrental.dao.entity.Brand;

import java.util.List;

/**
 * Abstract class to brands method
 */
public abstract class BrandDao {
    public abstract void insert(Brand carBrand) throws DBException;

    public abstract boolean update(Brand carBrand) throws DBException;

    public abstract List<Brand> getAll();

    public abstract List<Brand> getAllAvailible();

    public abstract Brand getById(int id);

    public abstract void delete(Brand carBrand) throws DBException;
}
