package com.rosteach.services.ferrero;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


import org.springframework.stereotype.Repository;

import com.rosteach.DAO.security.GetDetails;

import com.rosteach.entities.ferrero.FerreroRequest24;

@Repository
public class FerreroRequestServiceIMPL implements FerreroRequestService {

	@Override
	public List<FerreroRequest24> getAllRequests() {
		
		String startdate = "01.06.2016";
		String enddate = "30.06.2016";
		List<FerreroRequest24> orders = null;// = getOrders24(startdate, enddate);
		return orders; 
	}
	
	@Override
	public List<FerreroRequest24> getOrders24(java.sql.Date startdate, java.sql.Date enddate ){
		GetDetails ud = new GetDetails();
		Map<String,String> properties = new HashMap<String,String>();
		properties.put("javax.persistence.jdbc.url", ud.getDB()+"?lc_ctype=WIN1251;sql_dialect=3");
		properties.put("javax.persistence.jdbc.user", ud.getName());
		properties.put("javax.persistence.jdbc.password", ud.getPass());
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("database", properties); 
	    EntityManager em =  emf.createEntityManager();
	    Query oneDay = em.createNativeQuery("select"
	    		+ " AGENTSNAME, CLIENTSNAMEORD,  ADRESSLOCORD, PAYTYPENAME, IDORDER, DOCDATEORDER, LOADINGDATE"
	    		+ " from SPR_ORDERSINMIGRDET_RES5('"+startdate+"','"+enddate+"',5072) "
	    		+ "group by AGENTSNAME, CLIENTSNAMEORD,  ADRESSLOCORD, PAYTYPENAME, IDORDER, DOCDATEORDER, LOADINGDATE");
	    
	    List<Object[]> order =  oneDay.getResultList(); 
	    List<FerreroRequest24> orders24 = new ArrayList<FerreroRequest24>();
	    
	    FerreroRequest24 fr; 
	    
	    for(int i=0; i < order.size(); i++ ){
	    	fr  = new FerreroRequest24();	    	
	        Object[] r = (Object[]) order.get(i);
	        
	        	 fr.setAGENTSNAME((String) r[0]);
	 	        fr.setCLIENTSNAMEORD((String) r[1]);
	 	        fr.setADRESSLOCORD((String) r[2]);	
	 	        fr.setPAYTYPENAME((String) r[3]);
	 	        fr.setIdorder((Integer)r[4]);
	 	        fr.setDOCDATEORDER((java.sql.Date) r[5]);
	 	        
	 	        fr.setLOADINGDATE((java.sql.Date) r[6]);
	 	        
	 	      
	 	
	 	        if ( fr.getLOADINGDATE() != null){
	 	        	
	 	        	
	 	        	
	 	        	
	 	        	Date one = (java.sql.Date) r[5];
	 	        	Date two = (java.sql.Date) r[6];
	 	        	
	 	        	Calendar ordDay = Calendar.getInstance();
	 	        	ordDay.setTime(one);
	 	        	
	 	        	int day = ordDay.get(Calendar.DAY_OF_WEEK);
	 	        	System.out.println(one.toString() + " "+day);
	 	        	      	
	 	        	
	 	     	 	        	
	 	        	long daysBetween = TimeUnit.MILLISECONDS.toDays( two.getTime() - one.getTime());
	 	        	
	 	        	
	 	        	if(daysBetween < 2){
	 	        	fr.setNextday(1);
	 	        	} else if (ordDay.get(Calendar.DAY_OF_WEEK) == 6 && daysBetween == 3 ){
	 	        		fr.setNextday(1);	
	 	        	}
	 	        	
	 	        } else fr.setNextday(0);
	 	        
	 	        
	 	        
	 	        if (fr.getNextday() == 1){
	 	        	fr.setResult("100%");
	 	        } else fr.setResult("0");
	 	        
	 	       int ifExist=0;
	 	        if (fr.getLOADINGDATE() == null){
	 	        	int ordID = fr.getIdorder();
	 	        	
	 	        	for(Object o : order){
	 	        		
	 	        		Object[] check =(Object[]) o;
	 	        			if ((Integer)check[4] ==  ordID){
	 	        				ifExist += 1;
	 	        			}
	 	        		
	 	        	}
	 	        }
	 	        
	 	        if(ifExist < 2)
	 	        orders24.add(fr);
	  
	        } /*else {
	        	 Object[] test ;
	        	if (order.get(i+1) != null && order.get(i-1) != null){
	        		test = (Object[]) order.get(i+1);
	        		tes2 = 
	        		if(test[4] != r[4] ){
	        			
	        		}
	        	}
	        	
	        }
	        */
	       
	    

	    return orders24;

	}


	
	

}
