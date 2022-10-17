package com.epam.carrental.dao;

import com.epam.carrental.dao.entity.Brand;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BrandDaoTest {

    @Test
    void classCreateTest() {
        BrandDao brandDao = new BrandDao() {
            @Override
            public void insert(Brand carBrand) throws DBException {

            }

            @Override
            public boolean update(Brand carBrand) throws DBException {
                return false;
            }

            @Override
            public List<Brand> getAll() {
                return null;
            }

            @Override
            public List<Brand> getAllAvailible() {
                return null;
            }

            @Override
            public Brand getById(int id) {
                return null;
            }

            @Override
            public void delete(Brand carBrand) throws DBException {

            }
        };
    }

}
