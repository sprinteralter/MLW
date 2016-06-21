package com.rosteach.controllers;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	@RequestMapping(value = "/dataBinding", method = RequestMethod.GET)
	public String home() {
		Date date = new Date();
		date.getTime();
		logger.info("Home page downloaded with success!  Server date & time is: {}.",date);	
		return "DataBinding";
	}
	
}
