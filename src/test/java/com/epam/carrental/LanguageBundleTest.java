package com.epam.carrental;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LanguageBundleTest {

    @Test
    void getString() {
        LanguageBundle.setLanguage("");
        Assertions.assertThrows(
                NullPointerException.class,
                () -> LanguageBundle.getString("none")
        );
    }

    @Test
    void lang() {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> LanguageBundle.getString("none")
        );
    }

    @Test
    void setLanguage() {
        LanguageBundle.setLanguage("en");
        Assertions.assertEquals(
                "en",
                LanguageBundle.getLanguage()
        );
    }
}
