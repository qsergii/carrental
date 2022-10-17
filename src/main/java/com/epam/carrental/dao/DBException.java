package com.epam.carrental.dao;

import java.sql.SQLException;

/**
 * Custom exception for database exceptions
 */
public class DBException extends Exception {
    public DBException(String message, SQLException e) {
        super(message);
    }
}
