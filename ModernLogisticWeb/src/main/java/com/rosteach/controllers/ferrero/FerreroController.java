package com.rosteach.controllers.ferrero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rosteach.services.ferrero.FerreroRequestService;

@Controller
public class FerreroController {
	
	
	
	@RequestMapping(value = "/Ferrero")
	public String page() {
		return "ferrero";
	}
}
