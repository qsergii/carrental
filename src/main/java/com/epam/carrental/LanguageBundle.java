package com.epam.carrental;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class LanguageBundle {
    private static final Logger log = LogManager.getLogger(LanguageBundle.class);

    private static Map<String, ResourceBundle> bundleMap = new HashMap();
    private static String language = null;

    static {
        bundleMap.put("en", ResourceBundle.getBundle("messages"));
        bundleMap.put("ua", ResourceBundle.getBundle("messages_ua", new Locale("uk", "UA")));
    }

    public static String getString(String key) {
        ResourceBundle bundle = bundleMap.get(language);
        String string = null;
        try {
            string = bundle.getString(key);
        } catch (MissingResourceException e) {
            log.error("No resource bundle for language " + language + " for key: " + key);
            string = key;
        }
        return string;
    }

    public static String lang(String key) {
        return LanguageBundle.getString(key);
    }

    public static String getLanguage() {
        return language;
    }

    public static void setLanguage(String language) {
        LanguageBundle.language = language;
    }


}
