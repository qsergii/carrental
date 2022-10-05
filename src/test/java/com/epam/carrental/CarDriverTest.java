package com.epam.carrental;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CarDriverTest {

    @Test
    public void price() {
        CarDriver carDriver = new CarDriver();
        carDriver.setPrice(5);
        Assertions.assertEquals(5, carDriver.getPrice());
    }

}
