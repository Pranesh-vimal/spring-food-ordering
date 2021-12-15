package com.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.model.Order;
import com.model.OrderItem;

import org.springframework.stereotype.Service;

@Service
public class PdfService {
    void generatePdf(Order order) {

        Document doc = new Document();

        String projectDir = Paths.get("").toAbsolutePath().toString();
        String pdfDir = projectDir + "\\src\\main\\resources\\static\\pdf\\";
        String fileName = pdfDir + "Invoice_" + order.getId() + ".pdf";

        try {
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(fileName));
            System.out.println("PDF created.");
            doc.open();

            Rectangle rect = new Rectangle(30, 30, 550, 800);
            rect.setBorder(2);
            rect.setBorderColor(BaseColor.BLACK);
            rect.setBorder(Rectangle.BOX);
            rect.setBorderWidth(2);
            doc.add(rect);

            doc.add(Chunk.NEWLINE);
            Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
            Paragraph paragraph = new Paragraph("Invoice for Order #" + order.getId(), font);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            doc.add(paragraph);
            doc.add(Chunk.NEWLINE);

            paragraph = new Paragraph("Customer Name: " + order.getName(), font);
            doc.add(paragraph);
            doc.add(Chunk.NEWLINE);

            paragraph = new Paragraph("Customer Email: " + order.getEmail(), font);
            doc.add(paragraph);
            doc.add(Chunk.NEWLINE);

            paragraph = new Paragraph("Customer Phone: " + order.getPhone(), font);
            doc.add(paragraph);
            doc.add(Chunk.NEWLINE);

            paragraph = new Paragraph("Payment Status: " + order.getPayment(), font);
            doc.add(paragraph);
            doc.add(Chunk.NEWLINE);

            paragraph = new Paragraph("Payment Id: " + order.getTransaction_id(), font);
            doc.add(paragraph);
            doc.add(Chunk.NEWLINE);

            Timestamp ts = order.getCreated_at();
            Date date = new Date();
            date.setTime(ts.getTime());
            String formattedDate = new SimpleDateFormat("dd/MM/yyyy hh.mm.ss a").format(date);
            paragraph = new Paragraph("Order Date: " + formattedDate, font);
            doc.add(paragraph);
            doc.add(Chunk.NEWLINE);

            paragraph = new Paragraph("Order Details: ", font);
            doc.add(paragraph);
            doc.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(3);

            Stream.of("Product Name", "Quantity", "Price")
                    .forEach(headerTitle -> {
                        PdfPCell header = new PdfPCell();
                        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                        header.setBackgroundColor(BaseColor.YELLOW);
                        header.setHorizontalAlignment(Element.ALIGN_CENTER);
                        header.setBorderWidth(2);
                        header.setPadding(4);
                        header.setPhrase(new Phrase(headerTitle, headFont));
                        table.addCell(header);
                    });

            for (OrderItem orderItem : order.getOrderItems()) {
                PdfPCell productNameCell = new PdfPCell(new Phrase(orderItem.getProduct().getName()));
                productNameCell.setPadding(4);
                productNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                productNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(productNameCell);

                PdfPCell quantityCell = new PdfPCell(new Phrase(String.valueOf(orderItem.getQuantity())));
                quantityCell.setPadding(4);
                quantityCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                quantityCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(quantityCell);

                PdfPCell priceCell = new PdfPCell(new Phrase(String.valueOf("Rs." + orderItem.getSubTotal())));
                priceCell.setPadding(4);
                priceCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                priceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(priceCell);
            }

            PdfPCell totalCell = new PdfPCell(new Phrase(String.valueOf("Rs." + order.getTotal())));
            totalCell.setColspan(3);
            totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            totalCell.setPadding(4);
            totalCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(totalCell);

            doc.add(table);
            doc.add(Chunk.NEWLINE);

            paragraph = new Paragraph("Thank you for shopping with us.", font);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            doc.add(paragraph);

            doc.close();
            writer.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (DocumentException e1) {
            e1.printStackTrace();
        }
    }
}
