package com.rosteach.DAO.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

	private final long id;
	private String name;
	private String pass;
	private final String db;
	private final String role; 
	
	
	public CurrentUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities, long id, String db, String role) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.id = id;
		this.db = db;
		this.role = role;
		name = username;
		pass = password;
		// TODO Auto-generated constructor stub
	}
	
	
	public String getDB(){
		return db;
	}
	
	public String getRole(){
		return role;
	}
	
	public String getName(){
		return name;
	}
	
	public String getPass(){
		return pass;
	}
	
	public long getID(){	
		return id;
	}	
}


