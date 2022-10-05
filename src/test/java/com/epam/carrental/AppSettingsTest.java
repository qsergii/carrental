package com.epam.carrental;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppSettingsTest {

    @Test
    public void test() {
        AppSettings appSettings = new AppSettings();
        Assertions.assertNotNull(appSettings);
    }

}
