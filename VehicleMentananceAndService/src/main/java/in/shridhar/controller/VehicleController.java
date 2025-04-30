package in.shridhar.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import in.shridhar.entity.Vehicle;
import in.shridhar.service.VehicleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
@RequestMapping("/vehicle")

public class VehicleController {
	
	@Autowired
	private VehicleService vsrvc;
	
	@GetMapping("reg")
	public String vreg()
	{
		return "vehicleregister";
	}
	
	
	
	@PostMapping("vehiclereg")
	public String vehiclereg(@ModelAttribute Vehicle entity,Model model) {
		Vehicle vcl=vsrvc.checkvehicle(entity.getVehicleNumber());
		String page="";
		if(vcl ==null)
		{
			int vid=vsrvc.vehicleregister(entity);
			String vnum=entity.getVehicleNumber();
			
			if(vid >0)
			{
				model.addAttribute("statustype","success");
				model.addAttribute("statusmessage",vnum+" Registered Successfully by id"+vid);
				page="redirect:/vehicle/reg";
			}
			else
			{
				model.addAttribute("statustype","error");
				model.addAttribute("statusmessage"," Vehicle Registeration UnSuccessfull");
				page="redirect:/vehicle/reg";
			}
		}
		
		return page;
	}
	
	@GetMapping("all")
	public String getall(Model model)
	{
		List<Vehicle> list=vsrvc.getAllVehicles();
		model.addAttribute("list",list);
		model.addAttribute("statustype","success");
		model.addAttribute("statusmessage"," All data fetching Successfully");
		return "allvehicles";
	}
	
//	@GetMapping("/updatev")
//	public String updatev()
//	{
//		return "updatevehicle";
//	}
	
	@GetMapping("/edit/{vid}")
	public String edit(@PathVariable int vid,Model model) {
	   Optional<Vehicle> list=vsrvc.getVehicledata(vid);
	   System.out.println(list);
	   model.addAttribute("list",list.get());
		model.addAttribute("statustype","success");
		model.addAttribute("statusmessage"," All data fetching Successfully");
		return "updatevehicle";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute Vehicle entity,Model model)
	{
		String page="";
		Vehicle vdata=vsrvc.updatevehicle(entity);
		
		if(vdata!=null)
		{
			model.addAttribute("statustype","success");
			model.addAttribute("statusmessage"," Updating Successfully");
			page="redirect:/vehicle/edit/"+vdata.getVid();
		}else
		{
			model.addAttribute("statustype","error");
			model.addAttribute("statusmessage"," update unSuccessfull");
			page="redirect:/vehicle/edit/"+entity.getVid(); ;
		}
		return page;
	}
	
	
	
	

}
