package com.rosteach.controllers.ferrero;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class FerreroController {
	
	
	
	@RequestMapping(value = "/Ferrero")
	public String page() {
		return "ferrero";
	}
}
