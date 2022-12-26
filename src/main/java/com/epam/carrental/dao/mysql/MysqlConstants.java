package com.epam.carrental.dao.mysql;

public final class MysqlConstants {
    private MysqlConstants() {
    }

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

    /* USERS */

    public static final String USERS_GET_ALL = "SELECT * FROM users";
    public static final String GET_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";

    /* ORDERS */

    public static final String ORDER_GET_BY_ID = "SELECT * FROM orders WHERE id=?";

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
