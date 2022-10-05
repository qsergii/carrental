package com.epam.carrental.export;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;

public class ExportTest {

    @Test
    public void pdfTest() {
        Exporter export = Export.export("pdf");
        Assertions.assertEquals(export.getClass(), PDF.class);
    }

    @Test
    public void xlsxTest() {
        Exporter export = Export.export("xlsx");
        Assertions.assertEquals(export.getClass(), Excel.class);
    }

    @Test
    public void csvTest() {
        Exporter export = Export.export("csv");
        Assertions.assertEquals(export.getClass(), CSV.class);
    }

}
