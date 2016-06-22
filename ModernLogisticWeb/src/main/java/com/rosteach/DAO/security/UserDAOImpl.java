/*package com.rosteach.DAO.security;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("SQL"); 
    EntityManager em =  emf.createEntityManager();

	@Override
	public User getUserByName(String name) {
		Query query = em.createQuery("SELECT u FROM Users u where u.name = '"+name+"'", User.class);
	    return (User)query.getSingleResult();
	}

	@Override
	public void createUser(User user) {
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
*/