package in.shridhar.service;

import in.shridhar.entity.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmailWithAttachment(String toEmail, String subject, String text, byte[] attachment) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        // true indicates multipart message
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(text);

        // Add attachment
        if (attachment != null) {
            helper.addAttachment("Receipt.pdf", () -> new java.io.ByteArrayInputStream(attachment));
        }

        javaMailSender.send(message);
    }

    public void sendReceiptToUser(Booking booking, byte[] pdfContent) {
        try {
            String subject = "Booking Receipt";
            String text = "Dear " + booking.getCustomerName() + ",\n\nYour booking has been marked as completed. Please find your receipt attached.";
           
            
            sendEmailWithAttachment(booking.getEmail(), subject, text, pdfContent);
            System.out.println("mail sent successfully");
            
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
