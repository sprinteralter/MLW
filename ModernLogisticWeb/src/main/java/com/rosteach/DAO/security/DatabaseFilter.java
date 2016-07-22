package com.rosteach.DAO.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


public class DatabaseFilter extends UsernamePasswordAuthenticationFilter {
	/* @Override
	    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
	        final String dbValue = request.getParameter("dataBase");
	        request.getSession().setAttribute("dataBase", dbValue);
	        	
	        System.out.println(dbValue + " DATABASE ------------------**************************************************************************");
	        
	        return super.attemptAuthentication(request, response); 
	    } */
	 
	private String extraParameter = "extra";
	private String delimiter = ":";


	@Override
	protected String obtainUsername(HttpServletRequest request)
	{
		String username = request.getParameter("j_username");
		String extraInput = request.getParameter(getExtraParameter());
		String combinedUsername = username + getDelimiter() + extraInput;
		
		System.out.println("Combined username = " + combinedUsername + " " +request.getRemoteAddr());
		
		return combinedUsername;
	}


	public String getExtraParameter()
	{
		return this.extraParameter;
	}


	public void setExtraParameter(String extraParameter)
	{
		this.extraParameter = extraParameter;
	}

	public String getDelimiter()
	{
		return this.delimiter;
	}

	public void setDelimiter(String delimiter)
	{
		this.delimiter = delimiter;
	}

}
