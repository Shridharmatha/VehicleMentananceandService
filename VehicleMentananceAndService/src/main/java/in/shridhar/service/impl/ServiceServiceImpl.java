package in.shridhar.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.shridhar.entity.Services;
import in.shridhar.repository.InterfaceService;

import in.shridhar.service.ServiceService;

@Service
public class ServiceServiceImpl implements ServiceService{
	
	@Autowired
	private InterfaceService srvcrepo;

//	@Override
//	public String serviceexist(String service) {
//		
//		return srvcrepo.findByService(service);
//	}

	

	@Override
	public Integer save(Services entity) {
	
		return srvcrepo.save(entity).getId();
	}

	@Override
	public List<Services> getallServices() {
		List<Services> srvc=srvcrepo.findAll();
		return srvc;
	}

	@Override
	public Optional<Services> getoneservice(int id) {
		Optional<Services> list=srvcrepo.findById(id);
		return list;
	}

	@Override
	public Services supdate(Services entity) {
	
		return srvcrepo.save(entity);
	}

	
	

}
