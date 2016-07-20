package com.rosteach.xmlgen;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rosteach.DAO.order_info.Order_infoDAO;
import com.rosteach.DAO.security.GetDetails;
import com.rosteach.entities.ClientRequestDetails;
import com.rosteach.entities.EntityManagerReferee;
import com.rosteach.entities.Order_info;
import com.rosteach.entities.ResultLog;
import com.rosteach.entities.SPROutcomeInvoice;
import com.rosteach.entities.SPROutcomeInvoiceDetails;
import com.rosteach.util.DateUtils;
import com.rosteach.xml.DESADV;
import com.rosteach.xml.DESADV.HEAD;
import com.rosteach.xml.ordersp.ORDRSP;
import com.rosteach.xml.ordersp.ORDRSP.HEAD.POSITION;


public class XmlGenerator{
	
	private final GetDetails userdet = new GetDetails();
	
	@Autowired
	public Order_infoDAO ord_info;
	
	public XmlGenerator(){
	}
	
	public List<ResultLog> generateConfirmation(String request){
		
		List<ResultLog> resultList = new LinkedList<ResultLog>();
		try {
			ObjectMapper mapper = new ObjectMapper(); 
			List<SPROutcomeInvoice> inputInvoices = null;
			inputInvoices = mapper.readValue(request, new TypeReference<List<SPROutcomeInvoice>>(){});
			EntityManager entityManager = new EntityManagerReferee().getConnection(userdet.getDB(), userdet.getName(), userdet.getPass());
			for (SPROutcomeInvoice out : inputInvoices){
				ResultLog result = new ResultLog();
				ORDRSP ord = new ORDRSP();
				GregorianCalendar c = new GregorianCalendar();
				DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();

				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");	
				
				Query getCampaignNumber = entityManager.createNativeQuery("select DOGNUM from CLIENT where id ="+ out.getCLIENTID());  
				String campaignNumber = (String)getCampaignNumber.getSingleResult();
				ord.setCAMPAIGNNUMBER(campaignNumber);
				
				
				Query orderQ = entityManager.createNativeQuery("select COMMENT2, DOCDATE from ORDERSOUTINV where OUTCOMEINVOICEIDSSET ='"+ out.getID()+"'");
				  
				List<Object[]> respQ = orderQ.getResultList();

					ord.setDATE(datatypeFactory.newInstance().newXMLGregorianCalendar(format.format(c.getTime())));
					ord.setORDERNUMBER(String.valueOf(respQ.get(0)[0]));
						c.setTime(format.parse(String.valueOf(respQ.get(0)[1])));
					ord.setORDERDATE(datatypeFactory.newInstance().newXMLGregorianCalendar(format.format(c.getTime())));

					Query getPOSTCODE = entityManager.createNativeQuery("select postcode from client where id ="+ out.getCLIENTID());
					String glnQ = (String) getPOSTCODE.getSingleResult();
					Query getorderId = entityManager.createNativeQuery("select id from ORDERSOUTINV where OUTCOMEINVOICEIDSSET ='"+ out.getID()+"'");  
					Integer orderid = (Integer)getorderId.getSingleResult();
					//Order_info order = ord_info.getOrder_info_byKod(orderid); 	//get order by id
					
					EntityManagerFactory emf = Persistence.createEntityManagerFactory("SQL"); 
				    EntityManager em =  emf.createEntityManager();					
					Order_info order =(Order_info) em.createNativeQuery("Select * from users_auth.order_info where order_kod = "+orderid, Order_info.class).getSingleResult();// oi.get(0);
					
					ord.setNUMBER(order.getOrder_kod()); // order.getOrder_kod());
					ord.setHEAD(new ORDRSP.HEAD());
					ord.getHEAD().setSupplier(Long.valueOf("9863762978175"));
					ord.getHEAD().setBuyer(Long.valueOf(glnQ));//order.getBuyer()); 	//BUYER FROM ORDER.XML
					ord.getHEAD().setDeliveryplace(Long.valueOf(glnQ));
					ord.getHEAD().setSender(Long.valueOf("9863762978175"));
					ord.getHEAD().setRecipient(Long.valueOf(glnQ));
					
					//add ORDERSP forming date and number
					order.setOrdersp_date(ord.getDATE().toGregorianCalendar().getTime());
					order.setOrdersp_number(ord.getORDERNUMBER());
					//ord_info.persistOrder(order);
					
					em.getTransaction().begin();
				    em.persist(order);
				    em.getTransaction().commit();
				    em.clear();
				    em.close();
				    
					Query query = entityManager.createNativeQuery("select * from SPROUTCOMEINVOICEDET ("+out.getID()+",Null,0,Null,Null,0,0) order by GOODSID", SPROutcomeInvoiceDetails.class);	
					List<SPROutcomeInvoiceDetails> outcome= (List<SPROutcomeInvoiceDetails>) query.getResultList(); //rashod naklad
					
					Query getORDERSOUTINVDET = entityManager.createNativeQuery("select * from SPRORDERSOUTINVDET ("+orderid+",Null,0,Null,0, Null,Null,0,0) order by GOODSID", ClientRequestDetails.class);	
					@SuppressWarnings("unchecked")
					List<ClientRequestDetails> ORDERSOUTINVDET = getORDERSOUTINVDET.getResultList(); //zayavka ot client
					
					double orderedquantity = 0;
					double deliveryprice=0;
					double deliveryquantity=0;
					
					for (int i=0; i < ORDERSOUTINVDET.size(); i++){
						POSITION p = new POSITION();
						p.setPOSITIONNUMBER((short) i);
						p.setPRODUCT(ORDERSOUTINVDET.get(i).getGOODSCODE());
						p.setDESCRIPTION(ORDERSOUTINVDET.get(i).getGOODSNAME());
						p.setORDEREDQUANTITY(ORDERSOUTINVDET.get(i).getITEMCOUNT());
						p.setACCEPTEDQUANTITY(0.0);
						p.setPRODUCTIDBUYER(ORDERSOUTINVDET.get(i).getITEMPRICE());
						
						
						for(SPROutcomeInvoiceDetails o : outcome ){ //SPROutcomeInvoiceDetails o : positions  
							int outGoodsID = o.getGOODSID();
							int ordGoodsID = ORDERSOUTINVDET.get(i).getGOODSID();
							if(ordGoodsID == outGoodsID ){
								deliveryprice+=o.getSUMITEMPRICEWITHOVERH();
								p.setACCEPTEDQUANTITY(o.getITEMCOUNT());
								p.setPRICE(o.getENDPRICE());
								break;
							}
							
						}
						
						
						if(p.getORDEREDQUANTITY().intValue() == p.getACCEPTEDQUANTITY().intValue()){
							p.setPRODUCTTYPE(1);
						}
						if(p.getORDEREDQUANTITY().intValue() != p.getACCEPTEDQUANTITY().intValue() && p.getACCEPTEDQUANTITY().intValue() != 0 ){
							p.setPRODUCTTYPE(2);
						}
						if( p.getACCEPTEDQUANTITY().intValue() == 0 ){
							p.setPRODUCTTYPE(3);
						}
						orderedquantity+=p.getORDEREDQUANTITY();
						
						deliveryquantity+=p.getACCEPTEDQUANTITY();
						
						ord.getHEAD().getPOSITION().add(p);
					}
					
					
					String date = String.valueOf(format.format(new Date()));
					File directory = new File("C:/MLW/XMLORDERSP/"+date+"/");
					if(!directory.exists()){
						directory.mkdirs();
					}
					
					JAXBContext context = JAXBContext.newInstance(ORDRSP.class);
					Marshaller marshaller = context.createMarshaller();
					marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
					marshaller.marshal(ord, new File("C:/MLW/XMLORDERSP/"+date+"/","ORDERSP_"+userdet.getName()+"_"+out.getREGNUMBER()+".xml"));
					
					//result.setTotalbuyer(String.valueOf(ord.getHEAD().getBuyer()));
					
					//result.setTotaldeliveryplace(String.valueOf(ord.getHEAD().getDeliveryplace()));
					result.setTotalname("Подтв. заказа("+ord.getORDERNUMBER()+")");
					result.setTotaldate(ord.getDATE().toString());
					result.setTotalorderedquantity((int) orderedquantity);
					result.setTotaldeliveryprice(deliveryprice);	
					result.setTotaldeliveryquantity((int) deliveryquantity);
					result.setTotalInfo("ok");
					resultList.add(result);
					entityManager.clear();
					
					
			}
			
			} catch (Exception  e) {
				e.printStackTrace();
				return (List<ResultLog>) e;
			}
		
		return resultList;
	}
	
