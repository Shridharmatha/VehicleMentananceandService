package in.shridhar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import in.shridhar.entity.User;

public interface InterfaceUser extends JpaRepository<User, Integer>{
	
	
	//@Query("SELECT u.umail FROM User u WHERE u.umail = :mail")
	boolean existsByUmail(String mail);
	
	boolean existsByUpass(String upass);
	
	//User findByUmail(String email);
	
	User findByUmail(String umail);

}
