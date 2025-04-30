package in.shridhar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.shridhar.entity.Center;
import in.shridhar.repository.InterfaceCenter;
import in.shridhar.service.CenterService;

@Service
public class CenterServiceimpl implements CenterService {
	@Autowired
	private InterfaceCenter centerrepo;

	@Override
	public Center checkmail(String cmail) {
		
		return centerrepo.findByCmail(cmail);
	}

	@Override
	public int centerregister(Center entity) {
		
		return centerrepo.save(entity).getCid();
	}
	
	

	@Override
	public List<Center> getAllCenters() {
		List<Center> list=centerrepo.findAll();
		return list;
	}

	@Override
	public String getcentername(int cid) {
		
		return centerrepo.findByCname(cid);
	}

	
	

}
