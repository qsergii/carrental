package com.epam.carrental.export;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ExcelTest {

    @Test
    void export() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        XSSFWorkbook workbook = mock(XSSFWorkbook.class);

        MockedStatic<XSSFWorkbook> workbookStatic = mockStatic(XSSFWorkbook.class);
        MockedConstruction<XSSFWorkbook> XSSFWorkbookConstruction = mockConstruction(XSSFWorkbook.class);

        XSSFSheet sheet = mock(XSSFSheet.class);
        when(workbook.createSheet()).thenReturn(sheet);
        when(sheet.createRow(0)).thenReturn(null);
        List list = new ArrayList();

        Assertions.assertThrows(
                NullPointerException.class,
                () -> new Excel().export(request, response, list));

        assertEquals(1, XSSFWorkbookConstruction.constructed().size());
        workbookStatic.close();
    }
}
