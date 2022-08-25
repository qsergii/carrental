package com.epam.carrental.dao.mysql;

public abstract class MysqlConstants {
    private MysqlConstants(){}

    public static final String ADD_CAR = "INSERT INTO cars (id, name, description, price, quality_class, brand_id) VALUES (default, ?, ?, ?, ?, ?)";
}
