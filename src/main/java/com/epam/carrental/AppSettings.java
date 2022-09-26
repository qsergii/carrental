package com.epam.carrental;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class for reading settings for application.
 * Read file "settings.properties"
 */
public class AppSettings {

    /**
     * Main property to store properties
     * */
    public final static Properties PROPERTIES = new Properties();

    static {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = loader.getResourceAsStream("settings.properties")) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Properties file is missing", e);
        }
    }
}
