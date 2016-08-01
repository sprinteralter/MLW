package com.rosteach.controllers;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rosteach.DAO.security.GetDetails;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ViewController {
	
	private static final Logger logger = LoggerFactory.getLogger(ViewController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Date date) {
		date = new Date();
		date.getTime();
		logger.info("Home page downloaded with success!  Server date & time is: {}.",date);	
		return "home";
	}
	@RequestMapping(value = "testhome", method = RequestMethod.GET)
	public String testhome(Date date) {
		date = new Date();
		date.getTime();
		logger.info("Home page downloaded with success!  Server date & time is: {}.",date);	
		return "testhome";
	}
	
	@RequestMapping(value = "/dataBinding", method = RequestMethod.GET)
	public ModelAndView dataBinding() {
		ModelAndView mav = new ModelAndView();
		
		GetDetails currentUser = new GetDetails();
		String database = currentUser.getDB();
		String username = currentUser.getName();
		
		int start = database.lastIndexOf(':')+1;
		int end = database.length();
		
		Date date = new Date();
		logger.info("DataBinding downloaded with success!  Server date & time is: {}.",date);	
		
		mav.addObject("username", "Имя: "+username);
		mav.addObject("database", "База: "+database.substring(start,end));
		return mav;
	}
	
	/**
	 * returning xmlcreation home page (xmlfromdata)
	 */
	@RequestMapping(value = "/XMLcreation", method = RequestMethod.GET)
	public ModelAndView xmlfromdata() {
		ModelAndView mav = new ModelAndView();
		
		GetDetails currentUser = new GetDetails();
		String database = currentUser.getDB();
		String username = currentUser.getName();
		
		int start = database.lastIndexOf(':')+1;
		int end = database.length();
		
		Date date = new Date();
		logger.info("XMLcreation downloaded with success!  Server date & time is: {}.",date);	
		
		mav.addObject("username", "Имя: "+username);
		mav.addObject("database", "База: "+database.substring(start,end));
		return mav;
	}

	@RequestMapping(value = "/XML", method = RequestMethod.GET)
	public ModelAndView xml(){
		ModelAndView mav = new ModelAndView();
		
		GetDetails currentUser = new GetDetails();
		String database = currentUser.getDB();
		String username = currentUser.getName();
		
		int start = database.lastIndexOf(':')+1;
		int end = database.length();
		
		Date date = new Date();
		logger.info("XML downloaded with success!  Server date & time is: {}.",date.getTime());
		
		mav.addObject("username", "Имя: "+username);
		mav.addObject("database", "База: "+database.substring(start,end));
		return mav;
	}
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ModelAndView test(){
		ModelAndView mav = new ModelAndView();
		
		GetDetails currentUser = new GetDetails();
		String database = currentUser.getDB();
		String username = currentUser.getName();
		
		int start = database.lastIndexOf(':')+1;
		int end = database.length();
		
		Date date = new Date();
		logger.info("test page downloaded with success!  Server date & time is: {}.",date.getTime());
		
		mav.addObject("username", "Имя: "+username);
		mav.addObject("database", "База: "+database.substring(start,end));
		return mav;
	}
}
