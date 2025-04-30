package in.shridhar.service;

import java.util.List;
import java.util.Optional;

import in.shridhar.entity.User;
import jakarta.servlet.http.HttpSession;

public interface UserService {
	
	Integer saveuser(User entity);
	
	List<User> getAllUser();
	
	User updateuser(User entity);
	
	void deleteuser(int uid);
	
	boolean checkUser(String email);
	
	String loginUser(String email, String password, HttpSession session);
	
	List<User> getalluser();
	
	Optional<User> getuser(int uid);

	boolean checkUserpass(String upass);


}
