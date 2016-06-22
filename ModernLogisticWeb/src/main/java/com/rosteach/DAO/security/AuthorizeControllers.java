/*package com.rosteach.DAO.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthorizeControllers {

	 @Autowired
	  //  private UserDAO userDao;

	    @RequestMapping(value = "/reg")
	    public String addPage(Model model) {
	        return "reg";
	    }

	    @RequestMapping(value = "/useradd", method = RequestMethod.POST)             //register add user
	    public ModelAndView addAdv(@RequestParam(value = "name") String name,
	                               @RequestParam(value = "password") String password) {
	       User user = new User(name, password);
	       userDao.createUser(user);
	        return new ModelAndView("home");

	    }
	
}
*/