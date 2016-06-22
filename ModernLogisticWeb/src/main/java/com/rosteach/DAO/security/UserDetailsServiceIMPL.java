package com.rosteach.DAO.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceIMPL implements UserDetailsService 
 {

	

	@Autowired
	private UserDAO userDAO;
	
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		
		
		User user = userDAO.getUserByName(name);
		
		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
		
		
		if (user.getName().equals("admin")){
            roles.add(new SimpleGrantedAuthority(UserRole.ADMIN.name()));
        }
		
	   roles.add(new SimpleGrantedAuthority(UserRole.USER.name()));
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getName(),
                user.getPassword(),
                roles);
	
		return userDetails;
	}

}
