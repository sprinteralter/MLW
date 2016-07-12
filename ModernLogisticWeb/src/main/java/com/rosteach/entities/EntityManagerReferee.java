package com.rosteach.entities;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerReferee {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	public EntityManager getConnection(String database,String username,String password){
		Map<String,String> props = new HashMap<String,String>();
		props.put("javax.persistence.jdbc.url",database+"?lc_ctype=WIN1251;sql_dialect=3");
		props.put("javax.persistence.jdbc.user",username);
		props.put("javax.persistence.jdbc.password",password);
		
		entityManagerFactory = Persistence.createEntityManagerFactory("database",props);
		entityManager = entityManagerFactory.createEntityManager();	
		return entityManager;
	}; 
}
