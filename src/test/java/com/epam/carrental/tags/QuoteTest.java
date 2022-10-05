package com.epam.carrental.tags;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuoteTest {

    @Test
    void doStartTag() {
        Quote quote = new Quote();
        Assertions.assertThrows(NullPointerException.class, () -> quote.doStartTag());
    }
}
