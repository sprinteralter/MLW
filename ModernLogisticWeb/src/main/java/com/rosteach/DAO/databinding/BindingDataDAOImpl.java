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

	@Override
	public HashMap<ClientRequest,List<ClientRequestDetails>> getClientsRequestsDetails(
			String database, 
			String username, 
			String password,
			String inputIds,
			String startDate,
			String endDate) {
		
		/**
		 * Set properties for our persistence unit and into entityManagerFactory and result map
		 * */
		HashMap<ClientRequest,List<ClientRequestDetails>> resultMap= new HashMap<ClientRequest,List<ClientRequestDetails>>();
		
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
		
		Query query = entityManager.createNativeQuery("select * from SPRORDERSOUTINV (1,'"+startDate+"','"+endDate+"',0,0,0) where id in ("+inputIds+")",ClientRequest.class);
		
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
	public String setClientsRequestsWithDetails(
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
		
		EntityManagerFactory entityManagerFactoryInsert = Persistence.createEntityManagerFactory("database",props);
		EntityManager entityManagerInsert = entityManagerFactoryInsert.createEntityManager();
		/**
		 * Begin transaction
		 * */
		entityManagerInsert.getTransaction().begin();
		
		
		Set<ClientRequest> keys = clientsRequests.keySet();
		
		for(ClientRequest key: keys){
			//define our details collection per key request
			List<ClientRequestDetails> details = clientsRequests.get(key);
			
			Query query = entityManagerInsert.createNativeQuery("EXECUTE PROCEDURE EPRORDERSOUTINV_INSERT('"+
																key.getDocdate()+"',"+
																3+","+ //needed real data
																0+","+
																null+","+
																key.getComment()+","+
																null+","+
																null+","+
																null+","+
																null+","+
																key.getComment1()+","+
																key.getComment2()+","+
																null+","+
																null+","+
																null+","+
																null+","+
																null+","+
																key.getOk_passed()+");"
																);
			
			Integer InsertedId = (Integer)query.getSingleResult();
			System.out.println("--------------------Returned iD"+InsertedId);
			/*List<ClientRequest> requests = new ArrayList<ClientRequest>();
			requests.addAll(keys);
			System.out.println(requests.get(0).getId());*/
			
			/*ClientRequestDetails detailsPerKey = clientsRequests.get(key).get(0);
			System.out.println(detailsPerKey.getGOODSNAME());
				System.out.println();
				System.out.println("----------------------------"+clientsRequests.size());
				System.out.println();*/
			for(ClientRequestDetails detail: details){
				System.out.println("---------------------------Successfuly started execution!");
				
				
				Query queryForGOODSID = entityManagerInsert.createNativeQuery("select id from goods where code='4823001400725'");
				Integer GOODSID= (Integer) queryForGOODSID.getSingleResult();
					
					System.out.println("------------------------------GoodsId"+GOODSID);	
					Query queryForDetails= entityManagerInsert.createNativeQuery("EXECUTE PROCEDURE EPRORDERSOUTINVDET_INSERT("+
																			InsertedId+","+
																			GOODSID+","+
																			detail.getGOODSMEASUREID()+","+
																			detail.getITEMCOUNT()+","+
																			detail.getQUOTACOUNT()+")");
					queryForDetails.executeUpdate();
					
				System.out.println("---------------------------Successfuly completed execution!");
				break;
			}
			
			//System.out.println("-------------------------------------------------------"+result.get(0));
		}
		/**
		 * Commit transaction
		 * */
		entityManagerInsert.getTransaction().commit();
		entityManagerInsert.close();
		return null;
	}
	
}
