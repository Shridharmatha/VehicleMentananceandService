package in.shridhar.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.shridhar.entity.User;
import in.shridhar.repository.InterfaceUser;
import in.shridhar.service.UserService;
import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceimpl implements UserService{
	
	@Autowired
	private InterfaceUser Userrepo;

	@Override
	public Integer saveuser(User entity) {
	
		return Userrepo.save(entity).getUid();
	}

	@Override
	public List<User> getAllUser() {
	
		return Userrepo.findAll();
	}

	@Override
	public User updateuser(User entity) {
			return Userrepo.save(entity);
	}

	@Override
	public void deleteuser(int uid) {
		Userrepo.deleteById(uid);
		
		
	}

	@Override
	public boolean checkUser(String email) {
		
		return Userrepo.existsByUmail(email);
	}
	
	@Override
	public boolean checkUserpass(String upass) {
		
		return Userrepo.existsByUpass(upass);
	}

	@Override
	public String loginUser(String umail, String password, HttpSession session) {
		System.out.println(umail + "this is in imple");
	    String result = "";

	    User user = Userrepo.findByUmail(umail);

	    if (user.getUmail().equals(umail) && user.getUpass().equals(password)) {
	        System.out.println("Login successful");
	        session.setAttribute("umail", user.getUmail());
	        session.setAttribute("uname", user.getUname());
	        session.setAttribute("uphone", user.getUphone());

	        result = "success";
	    } else {
	        System.out.println("Login unsuccessful");
	        result = "fail";
	    }

	    return result;
	}

	@Override
	public List<User> getalluser() {
		List<User> list=Userrepo.findAll();
		
		return list;
	}

	@Override
	public Optional<User> getuser(int uid) {
		return Userrepo.findById(uid);
	
		
	}
	
	


}
