package com.rosteach.DAO.security;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service("userDetailsService")
public class UserDetailsServiceIMPL implements UserDetailsService 
 {

	@Autowired
	private UserDAO userDAO;
	
	
	@Override
	public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException, DataAccessException {
		
	String[] split = input.split(":");
		
		String username = split[0];
		String domain = split[1];
		User user = null;
		try {
		user = userDAO.getUserByName(username, domain); //getUserByNameAndPassword(username, domain); //
		} catch (NullPointerException e){
			user = getUserByNameAndPassword(username, split[1] , split[2]);
		}

		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
		
		
		/*if (user.getName().equals("sysdba")){
            roles.add(new SimpleGrantedAuthority(UserRole.ADMIN.name()));
        }*/
		
	   roles.add(new SimpleGrantedAuthority(UserRole.valueOf("ROLE_"+user.getRole()).name() ));
       
	   /*UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getName(),
                user.getPassword(),
                roles);*/
        
        CurrentUser cu = new CurrentUser(user.getName(), user.getPassword(), true, true, true, true, roles, user.getId(), user.getDatabase(), user.getRole());
	
		return cu;
	}
	
	
	public User getUserByNameAndPassword(String name, String  password, String database) {
		String db = "";
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SQL"); 

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
		System.out.println(name + " " + password + " " + " " +database + " " + db + " KEYFILE DATA");
		 EntityManager em =  emf.createEntityManager();
		Query query = em.createNativeQuery("SELECT * FROM users_auth where name = '"+name+"' and password = '"+password+"' and db = '"+db+"'", User.class);
		try{
	
	        
	    return (User)query.getResultList().get(0);
		} catch (IndexOutOfBoundsException e){
			e.printStackTrace();
		}
		return null;
	}
}
