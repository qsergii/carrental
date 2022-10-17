package com.epam.carrental.dao;

import com.epam.carrental.dao.entity.Brand;
import com.epam.carrental.dao.entity.Car;
import com.epam.carrental.dao.entity.Quality;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarDaoTest {

    @Test
    void classCreate() {
        CarDao carDao = new CarDao() {
            @Override
            public void insert(Car car) {

            }

            @Override
            public boolean update(Car car) {
                return false;
            }

            @Override
            public List<Car> getAll(boolean onlyUnblocked) {
                return null;
            }

            @Override
            public Object[] getAll(String brandId, String qualityId, String sortName, int page) {
                return new Object[0];
            }

            @Override
            public Car getById(int id, boolean onlyUnblocked) {
                return null;
            }

            @Override
            public List<Car> getByBrand(Brand brand) {
                return null;
            }

            @Override
            public List<Car> getByQuality(Quality quality) {
                return null;
            }

            @Override
            public void delete(Car car) throws DBException {

            }
        };
    }
}
