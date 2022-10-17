package com.epam.carrental.dao;

import com.epam.carrental.dao.entity.Quality;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QualityDaoTest {

    @Test
    void classCreate() {
        QualityDao qualityDao = new QualityDao() {
            @Override
            public void insert(Quality quality) throws DBException {

            }

            @Override
            public void update(Quality quality) throws DBException {

            }

            @Override
            public List<Quality> getAll() {
                return null;
            }

            @Override
            public List<Quality> getAllAvailible() {
                return null;
            }

            @Override
            public Quality getById(int id) {
                return null;
            }

            @Override
            public void delete(Quality quality) throws DBException {

            }
        };
    }
}
