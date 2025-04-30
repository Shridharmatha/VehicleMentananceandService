package in.shridhar.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.shridhar.entity.Vehicle;
import in.shridhar.repository.InterfaceVehicle;
import in.shridhar.service.VehicleService;

@Service
public class VehicleServiceimpl implements VehicleService{
	
	@Autowired
	private InterfaceVehicle vehrepo;

	@Override
	public Vehicle checkvehicle(String vehiclenumber) {
		
		return vehrepo.findByVehicleNumber(vehiclenumber);
	}

	@Override
	public int vehicleregister(Vehicle entity) {
	
		return vehrepo.save(entity).getVid();
	}

	@Override
	public List<Vehicle> getAllVehicles() {
		List<Vehicle> list=vehrepo.findAll();
		return list;
	}

	@Override
	public Optional<Vehicle> getVehicledata(int vid) {
		
		return vehrepo.findById(vid);
	}

	@Override
	public Vehicle updatevehicle(Vehicle entity) {
		
		
		return vehrepo.save(entity);
	}

	@Override
	public Vehicle getuserdetailes(String ownername) {
		
		return vehrepo.getdetails(ownername);
	}

}
