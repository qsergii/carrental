package com.epam.carrental.export;

import com.epam.carrental.dao.entity.Invoice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.epam.carrental.LanguageBundle.lang;

/**
 * Generate csv file of invoices
 */
public class CSV implements Exporter {
    @Override
    public void export(HttpServletRequest request, HttpServletResponse response, List invoices) {
        try {
            response.setContentType("text/csv");
            response.setHeader(
                    "Content-disposition",
                    "attachment; filename=export.csv");
            response.setHeader("Content-Type", "text/csv; charset=UTF-8");
            writeCsv(invoices, ';', response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException("can't greate pdf file: " + e.getMessage(), e);
        }
    }

    private void writeCsv(List<Invoice> docs, char separator, OutputStream output) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));

        // header
        writer.append(lang("invoices.Invoices"));
        writer.append(separator);
        writer.append(lang("orders.Order"));
        writer.append(separator);
        writer.append(lang("Amount"));
        writer.append(separator);
        writer.append(lang("Type"));
        writer.append(separator);
        writer.append(lang("Payed"));
        writer.newLine();

        for (Invoice doc : docs) {
            writer.append(String.valueOf(doc.getId()));
            writer.append(separator);
            writer.append(String.valueOf(doc.getOrder().getId()));
            writer.append(separator);
            writer.append(String.format("%.2f", doc.getAmount()));
            writer.append(separator);
            writer.append(lang("type." + String.valueOf(doc.getType())));
            writer.append(separator);
            writer.append(lang(doc.isPayed() ? "Yes" : "No"));
            writer.newLine();
        }
        writer.flush();
    }
}
