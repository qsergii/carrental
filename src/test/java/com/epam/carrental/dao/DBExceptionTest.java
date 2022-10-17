package com.epam.carrental.dao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DBExceptionTest {

    @Test
    void classCreate() {
        DBException dbException = new DBException(null, null);
    }

}
