package com.epam.carrental.export;

import com.epam.carrental.dao.entity.Invoice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

public class CSV implements Exporter {
    @Override
    public void export(HttpServletRequest request, HttpServletResponse response, List invoices) {
        try {
            response.setContentType("text/csv");
            response.setHeader(
                    "Content-disposition",
                    "attachment; filename=export.csv");
            writeCsv(invoices, ';', response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException("can't greate pdf file: " + e.getMessage(), e);
        }
    }

    public static <T> void writeCsv(List<Invoice> docs, char separator, OutputStream output) throws IOException {

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output, "UTF-8"));

        // header
        writer.append("Invoice");
        writer.append(separator);
        writer.append("Order");
        writer.append(separator);
        writer.append("Amount");
        writer.append(separator);
        writer.append("Type");
        writer.append(separator);
        writer.append("Payed");
        writer.newLine();

        for (Invoice doc : docs) {
            writer.append(String.valueOf(doc.getId()));
            writer.append(separator);
            writer.append(String.valueOf(doc.getOrder().getId()));
            writer.append(separator);
            writer.append(String.format("%.2f", doc.getAmount()));
            writer.append(separator);
            writer.append(String.valueOf(doc.getType()));
            writer.append(separator);
            writer.append(doc.isPayed() ? "Yes" : "No");
            writer.newLine();
        }
        writer.flush();
    }
}
