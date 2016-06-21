package com.rosteach.util;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateUtil {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public <T> Serializable create(final T entity) {
		return sessionFactory.getCurrentSession().save(entity); 
	}

	public <T> T update(final T entity) {
		sessionFactory.getCurrentSession().update(entity); 
		return entity;
	}

	public <T> void delete(final T entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	public <T> void delete(Serializable id, Class<T> entityClass) {
		T entity = fetchById(id, entityClass);
		delete(entity);
	}

	@SuppressWarnings("unchecked") 
	public <T> List<T> fetchAllExcel(Class<T> entityClass,String type) {
		if(type.equals("allreports")){
			return sessionFactory.getCurrentSession().createQuery(" FROM "+entityClass.getName()).list();
		}
		else{
			return sessionFactory.getCurrentSession().createQuery(" FROM "+entityClass.getName()+" WHERE type='"+type+"'").list(); 
		}
	}
	
	@SuppressWarnings("unchecked") 
	public <T> List<T> fetchAllExcel(Class<T> entityClass) {
		return sessionFactory.getCurrentSession().createQuery("SELECT id,name,type,database FROM "+entityClass.getName()).list(); 
	}

	
	@SuppressWarnings("rawtypes")
	public <T> List fetchAll(String query) { 
		return sessionFactory.getCurrentSession().createSQLQuery(query).list(); 
	}
	@SuppressWarnings("unchecked")
	public <T> T fetchById(Serializable id, Class<T> entityClass) {
		return (T)sessionFactory.getCurrentSession().get(entityClass, id);
	}
}
