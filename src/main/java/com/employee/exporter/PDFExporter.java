package com.employee.exporter;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import com.employee.entities.Employee;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

public class PDFExporter {

    private List<Employee> employee;

    public PDFExporter(List<Employee> employee) {
        this.employee = employee;
    }

    public void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        BaseColor gray = new BaseColor(Color.GRAY.getRed(), Color.GRAY.getGreen(), Color.GRAY.getBlue());

        cell.setBackgroundColor(gray);
        cell.setPadding(6);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.WHITE);

        cell.setPhrase(new Phrase("Employee ID", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("NAME", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("SALARY", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("ADDRESS", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("DESIGNATION", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("PRIMARY SKILL", font));
        table.addCell(cell);
    }

    public void writeTableData(PdfPTable table) {
        for (Employee emp : employee) {
            table.addCell(emp.getEmployee_id()); // Use String values directly
            table.addCell(emp.getName());
            table.addCell(String.valueOf(emp.getSalary())); // Convert to String
            table.addCell(emp.getAddress());
            table.addCell(emp.getDesignation());
            table.addCell(emp.getPrimary_skill());
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        // Set response content type for PDF
        response.setContentType("application/pdf");

        // Set response header for PDF attachment
        response.setHeader("Content-Disposition", "attachment; filename=employee_details.pdf");

        // Create the PDF document
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(BaseColor.RED);

        Paragraph p = new Paragraph("Employee Details", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] { 3.5f, 3.5f, 2.5f, 4.0f, 4.0f, 4.0f });
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);
        document.add(table);
        document.close();
    }
}
