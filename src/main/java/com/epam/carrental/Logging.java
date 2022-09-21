package com.epam.carrental;

public class Logging {

    public static String makeDescription(Exception exception){
        return "Message:"  + exception.getMessage() + "\n"
        + "trace:" + exception.getStackTrace();
    }
}
