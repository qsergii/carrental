package com.epam.carrental;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarDriverTest {

    @Test
    public void price() {
        CarDriver carDriver = new CarDriver();
        carDriver.setPrice(5);
        Assert.assertEquals(5, carDriver.getPrice());
    }

}