package com.epam.carrental.export;

import com.epam.carrental.dao.entity.Invoice;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Create PDF files
 * */
public class PDF implements Exporter{

    @Override
    public void export(HttpServletRequest request, HttpServletResponse response, List docs) {
        try {
            response.setContentType("application/pdf");
            response.setHeader(
                    "Content-disposition",
                    "attachment; filename=export.pdf");
//                    "inline; filename=export.pdf");

            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());

            document.open();

            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Chunk chunk = new Chunk("Invoices", font);
            document.add(chunk);

            document.add( Chunk.NEWLINE );
            document.add( Chunk.NEWLINE );
            document.add( new Paragraph( " " ) );
            document.add(getTable(docs));

            document.close();
        }catch (IOException | DocumentException e){
            throw new RuntimeException("can't greate pdf file: " + e.getMessage(), e);
        }
    }

    private PdfPTable getTable(List<Invoice> list) throws DocumentException {
        // table header
        PdfPTable table = new PdfPTable(5);

        // Header
        addHeaderCell(table, "Invoice");
        addHeaderCell(table, "Order");
        addHeaderCell(table, "Amount");
        addHeaderCell(table, "Type");
        addHeaderCell(table, "Payed");

        float totalAmount = 0;
        for (Invoice doc : list) {
            table.addCell(String.valueOf(doc.getId()));
            table.addCell(String.valueOf(doc.getOrder().getId()));

            PdfPCell cell = new PdfPCell(new Phrase(String.format("%.2f", doc.getAmount())));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            table.addCell(String.valueOf(doc.getType()));
            table.addCell(doc.isPayed() ? "Yes" : "No");

            totalAmount += doc.getAmount();
        }

        // total
        PdfPCell cell1 = new PdfPCell(new Phrase(String.valueOf(list.size())));
//        cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cell1);

        table.addCell("");

        PdfPCell cell3 = new PdfPCell(new Phrase(String.format("%.2f", totalAmount)));
        cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cell3);

        table.addCell("");
        table.addCell("");

        return table;
    }

    private void addHeaderCell(PdfPTable table, String text){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setBorderWidth(2);
        cell.setPhrase(new Phrase(text));
        table.addCell(cell);
    }

}