	//*********************************************************************************************
	
	public List<ResultLog> generateNotification(String request){
		//needed variables for confirmation resultlist
		List<ResultLog> resultList = new ArrayList<ResultLog>();
		try{
			//creating JAXB context and Marshaller for XML
			JAXBContext context = JAXBContext.newInstance(DESADV.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			//our input collection of outcominvoices
			ObjectMapper mapper = new ObjectMapper(); 
			List<SPROutcomeInvoice> inputInvoices = mapper.readValue(request, new TypeReference<List<SPROutcomeInvoice>>(){});
			
			//date formatting
			LocalDate localdate = LocalDate.now();
						
			System.out.println("--------------date------------"+DateUtils.asDate(localdate));
			
			GregorianCalendar c = new GregorianCalendar();
			
			//c.setTime(inputInvoices.get(0).getDOCDATE());
			XMLGregorianCalendar deliverydate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
			
			c.setTime(DateUtils.asDate(localdate));
			XMLGregorianCalendar date = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
			
			
			EntityManager entityManager = new EntityManagerReferee().getConnection(userdet.getDB(), userdet.getName(), userdet.getPass());
			entityManager.getTransaction().begin();
			
			for(SPROutcomeInvoice invoice: inputInvoices){
				ResultLog result = new ResultLog();
				//creating our notification entity and setters parameters
				DESADV note = new DESADV();
				int number=0; 
				
				// prehead settings of DESADV
				note.setNUMBER(number+1);
				
				note.setDATE(date);
				result.setTotalname("Ув. об отгрузке("+invoice.getREGNUMBER()+")");
				result.setTotaldate(localdate.toString());
		
				note.setDELIVERYDATE(deliverydate);
				
				Query getorderId = entityManager.createNativeQuery("select id from ORDERSOUTINV where OUTCOMEINVOICEIDSSET ='"+ invoice.getID()+"'");  
				int orderid = (int)getorderId.getSingleResult();
				System.out.println("------------------------------orderid------------------------"+orderid);
				
				//selecting ordernum from orders as comment2
				Query getComment = entityManager.createNativeQuery("select COMMENT2 from ORDERSOUTINV where OUTCOMEINVOICEIDSSET ='"+ invoice.getID()+"'");  
				String ordernumber = (String)getComment.getSingleResult();
				System.out.println("------------------------------ordernumber------------------------"+ordernumber);
				
				Query getOrdersDate = entityManager.createNativeQuery("select DOCDATE from ORDERSOUTINV where OUTCOMEINVOICEIDSSET ='"+ invoice.getID()+"'");  
				java.sql.Date orderdate = (java.sql.Date)getOrdersDate.getSingleResult();
				System.out.println("------------------------------orderdate------------------------"+orderdate);
				
				note.setORDERNUMBER(ordernumber);
				note.setORDERDATE(orderdate.toString());
				note.setDELIVERYNOTENUMBER(invoice.getREGNUMBER());//regnumber from SPROutcomeinvoice
				note.setDELIVERYNOTEDATE(deliverydate);
				
				Query getCampaignNumber = entityManager.createNativeQuery("select DOGNUM from CLIENT where id ="+ invoice.getCLIENTID());  
				String campaignNumber = (String)getCampaignNumber.getSingleResult();
				note.setCAMPAIGNNUMBER(campaignNumber);
					
					//head settings
					DESADV.HEAD head = new HEAD();
					
					head.setSUPPLIER("9863762978175");//final value
					head.setBUYER(0);
					
					Query getPOSTCODE = entityManager.createNativeQuery("select postcode from client where id ="+ invoice.getCLIENTID());  
					String postcode = (String)getPOSTCODE.getSingleResult();
					
					head.setDELIVERYPLACE(postcode);//postcode from clients
					head.setSENDER("9863762978175");//final value
					head.setRECIPIENT(0);
					head.setEDIINTERCHANGEID(0);
			
						//setting details into packingsequence
						DESADV.HEAD.PACKINGSEQUENCE packingSequence = new DESADV.HEAD.PACKINGSEQUENCE();
						List<DESADV.HEAD.PACKINGSEQUENCE.POSITION> list= new ArrayList<DESADV.HEAD.PACKINGSEQUENCE.POSITION>();
						
						//query for details of OrdersOutcomeInvoices
						Query getORDERSOUTINVDET = entityManager.createNativeQuery("select * from SPRORDERSOUTINVDET ("+orderid+",Null,0,Null,0, Null,Null,0,0) order by GOODSID", ClientRequestDetails.class);	
						@SuppressWarnings("unchecked")
						List<ClientRequestDetails> ORDERSOUTINVDET = getORDERSOUTINVDET.getResultList();
						
						//query for details of OutcomeInvoices
						Query getOutcomeDetails = entityManager.createNativeQuery("select * from SPROUTCOMEINVOICEDET ("+invoice.getID()+",Null,0,Null,Null,0,0) order by GOODSID", SPROutcomeInvoiceDetails.class);	
						@SuppressWarnings("unchecked")
						List<SPROutcomeInvoiceDetails> outcomeDetails = getOutcomeDetails.getResultList();
						
						
						int orderedquantity = 0;
						double deliveryprice = 0;
						int deliveryquantity = 0;
							for(int i =0; i<ORDERSOUTINVDET.size();i++){
								
								DESADV.HEAD.PACKINGSEQUENCE.POSITION position = new DESADV.HEAD.PACKINGSEQUENCE.POSITION();
								position.setPOSITIONNUMBER(i+1);
								position.setPRODUCT(ORDERSOUTINVDET.get(i).getGOODSCODE());
								position.setPRODUCTIDBUYER(0);//from clients 
								position.setDELIVEREDUNIT(ORDERSOUTINVDET.get(i).getMEASURESNAME());
								
								Query getORDEREDQUANTITY = entityManager.createNativeQuery("select ItemCount from OrdersOutInvDet where OrdersOutInvId ="+orderid+" and GOODSID ="+ORDERSOUTINVDET.get(i).getGOODSID());  
								double orderquantity = (double)getORDEREDQUANTITY.getSingleResult();
								position.setORDEREDQUANTITY(orderquantity);
								position.setORDERUNIT(ORDERSOUTINVDET.get(i).getMEASURESNAME());
								position.setDESCRIPTION(ORDERSOUTINVDET.get(i).getGOODSNAME());
								
								int OrderGOODSID = ORDERSOUTINVDET.get(i).getGOODSID();
								boolean checkpoint = false;
								int checknumber=0;
								for(int j =0; j<outcomeDetails.size();j++){
									int OutcomeGOODSID = outcomeDetails.get(j).getGOODSID(); 
									if(OutcomeGOODSID==OrderGOODSID){
										System.out.println("--------------OutcomeGOODSID---------------"+OutcomeGOODSID);
										System.out.println("--------------OutcomeGOODSID---------------"+OrderGOODSID);
										checkpoint=true;
										checknumber=j;
										break;
									}
								}
									
								if(checkpoint==false){
									position.setDELIVEREDQUANTITY(0.0);
									position.setPRICE(0.0);
								}
								else{
									position.setDELIVEREDQUANTITY(outcomeDetails.get(checknumber).getITEMCOUNT());
									position.setPRICE(outcomeDetails.get(checknumber).getSUMITEMPRICEWITHOVERH());
								}
								System.out.println("-----------------------------------------"+outcomeDetails.get(checknumber).getITEMCOUNT());
								orderedquantity+=position.getORDEREDQUANTITY();
								deliveryprice+=position.getPRICE();
								deliveryquantity+=position.getDELIVEREDQUANTITY();
								list.add(position);
							}
				result.setTotalorderedquantity(orderedquantity);
				result.setTotaldeliveryprice(deliveryprice);	
				result.setTotaldeliveryquantity(deliveryquantity);
				result.setTotalInfo("");
				
				packingSequence.setPOSITION(list);
				head.setPACKINGSEQUENCE(packingSequence);
			
				note.setHEAD(head);
				//creating our xml document
				File directory = new File("C:/MLW/XMLDESADV/"+localdate+"/");
				if(!directory.exists()){
					directory.mkdirs();
				}
				marshaller.marshal(note, new File("C:/MLW/XMLDESADV/"+localdate+"/","DESADV_"+userdet.getName()+"_"+invoice.getREGNUMBER()+".xml"));
				resultList.add(result);
				outcomeDetails=null;
			}
			EntityManagerFactory emf = entityManager.getEntityManagerFactory();
			entityManager.getTransaction().commit();
			entityManager.close();
			emf.close();
		}
		catch(JAXBException ex){
			System.out.println("-----------------------"+ex.getMessage());
		}
		catch(JsonMappingException ex){
			System.out.println("-----------------------"+ex.getMessage());
		}
		catch(JsonParseException ex){
			System.out.println("-----------------------"+ex.getMessage());
		}
		catch(IOException ex){
			System.out.println("-----------------------"+ex.getMessage());
		} catch (DatatypeConfigurationException ex) {
			System.out.println("-----------------------"+ex.getMessage());
		}
		return resultList;
	}
}
