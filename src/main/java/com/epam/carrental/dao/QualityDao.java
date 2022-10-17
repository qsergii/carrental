package com.epam.carrental.dao;

import com.epam.carrental.dao.entity.Quality;

import java.util.List;

/**
 * Abstract class to quality methods
 */
public abstract class QualityDao {
    public abstract void insert(Quality quality) throws DBException;

    public abstract void update(Quality quality) throws DBException;

    public abstract List<Quality> getAll();

    public abstract List<Quality> getAllAvailible();

    public abstract Quality getById(int id);

    public abstract void delete(Quality quality) throws DBException;
}
