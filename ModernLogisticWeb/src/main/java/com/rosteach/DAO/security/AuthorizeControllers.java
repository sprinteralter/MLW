package com.rosteach.DAO.security;


import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletResponse;

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
	       
	       
	       try{
	       Map<String,String> properties = new HashMap<String,String>();
			properties.put("javax.persistence.jdbc.url", db);
			properties.put("javax.persistence.jdbc.user", name);
			properties.put("javax.persistence.jdbc.password", password);
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("database", properties); 
			EntityManager em =  emf.createEntityManager();
			//em.close();
	       
	       userDao.createUser(user);
	       ModelAndView model = new ModelAndView("login");
	       model.addObject("error", "Теперь вы можете войти, "+name+"!");
	       return model;
	       }catch(PersistenceException e){
	    	   
	    	   ModelAndView modelerr = new ModelAndView("reg");
	    	   modelerr.addObject("error", "Проверьте правильность логина и пароля");
		       return modelerr;
	       }
	       
			}
			
			
			ModelAndView err = new ModelAndView("reg");
			err.addObject("error", "Пользователь "+ name + " уже существует!");
			return err;
	    }
	    
	    
	    
	    @RequestMapping(value = "/login", method = RequestMethod.GET)
		public ModelAndView login(@RequestParam(value = "error", required = false) String error) {
			ModelAndView model = new ModelAndView();
			if (error != null){
				model.addObject("error", "Логин или пароль не верны!  <div><a href=\"./reg\">Вы здесь впервые?</a></div>");
			}
			return model;
		}
	    
	  @RequestMapping(value = "/error", method = RequestMethod.GET)
		public String error(HttpServletResponse resp) {
			
			return "error";
		}
	
}
