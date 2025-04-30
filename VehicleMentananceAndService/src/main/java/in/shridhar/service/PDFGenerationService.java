package in.shridhar.service;

import in.shridhar.entity.Booking;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PDFGenerationService {

    public byte[] generateReceiptPDF(Booking booking) throws DocumentException, IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, byteArrayOutputStream);

        document.open();
        document.add(new Paragraph("Booking Receipt"));
        document.add(new Paragraph("Customer Name: " + booking.getCustomerName()));
        document.add(new Paragraph("Vehicle Number: " + booking.getVehicleNumber()));
        document.add(new Paragraph("Service Center: " + booking.getCenterName()));
        document.add(new Paragraph("Service Type: " + booking.getServiceType()));
        document.add(new Paragraph("Service Date: " + booking.getServiceDate()));
        document.add(new Paragraph("Status: " + booking.getStatus()));
        document.add(new Paragraph("Cost: " + booking.getCost()));
        document.close();

        return byteArrayOutputStream.toByteArray();
    }
}
