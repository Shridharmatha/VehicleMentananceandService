package in.shridhar.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.shridhar.entity.Booking;
import in.shridhar.entity.Center;
import in.shridhar.entity.Services;
import in.shridhar.entity.Vehicle;
import in.shridhar.repository.InterfaceBooking;
import in.shridhar.service.BookingService;
import in.shridhar.service.CenterService;
import in.shridhar.service.EmailService;
import in.shridhar.service.PDFGenerationService;
import in.shridhar.service.ServiceService;
import in.shridhar.service.VehicleService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/center")

public class CenterController {
	
	@Autowired
	private CenterService csrvc;
	
	@Autowired
	private VehicleService vsrvc;
	
	@GetMapping("regs")
	public String creg()
	{
		return "centerregister";
	}
	
	
	@PostMapping("/cregister")
	public String cregister(@ModelAttribute Center entity,Model model)
	{
		Center mid=csrvc.checkmail(entity.getCmail());
		String page="";
		
		if(mid ==null)
		{
			int cid=csrvc.centerregister(entity);
			String cname=entity.getCname();
			if(cid>0)
			{
				model.addAttribute("statustype","success");
				model.addAttribute("statusmessage",cname+" Registered Successfully by id "+cid);
				page="redirect:/center/regs";
			}else
			{
				model.addAttribute("statustype","error");
				model.addAttribute("statusmessage"," Registeration UnSuccessfull)");
				page="redirect:/center/regs";
			}
		}
		return page;
	}
	
	@GetMapping("/all")
	public String getall(Model model)
	{
		List<Center> list=csrvc.getAllCenters();
		model.addAttribute("list",list);
		System.out.println(list);
		model.addAttribute("statustype","success");
		model.addAttribute("statusmessage"," Registered Successfully by id");
		return "allcenters";
	}
	
	
	
	
	//===================================================================================================================
	
	
	@Autowired
	private ServiceService ssrvc;
	
	
	@GetMapping("/services")
	public String service()
	{
		return "services";
	}
	
	@PostMapping("/sreg")
	public String sregister(@ModelAttribute Services entity,Model model)
	{
		//String Status=ssrvc.serviceexist(entity.getService());
		String page="";
//		if(Status ==null)
//		{
			int sid=ssrvc.save(entity);
			if(sid>0)
			{
				model.addAttribute("statustype","success");
				model.addAttribute("statusmessage"," Registered Successfully by id "+sid);
				page="redirect:/center/services";
			}else
			{
				model.addAttribute("statustype","error");
				model.addAttribute("statusmessage"," Registeration UnSuccessfull)");
				page="redirect:/center/services";
			}
		//}
		return page;
	}
	
	@GetMapping("/sall")
	public String sall(Model model)
	{
		String page="";
		List<Services> serv=ssrvc.getallServices();
		if(serv !=null)
		{
			model.addAttribute("list",serv);
			model.addAttribute("statustype","success");
			model.addAttribute("statusmessage"," Data fetched Successfully");
			page="allservice";
		}else
		{
			model.addAttribute("statustype","error");
			model.addAttribute("statusmessage"," Data fetching UnSuccessfull)");
			page="allservice";
		}
		return page;
	}
	
	@GetMapping("/supdate/{id}")
	public String supdate(@PathVariable int id,Model model)
	{
		Optional<Services> list=ssrvc.getoneservice(id);
		System.out.println(list);
		if(list !=null)
		{
			model.addAttribute("list",list);
		}
		
		return "updateservice";
	}
	
	@PostMapping("/serviceupdate")
	public String serviceupdate(@ModelAttribute Services entity,Model model)
	{
		Services up=ssrvc.supdate(entity);
		String page="";
		if(up !=null)
		{
			model.addAttribute("statustype","success");
			model.addAttribute("statusmessage"," Data updated Successfully");
			page="redirect:/center/supdate/"+up.getId();
		}
		return page;
	}
	
	
	
	
	
	//===================================================================================================================
	
	
	
	
	
	
	
	
	@Autowired
	private BookingService bsrvc;
	
	@Autowired
    private InterfaceBooking bookingRepository;
	
	@Autowired
    private EmailService emailService;

