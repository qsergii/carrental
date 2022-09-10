package com.epam.carrental.dao.mysql;

public abstract class MysqlConstants {
    private MysqlConstants(){}

    /* BRANDS */

    public static final String BRAND_GET_ALL = "SELECT * FROM brands ORDER BY name";
    public static final String BRAND_GET_BY_ID = "SELECT * FROM brands WHERE id=?";
    public static final String BRAND_ADD = "INSERT INTO brands (name) VALUES (?)";
    public static final String BRAND_UPDATE = "UPDATE brands SET name=? WHERE id=?";
    public static final String BRAND_DELETE_BY_ID = "DELETE FROM brands WHERE id=?";

    /* CARS */

    public static final String GET_ALL_CAR = "SELECT * FROM cars";
    public static final String GET_CAR_BY_ID = "SELECT * FROM cars WHERE id=?";
    public static final String CAR_INSERT = "INSERT INTO cars (id, name, description, blocked, price, quality_class, brand_id) VALUES (default, ?, ?, ?, ?, ?, ?)";
    public static final String CAR_UPDATE = "UPDATE cars SET name=?, description=?, blocked=?, price=?, quality_class=?, brand_id=? WHERE id=?";

    /* USERS */

    public static final String USERS_GET_ALL = "SELECT * FROM users";
    public static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id=?";
    public static final String GET_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";
    public static final String INSERT_USER = "INSERT INTO users (login, password, role, blocked) VALUES (?, ?, ?, ?)";
    public static final String USER_UPDATE = "UPDATE users SET login=?, password=?, role=?, blocked=? WHERE id=?";

    /* ORDERS */

    public static final String ORDER_GET_BY_ID = "SELECT * FROM orders WHERE id=?";

    public static final String ORDER_INSERT = "INSERT INTO orders " +
            "(user_id, with_driver, lease_term, passport_number, passport_valid, car_id, price) VALUES " +
            "(?, ?, ?, ?, ?, ?, ?)";
}
