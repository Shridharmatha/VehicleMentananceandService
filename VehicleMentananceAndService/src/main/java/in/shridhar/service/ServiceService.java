package in.shridhar.service;


import java.util.List;
import java.util.Optional;

import in.shridhar.entity.Services;

public interface ServiceService {
	
	//String serviceexist(String service);
	
	Integer save(Services entity);
	
	List<Services> getallServices();
	
	Optional<Services> getoneservice(int id);
	
	Services supdate(Services entity);

}
