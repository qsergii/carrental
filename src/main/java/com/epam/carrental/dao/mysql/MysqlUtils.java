package com.epam.carrental.dao.mysql;

public class MysqlUtils {
    static java.util.Date getDate(java.sql.Date dateMysql) {
        if (dateMysql == null) {
            return null;
        }
        return new java.util.Date(dateMysql.getTime());
    }

    static java.sql.Date getSqlDate(java.util.Date date) {
        if (date == null) {
            return null;
        }
        return new java.sql.Date(date.getTime());
    }
}
