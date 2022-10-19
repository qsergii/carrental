package com.epam.carrental;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ini4j.Ini;
import org.ini4j.IniPreferences;

import java.io.File;
import java.io.IOException;

/**
 * Store price for driver service
 * Data stores in file
 */
public class CarDriver {
    private static final Logger log = LogManager.getLogger(CarDriver.class);
    private static int price = 0;
    private static final String fileName;

    static {
        fileName = CarDriver.class.getClassLoader().getResource("driver.ini").getFile();
        try {
            Ini ini = new Ini(new File(fileName));
            java.util.prefs.Preferences prefs = new IniPreferences(ini);
            price = Integer.parseInt(prefs.node("driver").get("price", "0"));
        } catch (IOException e) {
            log.error(Logging.makeDescription(e));
        }
    }

    public static int getPrice() {
        return price;
    }

    public static void setPrice(int priceNew) {
        price = priceNew;
        writeToFile();
    }

    private static void writeToFile() {

        try {
            File file = new File(fileName);
            Ini ini = new Ini(file);
            ini.put("driver", "price", price);
            ini.store();
        } catch (IOException e) {
            log.error(Logging.makeDescription(e));
            throw new RuntimeException(e);
        }
    }
}
