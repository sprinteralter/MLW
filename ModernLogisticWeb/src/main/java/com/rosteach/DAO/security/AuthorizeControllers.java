package com.rosteach.DAO.security;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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
	                               @RequestParam(value = "dataBase") String database,
	                               HttpServletRequest request
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
		public ModelAndView login(@RequestParam(value = "error", required = false) String error, HttpServletRequest request) throws IOException {
			ModelAndView model = new ModelAndView();
			final boolean match;
			
			//IpAddressMatcher matcher = new IpAddressMatcher("192.168.20.13"); //192.168.0.0/16
			 if (!(request.getRemoteAddr().equals("192.168.20.1")))//matcher.matches(request) == true)
		        	match = true;
		        else match= false;

		        if (match == true){
		        	if (error != null){
						model.addObject("error", "Логин или пароль не верны!  <div><a href=\"./reg\">Вы здесь впервые?</a></div>");
					}
					return model;
		        } else model.setViewName("denied");		       

			return model;
		}
	    
	    @RequestMapping(value = "/keyauth", method = RequestMethod.POST)
		public ModelAndView keyauth(@RequestParam(value = "error", required = false) String error, HttpServletRequest request, @RequestParam(value = "file") MultipartFile file) throws IOException {
			ModelAndView model = new ModelAndView("home");
			
			MultipartFile mf =  file;
			File f = new File("key");
			mf.transferTo(f);
			FileReader reader = new FileReader(f);
			char[] cbuf = new char[(int)f.length()];
			reader.read(cbuf);
			String s = new String(cbuf);
			String encoded = new String(Base64.getDecoder().decode(s));
			
			
			
			UserDetails userDetails = new UserDetailsServiceIMPL().loadUserByUsername(encoded);
			Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
			HttpSession session = request.getSession(true);
		    session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
			return model;
		}
	    
	    
	    
	  @RequestMapping(value = "/error", method = RequestMethod.GET)
		public String error(HttpServletResponse resp) {
			
			return "error";
		}
	  
	  
	  @RequestMapping(value = "/admin", method = RequestMethod.GET)
		public String admin(HttpServletResponse resp) {
			return "admin";
		}
	  
	
}
