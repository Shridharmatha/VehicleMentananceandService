package in.shridhar.service;


import java.util.List;
import java.util.Optional;

import in.shridhar.entity.Vehicle;

public interface VehicleService {
	Vehicle checkvehicle(String vehiclenumber);
	
	int vehicleregister(Vehicle entity);
	
	List<Vehicle> getAllVehicles();
	
	Optional<Vehicle> getVehicledata(int vid);
	
	 Vehicle updatevehicle(Vehicle entity);
	
	 Vehicle getuserdetailes(String ownername);

}
