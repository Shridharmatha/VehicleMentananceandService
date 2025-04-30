package in.shridhar.controller;


import in.shridhar.entity.Booking;
import in.shridhar.repository.InterfaceBooking;
import in.shridhar.service.EmailService;
import in.shridhar.service.PDFGenerationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.mail.MessagingException;
import java.io.IOException;

import java.util.Optional;

@Controller
@RequestMapping("/mail")
public class BookingController {

    

    

//    @PostMapping("/complete/{sid}")
//    public String markBookingAsCompleted(@PathVariable int sid, Model model) throws IOException, MessagingException {
//        // Fetch the booking by SID
//        Booking booking = bookingRepository.findById(sid);//.orElseThrow(() -> new RuntimeException("Booking not found"));
//
//        // Mark the status as completed
//        booking.setStatus("Completed");
//        bookingRepository.save(booking);
//
//        // Generate the receipt PDF
//        byte[] pdfContent = pdfGenerationService.generateReceiptPDF(booking);
//
//        // Send the email with the receipt
//        emailService.sendReceiptToUser(booking, pdfContent);
//
//        // Return a success message
//        model.addAttribute("message", "Booking marked as completed and receipt sent to " + booking.getEmail());
//        return "bookingConfirmation"; // Make sure this view exists
    }

