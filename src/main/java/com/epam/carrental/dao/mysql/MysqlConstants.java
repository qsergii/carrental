package com.epam.carrental.dao.mysql;

public final class MysqlConstants {
    private MysqlConstants(){}

    /* BRANDS */

    public static final String BRAND_GET_ALL = "SELECT * FROM brands ORDER BY name";
    public static final String BRAND_GET_ALL_AVAILIBLE =
            "SELECT DISTINCT\n" +
                    "    brands.*\n" +
                    "FROM\n" +
                    "    brands\n" +
                    "        JOIN cars\n" +
                    "            ON brands.id = cars.brand_id\n" +
                    "ORDER BY name";
    public static final String BRAND_GET_BY_ID = "SELECT * FROM brands WHERE id=?";
    public static final String BRAND_ADD = "INSERT INTO brands (name) VALUES (?)";
    public static final String BRAND_UPDATE = "UPDATE brands SET name=? WHERE id=?";
    public static final String BRAND_DELETE_BY_ID = "DELETE FROM brands WHERE id=?";

    /* QUALITIES */

    public static final String QUALITY_GET_ALL = "SELECT * FROM qualities ORDER BY name";
    public static final String QUALITIES_GET_ALL_AVAILIBLE =
            "SELECT DISTINCT\n" +
                    "    qualities.*\n" +
                    "FROM\n" +
                    "    qualities\n" +
                    "        JOIN cars\n" +
                    "            ON qualities.id = cars.quality_id\n" +
                    "ORDER BY name";
    public static final String QUALITY_GET_BY_ID = "SELECT * FROM qualities WHERE id=?";
    public static final String QUALITY_ADD = "INSERT INTO qualities (name) VALUES (?)";
    public static final String QUALITY_UPDATE = "UPDATE qualities SET name=? WHERE id=?";
    public static final String QUALITY_DELETE_BY_ID = "DELETE FROM qualities WHERE id=?";

    /* CARS */

    public static final String GET_ALL_CAR = "SELECT * FROM cars";

    public static final String GET_CAR_BY_ID = "SELECT * FROM cars WHERE id=?";
//    public static final String CAR_INSERT = "INSERT INTO cars (id, name, description, blocked, price, quality_id, brand_id) VALUES (default, ?, ?, ?, ?, ?, ?)";
//    public static final String CAR_UPDATE = "UPDATE cars SET " +
//            "name=?, description=?, blocked=?, price=?, quality_id=?, brand_id=? " +
//            "WHERE id=?";

    /* USERS */

    public static final String USERS_GET_ALL = "SELECT * FROM users";
//    public static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id=?";
    public static final String GET_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";
//    public static final String INSERT_USER = "INSERT INTO users (login, password, role, blocked) VALUES (?, ?, ?, ?)";
    //public static final String USER_UPDATE = "UPDATE users SET login=?, password=?, role=?, blocked=? WHERE id=?";

    /* ORDERS */

    public static final String ORDER_GET_ALL = "SELECT * FROM orders";
    public static final String ORDER_GET_BY_ID = "SELECT * FROM orders WHERE id=?";
    public static final String ORDER_UPDATE = "UPDATE orders SET " +
            "date=?, user_id=?, with_driver=?, lease_term=?, passport_number=?, passport_valid=?, car_id=?, price=?, " +
            "rejected=?, reject_reason=? " +
            "WHERE id=?";

    /* INVOICES */
    public static final String INVOICE_GET_ALL = "SELECT * FROM invoices";
    public static final String INVOICE_GET_BY_ID = "SELECT * FROM invoices WHERE id=?";
    public static final String INVOICE_INSERT = "INSERT INTO invoices " +
            "(order_id, type, amount, payed, date, payed_date, user_id) VALUES " +
            "(?, ?, ?, ?, ?, ?, ?)";
    public static final String INVOICE_UPDATE =
            "UPDATE invoices " +
            "SET" +
                    " order_id=?, type=?, amount=?, payed=?, date=?, payed_date=?" +
            "WHERE id=?";

}
