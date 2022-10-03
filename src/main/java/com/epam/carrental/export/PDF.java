package com.epam.carrental.export;

import com.epam.carrental.LanguageBundle;
import com.epam.carrental.dao.entity.Invoice;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Create PDF files
 */
public class PDF implements Exporter {
    private Font font;

    @Override
    public void export(HttpServletRequest request, HttpServletResponse response, List docs) {
        try {
            response.setContentType("application/pdf");
            response.setHeader(
                    "Content-disposition",
                    "attachment; filename=export.pdf");

            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());

            document.open();

            String fontFilePath = PDF.class.getClassLoader().getResource("fonts/FreeSans.ttf").getFile();
            BaseFont bf = BaseFont.createFont(fontFilePath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            font = new Font(bf, 11, Font.NORMAL);

            Chunk chunk = new Chunk(lang("invoices.Invoice"), font(16));
            document.add(chunk);

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(new Paragraph(" "));
            document.add(getTable(docs));

            document.close();
        } catch (IOException | DocumentException | NullPointerException e) {
            throw new RuntimeException("can't create pdf file: " + e.getMessage(), e);
        }
    }

    private PdfPTable getTable(List<Invoice> list) throws DocumentException {
        // table header
        PdfPTable table = new PdfPTable(5);

        // Header
        addHeaderCell(table, lang("invoices.Invoices"));
        addHeaderCell(table, lang("orders.Order"));
        addHeaderCell(table, lang("Amount"));
        addHeaderCell(table, lang("Type"));
        addHeaderCell(table, lang("Payed"));

        float totalAmount = 0;
        for (Invoice doc : list) {
            table.addCell(String.valueOf(doc.getId()));
            table.addCell(String.valueOf(doc.getOrder().getId()));

            PdfPCell cell = new PdfPCell(new Phrase(String.format("%.2f", doc.getAmount())));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            table.addCell(new Phrase(lang("type." + String.valueOf(doc.getType())), font));
            table.addCell(lang(doc.isPayed() ? "Yes" : "No"));

            totalAmount += doc.getAmount();
        }

        // total
        PdfPCell cell1 = new PdfPCell(new Phrase(String.valueOf(list.size())));
        table.addCell(cell1);

        table.addCell("");

        PdfPCell cell3 = new PdfPCell(new Phrase(String.format("%.2f", totalAmount)));
        cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cell3);

        table.addCell("");
        table.addCell("");

        return table;
    }

    private void addHeaderCell(PdfPTable table, String text) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setBorderWidth(2);
        cell.setPhrase(new Phrase(text, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }

    private String lang(String key) {
        return LanguageBundle.getString(key);
    }

    private Font font(int size) throws DocumentException, IOException {
        String fontFilePath = PDF.class.getClassLoader().getResource("fonts/FreeSans.ttf").getFile();
        BaseFont bf = BaseFont.createFont(fontFilePath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        return new Font(bf, size, Font.NORMAL);
    }

}
