package com.epam.carrental;

import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class LoggingTest {

    @Test
    public void makeDescription() {
        Assert.assertEquals(true,
                Logging.makeDescription(new SQLException("Wrong request"))
                        .contains("Wrong request")
        );
    }
}