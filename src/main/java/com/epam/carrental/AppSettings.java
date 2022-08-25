package com.epam.carrental;

import java.io.InputStream;
import java.util.Properties;

public class AppSettings {

    public final static Properties PROPERTIES = new Properties();

    static {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = loader.getResourceAsStream("settings.properties")) {
            PROPERTIES.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Properties file is missing", e);
        }
    }
}
