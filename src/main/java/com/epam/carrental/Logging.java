package com.epam.carrental;

import org.codehaus.plexus.util.ExceptionUtils;

import java.sql.SQLException;

/**
 * Util for create debug info
 * */
public class Logging {

    public static String makeDescription(Exception exception) {
        StringBuilder builder = new StringBuilder(
                "EXCEPTION:\n"
                        + " - message:" + exception.getMessage() + "\n"
                        + " - stack trace:" + ExceptionUtils.getStackTrace(exception));


        if (exception instanceof SQLException) {
            builder.append("Error Code = " + ((SQLException) exception).getErrorCode());
            builder.append("SQL state = " + ((SQLException) exception).getSQLState());
            builder.append("printTrace /n");
        }

        return builder.toString();
    }
}
