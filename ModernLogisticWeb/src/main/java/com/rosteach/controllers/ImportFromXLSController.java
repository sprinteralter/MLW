/**
 * @author Pavlenko R.O.
 */
package com.rosteach.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ImportFromXLSController {
	private static final Logger logger = LoggerFactory.getLogger(ImportFromXLSController.class);
	@RequestMapping(value="/importFromXLS")
	public String importFromXLS(){
		logger.info("In ImportFromXLSController!");
		return "importFromXLS";
	}
}
