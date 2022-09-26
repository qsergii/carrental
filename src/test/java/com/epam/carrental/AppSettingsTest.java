package com.epam.carrental;

import org.junit.Assert;
import org.junit.Test;

public class AppSettingsTest {

    @Test
    public void test(){
        AppSettings appSettings = new AppSettings();
        Assert.assertNotNull(appSettings);
    }

}