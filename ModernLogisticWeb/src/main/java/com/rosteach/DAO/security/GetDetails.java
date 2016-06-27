package com.rosteach.DAO.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class GetDetails{
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	CurrentUser cu = (CurrentUser)authentication.getPrincipal();
	
	public String getDB(){
		return cu.getDB();
	}
	
	public String getRole(){
		return cu.getRole();
	}
	
	public long getID(){
		return cu.getID();
	}
	
	public String getName(){
		return cu.getName();
	}
	
	public String getPass(){
		return cu.getPass();
	}
	
}
