/**
 * @author Pavlenko R.A.
 */
package com.rosteach.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.rosteach.entities.ClientRequestDetails;
import com.rosteach.entities.SPROutcomeInvoiceDetails;

public class QueryManagerUtil{
	/**
	 * input types: int, EntityManager 
	 * method to get orderId by input invoices iD from firebird table ORDERSOUTINV
	 * return type: int
	 * */
	public static int getOrderIdBySPRoiID(int invoiceId,EntityManager em){
		Query getorderId = em.createNativeQuery("select id from ORDERSOUTINV where OUTCOMEINVOICEIDSSET ='"+ invoiceId+"'");  
		return (int)getorderId.getSingleResult();
	}
	/**
	 * input types: int, EntityManager
	 * method to get comment2 as ordernumber by input invoices iD from firebird table ORDERSOUTINV
	 * return type: String
	 * */
	public static String getOrderNumberBySPRoiID(int invoiceId,EntityManager em){
		Query getComment = em.createNativeQuery("select COMMENT2 from ORDERSOUTINV where OUTCOMEINVOICEIDSSET ='"+ invoiceId+"'");  
		return (String)getComment.getSingleResult();  
	}
	/**
	 * input types: int, EntityManager 
	 * method to get ordersdate as DOCDATE from ORDERSOUTINV (FIREBIRD)
	 * return type: java.sql.Date
	 * */
	public static java.sql.Date getOrderDateBySPRoiID(int invoiceId,EntityManager em){
		Query getOrdersDate = em.createNativeQuery("select DOCDATE from ORDERSOUTINV where OUTCOMEINVOICEIDSSET ='"+ invoiceId+"'");  
		return (java.sql.Date)getOrdersDate.getSingleResult();  
	}
	/**
	 * input types: int, EntityManager 
	 * method to get campaignnumber as DOGNUM from CLIENT (FIREBIRD)
	 * return type: String
	 * */
	public static String getCampaignNumberByClientID(int clientId,EntityManager em){
		Query getCampaignNumber = em.createNativeQuery("select DOGNUM from CLIENT where id ="+ clientId);  
		return (String)getCampaignNumber.getSingleResult();  
	}
	/**
	 * input types: int,int,EntityManager 
	 * method to get orderQuantity as ITEMCOUNT from OrdersOutInvDet by id and goodsid (FIREBIRD)
	 * return type: double
	 * */
	public static double getOrderQuantityByParam(int Id,int goodsid,EntityManager em){
		Query getOrderQuantity = em.createNativeQuery("select ItemCount from OrdersOutInvDet where OrdersOutInvId ="+Id+" and GOODSID ="+goodsid);  
		return (double)getOrderQuantity.getSingleResult();  
	}
	/**
	 * input types: int,int,EntityManager 
	 * method to get orderQuantity as POSTCODE from CLIENT by clientID (FIREBIRD)
	 * return type: double
	 * */
	public static String getRecipientByClientID(int clientId,EntityManager em){
		Query getRECEPIENT = em.createNativeQuery("select postcode from client where id ="+ clientId);  
		return (String)getRECEPIENT.getSingleResult();
	}
	/**
	 * input types: int,EntityManager 
	 * method to get list of orders details according to order ID(FIREBIRD)
	 * return type: List<ClientRequestDetails>
	 * */
	public static List<ClientRequestDetails> getOrdersDetailsByID(int id,EntityManager em){
		Query getORDERSOUTINVDET = em.createNativeQuery("select * from SPRORDERSOUTINVDET ("+id+",Null,0,Null,0, Null,Null,0,0) order by GOODSID", ClientRequestDetails.class);	  
		@SuppressWarnings("unchecked")
		List<ClientRequestDetails> ORDERSOUTINVDET = getORDERSOUTINVDET.getResultList();
		return ORDERSOUTINVDET;
	}
	/**
	 * input types: int,EntityManager 
	 * method to get list of outcome details according to outcome ID(FIREBIRD)
	 * return type: List<SPROutcomeInvoiceDetails>
	 * */
	public static List<SPROutcomeInvoiceDetails> getOutcomeDetailsByID(int id,EntityManager em){
		Query getOutcomeDetails = em.createNativeQuery("select * from SPROUTCOMEINVOICEDET ("+id+",Null,0,Null,Null,0,0) order by GOODSID", SPROutcomeInvoiceDetails.class);	
		@SuppressWarnings("unchecked")
		List<SPROutcomeInvoiceDetails> outcomeDetails = getOutcomeDetails.getResultList();
		return outcomeDetails;
	}
}
