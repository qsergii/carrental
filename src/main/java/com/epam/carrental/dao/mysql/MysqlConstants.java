package com.epam.carrental.dao.mysql;

public abstract class MysqlConstants {
    private MysqlConstants(){}

    /* CARS BRANDS */
    public static final String GET_ALL_CAR_BRAND = "SELECT * FROM brands ORDER BY name";
    public static final String GET_CAR_BRAND_BY_ID = "SELECT * FROM brands WHERE id=?";
    public static final String ADD_CAR_BRAND = "INSERT INTO brands (name) VALUES (?)";

    public static final String DELETE_CAR_BRAND_BY_ID = "DELETE FROM brands WHERE id=?";

    /* CARS */

    public static final String GET_ALL_CAR = "SELECT * FROM cars";
    public static final String ADD_CAR = "INSERT INTO cars (id, name, description, blocked, price, quality_class, brand_id) VALUES (default, ?, ?, ?, ?, ?, ?)";

    /* USERS */

    public static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id=?";
    public static final String GET_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";

    public static final String INSERT_USER = "INSERT INTO users (login, password, role, blocked) VALUES (?, ?, ?, ?)";
}
