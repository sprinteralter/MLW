package com.rosteach.DAO.databinding;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.rosteach.entities.ClientRequest;

@Repository
public class BindingDataDAOImpl implements BindingDataDAO{

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	
	@Override
	public List<ClientRequest> getClientsRequests(String database, String username, String password,
			String inputIds) {
		/**
		 * Set properties for our persistence unit and into entityManagerFactory
		 * */
		Map<String,String> props = new HashMap<String,String>();
		props.put("javax.persistence.jdbc.url", "jdbc:firebirdsql:192.168.20.17/3050:"+database);
		props.put("javax.persistence.jdbc.user", username);
		props.put("javax.persistence.jdbc.password", password);
		
		entityManagerFactory = Persistence.createEntityManagerFactory("database",props);
		entityManager = entityManagerFactory.createEntityManager();
		
		/**
		 * Begin transaction
		 * */
		
		entityManager.getTransaction().begin();
		
		Query query = entityManager.createNativeQuery("select * from SPRORDERSOUTINV (1,'16.05.2016',Null,0,Null,0) where id="+324990,ClientRequest.class);
		
		@SuppressWarnings("unchecked")
		List<ClientRequest> result = (List<ClientRequest>)query.getResultList();
		
		System.out.println("-------------------------------------------------------"+result.get(0).getId());
		
		/**
		 * Commit transaction
		 * */
		entityManager.getTransaction().commit();
		entityManager.close();
		return result;
	}
	
	@Override
	public HashMap<Integer,Object> getClientsRequestsDetails(List<ClientRequest> clientRequest) {
		HashMap<Integer,Object> map = new HashMap<Integer,Object>();
		for(ClientRequest each: clientRequest){
			Query query = entityManager.createNativeQuery("");
			@SuppressWarnings("unchecked")
			List<Object> queryResult = (List<Object>) query.getResultList();
			for(Object obj: queryResult){
				map.put(each.getId(), obj);
			}
		}
		return map;
	}

	@Override
	public List<Integer> setClientsRequests(List<ClientRequest> clientsRequests,String database, String username, String password,
			String inputIds) {
		/**
		 * Set properties for our persistence unit and into entityManagerFactory
		 * */
		Map<String,String> props = new HashMap<String,String>();
		props.put("javax.persistence.jdbc.url", "jdbc:firebirdsql:192.168.20.13/3050:"+database);
		props.put("javax.persistence.jdbc.user", username);
		props.put("javax.persistence.jdbc.password", password);
		
		entityManagerFactory = Persistence.createEntityManagerFactory("database",props);
		entityManager = entityManagerFactory.createEntityManager();
		/**
		 * Begin transaction
		 * */
		entityManager.getTransaction().begin();
		
		Query query = entityManager.createNativeQuery("EXECUTE PROCEDURE EPRORDERSOUTINV_INSERT('"+
															clientsRequests.get(0).getDocdate()+"',"+
															clientsRequests.get(0).getClientid()+","+
															0+",'"+
															null+"','"+
															clientsRequests.get(0).getComment()+"',"+
															null+","+
															null+","+
															null+","+
															null+","+
															clientsRequests.get(0).getComment1()+",'"+
															clientsRequests.get(0).getComment2()+"',"+
															null+","+
															null+","+
															null+","+
															null+","+
															null+","+
															clientsRequests.get(0).getOk_passed()+");"
															);
		
		@SuppressWarnings("unchecked")
		List<Integer> result = (List<Integer>)query.getResultList();
		
		System.out.println("-------------------------------------------------------"+result.get(0));
		
		/**
		 * Commit transaction
		 * */
		entityManager.getTransaction().commit();
		return result;
	}

	@Override
	public boolean setClientsRequestsDetails(List<Object> details) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
