package com.epam.carrental.dao.mysql;

public abstract class MysqlConstants {
    private MysqlConstants(){}

    /* CARS BRANDS */
    public static final String GET_ALL_CAR_BRAND = "SELECT * FROM cars_brands ORDER BY name";
    public static final String GET_CAR_BRAND_BY_ID = "SELECT * FROM cars_brands WHERE id=?";
    public static final String ADD_CAR_BRAND = "INSERT INTO cars_brands (name) VALUES (?)";

    /* CARS */

    public static final String GET_ALL_CAR = "SELECT * FROM cars";
    public static final String ADD_CAR = "INSERT INTO cars (id, name, description, blocked, price, quality_class, brand_id) VALUES (default, ?, ?, ?, ?, ?, ?)";
}
