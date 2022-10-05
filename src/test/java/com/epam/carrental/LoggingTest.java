package com.epam.carrental;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;

public class LoggingTest {

    @Test
    public void makeDescription() {
        Assertions.assertEquals(true,
                Logging.makeDescription(new SQLException("Wrong request"))
                        .contains("Wrong request")
        );
    }
}
