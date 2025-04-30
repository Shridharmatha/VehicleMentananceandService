package in.shridhar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.shridhar.entity.Vehicle;
import java.util.List;
import java.util.Optional;


public interface InterfaceVehicle extends JpaRepository<Vehicle, Integer>{
	
	//boolean findByVehicleNumber(String vehiclenumber);
	
	Vehicle findByVehicleNumber(String vehicleNumber);
	
	@Query(value="select * from Vehicle where ownername= :ownername",nativeQuery = true)
	Vehicle getdetails(String ownername);

}
