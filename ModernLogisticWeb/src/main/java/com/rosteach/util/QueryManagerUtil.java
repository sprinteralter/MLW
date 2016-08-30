/**
 * @author Pavlenko R.A.
 */
package com.rosteach.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.rosteach.entities.ClientRequestDetails;
import com.rosteach.entities.Order_info;
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
	public static double getOrderQuantityByParam(int Id,int goodsid,EntityManager em,int goodsgroupid){	
		Query getOrderQuantity = em.createNativeQuery("select ItemCount from OrdersOutInvDet where OrdersOutInvId ="+Id+" and GOODSID ="+goodsid);	
		Query getMeasureCoef = em.createNativeQuery("select coef from goodsmeasurelink where goodsid ="+goodsid+" and measureid = "+4);
		double result = (double)getOrderQuantity.getSingleResult();
		if(goodsgroupid==740){
			return result*(double)getMeasureCoef.getSingleResult(); 
		}
		else
		return result;  
	}
	/**
	 * input types: int,double,int,EntityManager 
	 * method to get deliveredQuantity as ITEMCOUNT from OrdersOutInvDet by id and goodsid according to groopid (FIREBIRD)
	 * return type: double
	 * */
	public static double getDeliveredQuantityByParam(int goodsid,double itemcount, int goodsgroupid,EntityManager em){		
		Query getMeasureCoef = em.createNativeQuery("select coef from goodsmeasurelink where goodsid ="+goodsid+" and measureid ="+4);
		double coef = (double)getMeasureCoef.getSingleResult();
		System.out.println("---------------coef"+coef);
		if(goodsgroupid==740){
			return itemcount*coef;
		}
		else
		return itemcount;
	}
	/**
	 * input types: int,double,int,EntityManager 
	 * method to get delivereEndprice per COUNT from OrdersOutInvDet by endprice according to goodsgroopid (FIREBIRD)
	 * return type: double
	 * */
	public static double getDeliveredEndPriceByParam(int clientid, int goodsgroupid,int goodsid,EntityManager em){		
		double coef=0;
		double endprice=0;
		
		Query getClientPriceID = em.createNativeQuery("select pricetypeid from client where id="+clientid);
		int priceid = (short)getClientPriceID.getSingleResult();
		
		Query getClientBankMFO = em.createNativeQuery("select bankmfo from client where id="+clientid);
		int bankMFO = Integer.parseInt((String)getClientBankMFO.getSingleResult());
		
		if(bankMFO==322313){
			Query getValWithOverth = em.createNativeQuery("select VAL from SPRFIXEDPRICE ("+priceid+",Null,0,Null,Null,0,0,Null) where goodsid ="+goodsid);
			endprice = (double)getValWithOverth.getSingleResult();
		}
		else{
			Query getValWithOverth = em.createNativeQuery("select VALWITHOVERH from SPRFIXEDPRICE ("+priceid+",Null,0,Null,Null,0,0,Null) where goodsid ="+goodsid);
			endprice = (double)getValWithOverth.getSingleResult();
		}
		
		Query getMeasureCoef = em.createNativeQuery("select coef from goodsmeasurelink where goodsid ="+goodsid+" and measureid ="+4);
		coef = (double)getMeasureCoef.getSingleResult();
		
		double div = endprice/coef;
		
		if(goodsgroupid==740){
			String res = div+"";
			if(res.length()>7){
				return Double.parseDouble(res.substring(0,7));
			}
			else 
			return div;
		}
		else
		return endprice;
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
	/**
	 * input types: int,EntityManager 
	 * method to get Order according to orderid from firebird (MySQL)
	 * return type: Order_info
	 * */
	public static Order_info getOrder_infoByID(int orderid,EntityManager em){
		Query getOrder_info = em.createNativeQuery("Select * from users_auth.order_info where order_kod = "+orderid, Order_info.class);	
		Order_info order = (Order_info)getOrder_info.getSingleResult();
		return order;
	}
	/**
	 * input types: int goodsid,int clientid,EntityManager 
	 * method to get productIdBuyer as PRODCODE from PRODLINK(FIREBIRD)
	 * return type: String
	 * */
	public static String getProductIdBuyerByParam(int goodsid,int clientid,EntityManager em){
		Query getPRODUCTIDBUYER = em.createNativeQuery("select prodcode from prodlink where clientid="+clientid+"and goodsid="+goodsid);	
		return (String)getPRODUCTIDBUYER.getSingleResult();
	}	
}
