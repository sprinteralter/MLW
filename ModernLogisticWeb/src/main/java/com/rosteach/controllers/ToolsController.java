package com.rosteach.controllers;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ToolsController {
	private static final Logger logger = LoggerFactory.getLogger(ToolsController.class);
	@RequestMapping(value = "/Tools", method = RequestMethod.GET)
	public String tools(Date date) {
		date = new Date();
		date.getTime();
		logger.info("Tools page downloaded with success!  Server date & time is: {}.",date);
		return "Tools";
	}
	@RequestMapping(value = "/toolsInfo", method = RequestMethod.GET)
	public String getInfo() {
		return "toolsInfo";
	}
}
