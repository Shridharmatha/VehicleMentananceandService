package in.shridhar.service;

import java.util.List;

import in.shridhar.entity.Center;

public interface CenterService {
	
	Center checkmail(String cmail);
	
	int centerregister(Center entity);
	
	List<Center> getAllCenters();
	
	String getcentername(int cid);
	
	

	
}
