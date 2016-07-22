package com.rosteach.DAO.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminPageController {

	@Autowired
	UserDAO userDao;
	
	 @RequestMapping(value = "/generateKey", method = RequestMethod.POST)             //register add user
	    public String outKey(@RequestParam(value = "name") String name,
	                               @RequestParam(value = "domain") String database,
	                               HttpServletResponse response
	                              
	                               ) {

		
			
			/*	
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
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("SQL"); 
				EntityManager em =  emf.createEntityManager();
				Query query = em.createNativeQuery("SELECT * FROM users_auth where name = '"+name+"' and db = '"+db+"'", User.class);	*/
		 
				User user = userDao.getUserByName(name, database);
				String splitter = user.getName()+":"+user.getPassword()+":"+database;
				String encoded = Base64.getEncoder().encodeToString(splitter.getBytes());
				
				
				File file = new File("key.auth");
				try {
				FileWriter writer = new FileWriter(file);
				System.out.println(encoded);
					writer.write(encoded);
					writer.flush();
					writer.close();
					
					FileInputStream fileIn = new FileInputStream(file);
					response.setHeader("Content-Disposition",
		                     "attachment;filename=" + file);
					response.setContentType("text/plain");
				  
					OutputStream os = response.getOutputStream();
					IOUtils.copy(fileIn, os);
					
					 os.flush();
					 os.close();
					fileIn.close();
					response.flushBuffer();
					
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	           
				//
		return "admin";
	 }

}
	

