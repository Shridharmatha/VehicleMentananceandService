package in.shridhar.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.shridhar.entity.Booking;
import in.shridhar.entity.User;
import in.shridhar.service.BookingService;
import in.shridhar.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/Userdata")

public class UserController {
	
	@Autowired
	private UserService use; 
	
	@GetMapping("/reg")
	public String reg()
	{
		return "dashbord";
	}
	
	@GetMapping("/usereg")
	public String regs()
	{
		return "Register";
	}
	
	@GetMapping("/log")
	public String logs()
	{
		return "Login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session,HttpServletResponse response,Model model)
	{
		session.invalidate();
		
		model.addAttribute("statustype","success");
		model.addAttribute("statusmessage"," Logged Out Successfully");
		
		 response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		    response.setHeader("Pragma", "no-cache");
		    response.setDateHeader("Expires", 0);

		
		return "Login";
		
	}
	
	@GetMapping("admin")
	public String admin()
	{
		return "admin";
	}
	
	@GetMapping("/checkEmail")
	public ResponseEntity<Boolean> checkmail(Model model,@RequestParam String umail)
	{
		boolean check=use.checkUser(umail);
		return ResponseEntity.ok(check);
	}
	
	
	@GetMapping("/checkPass")
	public ResponseEntity<Boolean> checkpass(Model model,@RequestParam String upass)
	{
		boolean check=use.checkUserpass(upass);
		return ResponseEntity.ok(check);
	}
	
	
	@PostMapping("uregister")
	public String useregister(@ModelAttribute User entity,Model model,RedirectAttributes redirectAttributes) {
		boolean us=use.checkUser(entity.getUmail());
		
		String page="";
		if(us==false)
		{
			int uid=use.saveuser(entity);
			String uname=entity.getUname();
			if(uid>0)
			{
				redirectAttributes.addFlashAttribute("statustype","success");
				redirectAttributes.addFlashAttribute("statusmessage",uname+" Registered Successfully by id"+uid);
				model.addAttribute("statustype","success");
				model.addAttribute("statusmessage",uname+" Registered Successfully by id"+uid);
				page="Login";
			}else {
				model.addAttribute("error","Registeration Unsuccessfull");
				page="Register";
			}
		}else {
			model.addAttribute("error","Invalid Credentials !");
			page="Register";
		}
	
		
		return page;
	}
	
	@PostMapping("loginuser")
	public String loginuser(@ModelAttribute User entity,HttpSession session,Model model,RedirectAttributes redirectAttributes)
	{
		String page="";
		String status=use.loginUser(entity.getUmail(), entity.getUpass(), session);
		if(status.equals("success"))
		{
						
			model.addAttribute("uname",session.getAttribute("uname"));
			model.addAttribute("umail",session.getAttribute("umail"));
			model.addAttribute("uphone",session.getAttribute("uphone"));
			model.addAttribute("uid",session.getAttribute("uid"));
			model.addAttribute("uroll", session.getAttribute("uroll"));
			System.out.println(session.getAttribute("uid"));
			System.out.println(session.getAttribute("uroll"));
			System.out.println(session.getAttribute("uname"));
			
			if(entity.getUmail().equals("shridhar3584@gmail.com") && entity.getUpass().equals("12345678"))
			{
//				redirectAttributes.addFlashAttribute("statustype","success");
//				redirectAttributes.addFlashAttribute("statusmessage"," Logged in Successfully");
				
				model.addAttribute("statustype","success");
				model.addAttribute("statusmessage"," Admin Logged in Successfully");

				System.out.println("admin login successfull");
				page="admin";
			}else {
				
//				redirectAttributes.addFlashAttribute("statustype","error");
//				redirectAttributes.addFlashAttribute("statusmessage"," Login unsuccessfull");
				
				model.addAttribute("statustype","success");
				model.addAttribute("statusmessage","User Logged in Successfully");

				
				System.out.println("user login successfull");
				page="userdash";
			}
			
		
		}else {
			
//			redirectAttributes.addFlashAttribute("statustype","error");
//			redirectAttributes.addFlashAttribute("statusmessage"," Login unsuccessfull");
			model.addAttribute("statustype","error");
			model.addAttribute("statusmessage"," Login UnSuccessfull");
			
			model.addAttribute("message", "Login failed");
			System.out.println("Login failed..");
			page = "login";
		}
		
		return page;
	}
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("alll")
	public List<User> getAllUsers()
	{
		return use.getAllUsers();
	}
	
	//@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("all")
	public String allusers(Model model)
	{
		List<User> list=use.getalluser();
		String page="";
		if(list !=null)
		{
			model.addAttribute("list",list);
			model.addAttribute("statustype","success");
			model.addAttribute("statusmessage"," Data fetched Successfully");
			page="allUsers";
		}else
		{
			model.addAttribute("list",list);
			model.addAttribute("statustype","error");
			model.addAttribute("statusmessage"," Data fetching UnSuccessfull)");
			page="allUsers";
		}
		return page;
	}
	
	@GetMapping("/edit/{uid}")
	public String edituser(Model model,@PathVariable int uid)
	{
		Optional<User> list=use.getuser(uid);
		System.out.println(list);
		String page="";
		if(list !=null)
		{
			model.addAttribute("list",list);
			page="updateusers";
		}else
		{
			model.addAttribute("list",list);
			page="updateusers";
		}
		return page;
	}
	
	@PostMapping("update")
	public String updateuser(@ModelAttribute User entity,Model model,RedirectAttributes redirectAttributes) {
		User data=use.updateuser(entity);
		String page="";
		if(data !=null)
		{
//			model.addAttribute("statustype","success");
//			model.addAttribute("statusmessage","User data updated Successfully");
			
			redirectAttributes.addFlashAttribute("statustype","success");
			redirectAttributes.addFlashAttribute("statusmessage","User data updated Successfully");
			page="redirect:/Userdata/edit/"+data.getUid();
		}else
		{
//			model.addAttribute("statustype","errror");
//			model.addAttribute("statusmessage","User data updating UnSuccessfully");
			
			redirectAttributes.addFlashAttribute("statustype","success");
			redirectAttributes.addFlashAttribute("statusmessage","User data updating UnSuccessfull");
			page="redirect:/Userdata/edit/"+entity.getUid();
		}
		
		return page;
	}
	
	@GetMapping("delete/{uid}")
	public String delete(@PathVariable int uid,Model model )
	{
		use.deleteuser(uid);
		
			model.addAttribute("statustype","success");
			model.addAttribute("statusmessage","User data deleted Successfully");
		
		
		return "allUsers";
	}
	
	
	
	
	
	
	
	
	

}
