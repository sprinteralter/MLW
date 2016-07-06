package com.rosteach.services.ferrero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rosteach.DAO.security.GetDetails;
import com.rosteach.entities.ClientsRequests;
import com.rosteach.entities.ferrero.FerreroRequest;

@Repository
public class FerreroRequestServiceIMPL implements FerreroRequestService {

	@Override
	public List<FerreroRequest> getAllRequests() {
		
		String startdate = "01.06.2016";
		String enddate = "02.06.2016";
		List<FerreroRequest> orders = getOrders(startdate, enddate);
		
		List<FerreroRequest> oneDay = new ArrayList<FerreroRequest>(); 
		
		int idOrd = 0;
		int clientID = 0;
		String agentName = "blablabla";
		FerreroRequest oneReq = new FerreroRequest();
		java.sql.Date orderDate = new java.sql.Date(12, 12, 1988);
		for (int i = 0; i < orders.size(); i++){
			idOrd = orders.get(i).getIdorder();
			clientID = orders.get(i).getCLIENTID();
			agentName = orders.get(i).getAGENTSNAME();
			
			if( oneReq.getIdorder() == idOrd && oneReq.getCLIENTID() == clientID && oneReq.getDOCDATEORDER() == orderDate && oneReq.getAGENTSNAME().equals(agentName) ){	
				oneReq.setAGENTSNAME("-------------------");
				oneReq.setCLIENTID(0);	
			} else{
				oneReq = orders.get(i);
			}
			oneDay.add(oneReq);
		}

		return oneDay;
	}
	
	private List<FerreroRequest> getOrders(String startdate, String enddate ){
		GetDetails ud = new GetDetails();
		Map<String,String> properties = new HashMap<String,String>();
		properties.put("javax.persistence.jdbc.url", ud.getDB());
		properties.put("javax.persistence.jdbc.user", ud.getName());
		properties.put("javax.persistence.jdbc.password", ud.getPass());
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("database", properties); 
	    EntityManager em =  emf.createEntityManager();
	  String sqlString = "select IDORDER, DOCDATEORDER, AGENTID, CLIENTID , GOODSID,ITEMCOUNTORDER, IDOUT, LOADINGDATE,"
	  		+ " ITEMCOUNTOUT, RES, RESRATE, CLIENTSNAMEORD, ADRESSLOCORD, GOODSNAME, GOODSGROUP, GROUPNAME, AGENTSNAME,"
	  		+ " PRODTREEID, PRODTREENAME, PAYTYPENAME "
	    		+ "from SPR_ORDERSINMIGRDET_RES5('"+startdate+"','"+ enddate+"',"+5072+")";
	   
	    
	  
	    Query getAll = em.createNativeQuery(sqlString); 
	  
	  
	   
	   List<FerreroRequest> orders = new ArrayList<FerreroRequest>(); 
	    List<Object[]> order =  getAll.getResultList();
	    
	    FerreroRequest fr; 
	    for (Object result : order) {
	    	fr  = new FerreroRequest();	    	
	        Object[] r = (Object[]) result;	        
	        fr.setIdorder((Integer)r[0]);
	        fr.setDOCDATEORDER((java.sql.Date) r[1]);
	        fr.setAGENTID((Integer) r[2]);
	        fr.setCLIENTID((Integer)r[3] );
	        fr.setGOODSID((Integer) r[4]);
	        fr.setITEMCOUNTORDER((Integer) r[5]);
	        fr.setIDOUT((String) r[6]);
	        fr.setLOADINGDATE((java.sql.Date) r[7]);
	        fr.setITEMCOUNTOUT((Integer) r[8]);	      
	        fr.setRES((Integer) r[9]);	       
	        fr.setRESRATE((Integer) r[10]);	        
	        fr.setCLIENTSNAMEORD((String) r[11]);	       
	        fr.setADRESSLOCORD((String) r[12]);	      
	        fr.setGOODSNAME((String) r[13]);	       
	        fr.setGOODSGROUP((Integer) r[14]);	       
	        fr.setGROUPNAME((String) r[15]);
	        fr.setAGENTSNAME((String) r[16]);	        
	        fr.setPRODTREEID((Integer) r[17]);
	        fr.setPRODTREENAME((String) r[18]);
	       	fr.setPAYTYPENAME((String) r[19]);        
	        orders.add(fr);
	     
	    }
	    return orders;
	}

}
