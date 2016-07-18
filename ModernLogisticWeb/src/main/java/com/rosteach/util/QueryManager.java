package com.rosteach.util;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class QueryManager {
	public QueryManager(){
		
	}
	public String getOrderNumber(Integer invoiceId,EntityManager entityManager){
		Query getComment = entityManager.createNativeQuery("select COMMENT2 from ORDERSOUTINV where OUTCOMEINVOICEIDSSET ='"+ invoiceId+"'");  
		String ordernumber = (String)getComment.getSingleResult();
		return ordernumber;
	}
}
