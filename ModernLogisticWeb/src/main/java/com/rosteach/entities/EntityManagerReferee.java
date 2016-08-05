package com.rosteach.entities;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.rosteach.DAO.security.GetDetails;

public class EntityManagerReferee {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private final GetDetails currentUser = new GetDetails();
	public EntityManager getConnection(){
		Map<String,String> props = new HashMap<String,String>();
		props.put("javax.persistence.jdbc.url",currentUser.getDB()+"?lc_ctype=WIN1251;sql_dialect=3");
		props.put("javax.persistence.jdbc.user",currentUser.getName());
		props.put("javax.persistence.jdbc.password",currentUser.getPass());
		
		entityManagerFactory = Persistence.createEntityManagerFactory("database",props);
		entityManager = entityManagerFactory.createEntityManager();	
		return entityManager;
	}; 
	
	public void closeConnection(EntityManager em, EntityManagerFactory emf){
		em.getTransaction().commit();
		em.clear();
		em.close();
		emf.close();
	}
}
