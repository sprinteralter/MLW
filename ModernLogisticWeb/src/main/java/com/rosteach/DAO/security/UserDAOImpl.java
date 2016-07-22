package com.rosteach.DAO.security;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Repository
public class UserDAOImpl implements UserDAO {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("SQL"); 
   

	@Override
	public User getUserByName(String name, String database) {
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
		
		 EntityManager em =  emf.createEntityManager();
		Query query = em.createNativeQuery("SELECT * FROM users_auth where name = '"+name+"' and db = '"+db+"'", User.class);
		try{
	
	        
	    return (User)query.getResultList().get(0);
		} catch (IndexOutOfBoundsException e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void createUser(User user) {
		
	EntityManager em =  emf.createEntityManager();

	try {
	            em.getTransaction().begin();
	            em.persist(user);
	            em.getTransaction().commit();
	        } catch (Exception ex) {
	            em.getTransaction().rollback();
	            ex.printStackTrace();
	        }

	}

}
