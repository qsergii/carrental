package com.epam.carrental.export;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExportTest {

    @Test
    public void pdfTest() {
        Exporter export = Export.export("pdf");
        Assert.assertEquals(export.getClass(), PDF.class);
    }

    @Test
    public void xlsxTest() {
        Exporter export = Export.export("xlsx");
        Assert.assertEquals(export.getClass(), Excel.class);
    }

    @Test
    public void csvTest() {
        Exporter export = Export.export("csv");
        Assert.assertEquals(export.getClass(), CSV.class);
    }

}