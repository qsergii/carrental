package com.epam.carrental.dao;

import com.epam.carrental.entity.Brand;
import com.epam.carrental.entity.Quality;

import java.util.List;

public abstract class QualityDao {
    public abstract void create(Quality quality);
    public abstract boolean update(Quality quality);
    public abstract List<Quality> getAll();
    public abstract List<Quality> getAllAvailible();
    public abstract Quality getById(int id);
    public abstract boolean delete(Quality quality);
}
