package com.rosteach.DAO.databinding;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.rosteach.entities.ClientRequest;
import com.rosteach.entities.ClientRequestDetails;

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
	public HashMap<ClientRequest,List<ClientRequestDetails>> getClientsRequestsDetails(
			String database, 
			String username, 
			String password,
			String inputIds) {
		
		/**
		 * Set properties for our persistence unit and into entityManagerFactory and result map
		 * */
		HashMap<ClientRequest,List<ClientRequestDetails>> resultMap= new HashMap<ClientRequest,List<ClientRequestDetails>>();
		
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
		
		Query query = entityManager.createNativeQuery("select * from SPRORDERSOUTINV (1,'16.05.2016','16.05.2016',0,Null,0) where id in ("+inputIds+")",ClientRequest.class);
		
		@SuppressWarnings("unchecked")
		List<ClientRequest> requestResult = (List<ClientRequest>)query.getResultList();
		/**
		 * get our details for each requestResult
		 * */
		for(ClientRequest request: requestResult){
			
			Query queryForDetails = entityManager.createNativeQuery("select * from SPRORDERSOUTINVDET ("+request.getId()+",1,0,Null,0, Null,Null,0,0);",ClientRequestDetails.class);
			
			@SuppressWarnings("unchecked")
			List<ClientRequestDetails> requestDetailsResult = (List<ClientRequestDetails>)queryForDetails.getResultList();
			
			resultMap.put(request, requestDetailsResult);
		}
		/**
		 * Commit transaction
		 * */
		entityManager.getTransaction().commit();
		entityManager.close();
		return resultMap;
	}

	@Override
	public List<Integer> setClientsRequestsWithDetails(
			HashMap<ClientRequest,List<ClientRequestDetails>> clientsRequests,
			String database,
			String username,
			String password){
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
		
		Set<ClientRequest> keys = clientsRequests.keySet();
		for(ClientRequest key: keys){
			Query query = entityManager.createNativeQuery("EXECUTE PROCEDURE EPRORDERSOUTINV_INSERT('"+
																key.getDocdate()+"',"+
																key.getClientid()+","+
																0+",'"+
																null+"','"+
																key.getComment()+"',"+
																null+","+
																null+","+
																null+","+
																null+","+
																key.getComment1()+",'"+
																key.getComment2()+"',"+
																null+","+
																null+","+
																null+","+
																null+","+
																null+","+
																key.getOk_passed()+");"
																);
			
			@SuppressWarnings("unchecked")
			List<Integer> result = (List<Integer>)query.getResultList();
			System.out.println("-------------------------------------------------------"+result.get(0));
		}
		/**
		 * Commit transaction
		 * */
		entityManager.getTransaction().commit();
		return null;
	}
}
