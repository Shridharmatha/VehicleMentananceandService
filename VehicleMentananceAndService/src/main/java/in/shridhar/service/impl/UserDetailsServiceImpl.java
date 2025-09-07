package in.shridhar.service.impl;

import in.shridhar.entity.User;
import in.shridhar.repository.InterfaceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private InterfaceUser userRepo;

    @Override
    public UserDetails loadUserByUsername(String umail) throws UsernameNotFoundException {
        User user = userRepo.findByUmail(umail);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + umail);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUmail(),
                user.getUpass(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getUroll().toUpperCase()))
              
        );

    }
}
