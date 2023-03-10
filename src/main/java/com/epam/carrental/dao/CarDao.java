package com.epam.carrental.dao;

import com.epam.carrental.dao.entity.Brand;
import com.epam.carrental.dao.entity.Car;
import com.epam.carrental.dao.entity.Quality;

import java.util.List;

/**
 * Abstract class to car method
 */
public abstract class CarDao {
    public abstract void insert(Car car);

    public abstract boolean update(Car car);

    public abstract List<Car> getAll(boolean onlyUnblocked);

    public abstract Object[] getAll(String brandId, String qualityId, String sortName, int page);

    public abstract Car getById(int id, boolean onlyUnblocked);

    public abstract List<Car> getByBrand(Brand brand);

    public abstract List<Car> getByQuality(Quality quality);

    public abstract void delete(Car car) throws DBException;
}
