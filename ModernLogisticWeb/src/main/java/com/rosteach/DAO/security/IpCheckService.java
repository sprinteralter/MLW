package com.rosteach.DAO.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.util.matcher.IpAddressMatcher;

public class IpCheckService {
	public String isValid(HttpServletRequest request) throws IOException {
        //This  service is a bean so you can inject other dependencies,
            //for example load the white list of IPs from the database 
        IpAddressMatcher matcher = new IpAddressMatcher("192.168.20.13"); //192.168.0.0/16
        
        if (matcher.matches(request) == true)
        	return "true";
        else return "false";

    }
}
