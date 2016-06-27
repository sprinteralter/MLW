package com.rosteach.DAO.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsServiceIMPL implements UserDetailsService 
 {

	@Autowired
	private UserDAO userDAO;
	
	
	@Override
	public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException, DataAccessException {
		
	String[] split = input.split(":");
		
		String username = split[0];
		String domain = split[1];
		
		User user = userDAO.getUserByName(username, domain);
		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
		
		
		/*if (user.getName().equals("sysdba")){
            roles.add(new SimpleGrantedAuthority(UserRole.ADMIN.name()));
        }*/
		
	   roles.add(new SimpleGrantedAuthority(UserRole.valueOf(user.getRole()).name() ));
       
	   /*UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getName(),
                user.getPassword(),
                roles);*/
        
        CurrentUser cu = new CurrentUser(user.getName(), user.getPassword(), true, true, true, true, roles, user.getId(), user.getDatabase(), user.getRole());
	
		return cu;
	}

}
