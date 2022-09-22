package com.epam.carrental;

import org.codehaus.plexus.util.ExceptionUtils;

public class Logging {

    public static String makeDescription(Exception exception){
        return "EXCEPTION:\n"
        + " - message:"  + exception.getMessage() + "\n"
        + " - stack trace:" + ExceptionUtils.getStackTrace(exception);
    }
}
