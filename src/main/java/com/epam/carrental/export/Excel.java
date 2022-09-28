package com.epam.carrental.export;

import com.epam.carrental.dao.entity.Invoice;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Excel implements Exporter{

    @Override
    public void export(HttpServletRequest request, HttpServletResponse response, List docs) {
        try{
            response.setContentType("application/xlsx");
            response.setHeader(
                    "Content-disposition",
                    "inline; filename=export.xlsx");

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Invoices");
            writeHeaderLine(sheet);
            writeDataLines(workbook, sheet, docs);
            workbook.write(response.getOutputStream());
//            String excelFilePath = "c:\\temp\\Reviews-export.xlsx";
//            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
//            workbook.write(outputStream);
            workbook.close();

        }catch (IOException e){
            throw new RuntimeException("can't create xlsx file: " + e.getMessage(), e);
        }
    }
    private void writeHeaderLine(XSSFSheet sheet) {

        Row headerRow = sheet.createRow(0);

        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Invoice");

        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("Order");

        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("Amount");

        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("Type");

        headerCell = headerRow.createCell(4);
        headerCell.setCellValue("Payed");
    }

    private void writeDataLines(XSSFWorkbook workbook,
                                XSSFSheet sheet, List<Invoice> docs) {
        int rowCount = 1;

        for (Invoice doc : docs) {

            Row row = sheet.createRow(rowCount++);

            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(doc.getId());

            cell = row.createCell(columnCount++);
            cell.setCellValue(doc.getOrder().getId());

            cell = row.createCell(columnCount++);

            CellStyle cellStyle = workbook.createCellStyle();
            CreationHelper creationHelper = workbook.getCreationHelper();
            cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
            cell.setCellStyle(cellStyle);

            cell.setCellValue(doc.getType().toString());

            cell = row.createCell(columnCount++);
            cell.setCellValue(doc.getAmount());

            cell = row.createCell(columnCount);
            cell.setCellValue(doc.isPayed());

        }
    }
}