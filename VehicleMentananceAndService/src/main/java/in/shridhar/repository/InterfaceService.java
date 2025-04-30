package in.shridhar.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import in.shridhar.entity.Services;

public interface InterfaceService extends JpaRepository<Services, Integer>{
	
//String findByService(String Service);
	

}
