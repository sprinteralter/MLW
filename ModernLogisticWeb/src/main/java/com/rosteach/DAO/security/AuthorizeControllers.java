package com.rosteach.DAO.security;

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
	    private UserDAO userDao;

	    @RequestMapping(value = "/reg")
	    public String addPage(Model model) {
	        return "reg";
	    }

	    @RequestMapping(value = "/useradd", method = RequestMethod.POST)             //register add user
	    public ModelAndView addAdv(@RequestParam(value = "name") String name,
	                               @RequestParam(value = "password") String password,
	                               @RequestParam(value = "dataBase") String database
	                               ) {
	    	
	    	
			
			User check = userDao.getUserByName(name, database);
			
			if(check == null){
				
				String db = "";
		    	
		    	if(database.equals("alter_ros")){
					db="jdbc:firebirdsql:192.168.20.85/3050:alter_ros";
				} 
				if(database.equals("Alter")){
					db="jdbc:firebirdsql:192.168.20.17/3050:alter";
				} 
				if(database.equals("alter_curent")){
					db="jdbc:firebirdsql:192.168.20.13/3050:alter_curent";
				}	
				
				if(database.equals("sprinter_curent")){
					db="jdbc:firebirdsql:192.168.20.13/3050:sprinter_curent";
				}	
				
				if(database.equals("Sprinter")){
					db="jdbc:firebirdsql:192.168.20.16/3050:sprinter";
				}
				
	       User user = new User(name, password, db, "USER");
	       userDao.createUser(user);
	       return new ModelAndView("login");
			}
	       //сделать проверку если такой юзер существует
	       
			return new ModelAndView("reg");
	    }
	    
	    @RequestMapping(value = "/login", method = RequestMethod.GET)
		public String login() {
			
			return "login";
		}
	
}