    @Autowired
    private PDFGenerationService pdfGenerationService;
	
	
	@GetMapping("/booking/{cid}")
	public String bookService(@PathVariable int cid, Model model,HttpSession session) {
	    String address = csrvc.getcentername(cid);
		List<Services> serv=ssrvc.getallServices();
	
		String ownername = (String) session.getAttribute("uname");
		String ownermail = (String) session.getAttribute("umail");
		System.out.println("owner name is "+ownername);
		Vehicle ownerdetails=vsrvc.getuserdetailes(ownername);
	    model.addAttribute("centername", address);
	    model.addAttribute("serviceTypes",serv);
	    model.addAttribute("oname",ownername);
	    model.addAttribute("omail",ownermail);
	    model.addAttribute("ownerdata",ownerdetails);
	    
	    System.out.println(ownerdetails);
	    
	    model.addAttribute("statustype","success");
		model.addAttribute("statusmessage"," Booking data fetched Successfully ");

	    return "bookingservice";
	}
	
	
	@PostMapping("/booking")
	public String booking(@ModelAttribute Booking entity,Model model)
	{
		String page="";
		int sid=bsrvc.register(entity);
		if(sid>0)
		{
			model.addAttribute("statustype","success");
			model.addAttribute("statusmessage"," Booking Successfull By id "+sid);
			 page="redirect:/center/booking/"+sid;

		}else
		{
			model.addAttribute("statustype","error");
			model.addAttribute("statusmessage"," Booking UnSuccessfull");
		    page="redirect:/center/booking/"+entity.getSid();
		}
		return page;
	}
	
//	@GetMapping("/bookings")
//	public String bookings(Model model) {
//		
//		List<Booking> list=bsrvc.getallbooking();
//		String page="";
//		if(list !=null)
//		{
//			model.addAttribute("list",list);
//			System.out.println(list);
//			model.addAttribute("statustype","success");
//			model.addAttribute("statusmessage","Successfully fetched data ");
//			page="allbookings";
//		}else
//		{
//			model.addAttribute("list",list);
//			model.addAttribute("statustype","error");
//			model.addAttribute("statusmessage","Unable to fetch the data ");
//			page="allbookings";
//		}
//	 
//	    return page;
//	}
	
	@PostMapping("/accept/{sid}")
	public String accept(@PathVariable int sid,Model model)
	{
		System.out.println("sid "+sid);
		String page="";
		if(sid>0)
		{
			int status=bsrvc.acceptbooking(sid);
			if(status >0)
			{
				System.out.println(status);
				model.addAttribute("statustype","success");
				model.addAttribute("statusmessage","data accepted Successfully");
				page="redirect:/center/bookings";
			}else
			{
				model.addAttribute("statustype","error");
				model.addAttribute("statusmessage","unable to accept data ");
				page="redirect:/center/bookings";
			}
		}
		return page;
	}
	
	
	@PostMapping("/reject/{sid}")
	public String reject(@PathVariable int sid,Model model)
	{
		System.out.println("sid "+sid);
		String page="";
		if(sid>0)
		{
			int status=bsrvc.rejectbooking(sid);
			if(status >0)
			{
				System.out.println(status);
				model.addAttribute("statustype","success");
				model.addAttribute("statusmessage","data rejected successfully");
				page="redirect:/center/bookings";
			}else
			{
				model.addAttribute("statustype","error");
				model.addAttribute("statusmessage","unable to reject data ");
				page="redirect:/center/bookings";
			}
		}
		return page;
	}
	
	
	@PostMapping("/complete/{sid}")
	public String completed(@PathVariable int sid, Model model) throws IOException, MessagingException {
	    System.out.println("sid " + sid);
	    String page = "";

	    if (sid > 0) {
	        int status = bsrvc.completebooking(sid);
	        if (status > 0) {
	            System.out.println(status);
	            model.addAttribute("statustype", "success");
	            model.addAttribute("statusmessage", "Service completed Successfully");

	            // Proceed with PDF and email
	            Booking booking = bookingRepository.findById(sid);//.orElse(null);
	            if (booking != null) {
	                // Mark the status as completed (already done above, this line is optional now)
	                booking.setStatus("Completed");
	                bookingRepository.save(booking);

	                // Generate PDF
	                byte[] pdfContent = pdfGenerationService.generateReceiptPDF(booking);

	                // Send the email
	                emailService.sendReceiptToUser(booking, pdfContent);

	                model.addAttribute("message", "Booking marked as completed and receipt sent to " + booking.getEmail());
	            } else {
	                model.addAttribute("statustype", "error");
	                model.addAttribute("statusmessage", "Booking not found while sending email");
	            }

	            page = "redirect:/center/bookings";
	        } else {
	            model.addAttribute("statustype", "error");
	            model.addAttribute("statusmessage", "unable to update data ");
	            page = "redirect:/center/bookings";
	        }
	    }

	    return page;
	}

	
	
	@GetMapping("/bookings")
	public String showBookings(Model model,HttpSession session) {
		String username = (String) session.getAttribute("uname");

	    List<Booking> bookings;
	    boolean isAdmin = "Shridhar Mathapati".equals(username);

	    if (isAdmin) {
	        bookings = bsrvc.getallbooking(); 
	    } else {
	        bookings = bsrvc.getBookingsByCustomerName(username); 
	    }

	    model.addAttribute("list", bookings);
	    model.addAttribute("isAdmin", isAdmin);
	    return "allbookings";
	}


	
	
	

}
