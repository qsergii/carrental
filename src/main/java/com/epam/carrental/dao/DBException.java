package com.epam.carrental.dao;

import java.sql.SQLException;

public class DBException extends Exception{
    public DBException(String message, SQLException e) {
        super(message);
    }
}
