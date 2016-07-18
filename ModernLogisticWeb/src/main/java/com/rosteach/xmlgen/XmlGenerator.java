package com.rosteach.xmlgen;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rosteach.DAO.security.GetDetails;
import com.rosteach.entities.ClientRequestDetails;
import com.rosteach.entities.EntityManagerReferee;
import com.rosteach.entities.ResultLog;
import com.rosteach.entities.SPROutcomeInvoice;
import com.rosteach.entities.SPROutcomeInvoiceDetails;
import com.rosteach.util.DateUtils;
import com.rosteach.xml.DESADV;
import com.rosteach.xml.DESADV.HEAD;

public class XmlGenerator{
	
	private final GetDetails userdet = new GetDetails();

	
	public XmlGenerator(){
	}
	
	public List<ResultLog> generateConfirmation(String request){
		return null;
	}
	
	public List<ResultLog> generateNotification(String request){
		//needed variables for confirmation resultlist
		ResultLog result = new ResultLog();
		List<ResultLog> resultList = new LinkedList<ResultLog>();
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
				//creating our notification entity and setters parameters
				DESADV note = new DESADV();
				int number=0; 
				
				// prehead settings of DESADV
				note.setNUMBER(number+1);
				
				note.setDATE(date);
				result.setTotalname("Уведомление об отгрузке по расходной накладной: "+invoice.getREGNUMBER());
				result.setTotaldate(localdate.toString());
		
				note.setDELIVERYDATE(deliverydate);
				
				Query getorderId = entityManager.createNativeQuery("select id from ORDERSOUTINV where OUTCOMEINVOICEIDSSET ='"+ invoice.getID()+"'");  
				Integer orderid = (Integer)getorderId.getSingleResult();
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
						
							for(int i =0; i<ORDERSOUTINVDET.size();i++){
								Integer OrderGOODSID = ORDERSOUTINVDET.get(i).getGOODSID();
								boolean checkpoint = false;
								int checknumber=0;
								for(int j =0; j<outcomeDetails.size()-1;j++){
									Integer OutcomeGOODSID = outcomeDetails.get(j).getGOODSID(); 
									if(OutcomeGOODSID.equals(OrderGOODSID)){
										checkpoint=true;
										checknumber=j;
									}
								}
								System.out.println("--------------------checkpoint-------------------------"+checkpoint);
									
								if(checkpoint==false){
									System.out.println("------------------nullPosition-------------------");
									DESADV.HEAD.PACKINGSEQUENCE.POSITION position = new DESADV.HEAD.PACKINGSEQUENCE.POSITION();
									position.setPOSITIONNUMBER(i+1);
									position.setPRODUCT(null);
									
									position.setPRODUCTIDBUYER(0);//from clients 
									position.setDELIVEREDQUANTITY(0.0);
									
									Query getORDERUNIT = entityManager.createNativeQuery("select SNAME from MEASURE where ID ="+outcomeDetails.get(checknumber).getMEASUREID());  
									String orderUnit = (String)getORDERUNIT.getSingleResult();
									position.setDELIVEREDUNIT(orderUnit);
									
									//setting our queries for orders details
									Query getORDEREDQUANTITY = entityManager.createNativeQuery("select ItemCount from OrdersOutInvDet where OrdersOutInvId ="+orderid+"and GOODSID ="+ORDERSOUTINVDET.get(i).getGOODSID());  
									Double orderquantity = (Double)getORDEREDQUANTITY.getSingleResult();
									position.setORDEREDQUANTITY(orderquantity);
								
									position.setORDERUNIT(orderUnit);
									
									position.setPRICE(outcomeDetails.get(checknumber).getENDPRICE());
									position.setDESCRIPTION(ORDERSOUTINVDET.get(i).getGOODSNAME());
									
									list.add(position);
								}
								else{
									System.out.println("------------------fullPosition-------------------");
									DESADV.HEAD.PACKINGSEQUENCE.POSITION position = new DESADV.HEAD.PACKINGSEQUENCE.POSITION();
									position.setPOSITIONNUMBER(i+1);
									position.setPRODUCT(ORDERSOUTINVDET.get(i).getGOODSCODE());
									
									position.setPRODUCTIDBUYER(0);//from clients 
									position.setDELIVEREDQUANTITY(ORDERSOUTINVDET.get(i).getITEMCOUNT());
									position.setDELIVEREDUNIT(ORDERSOUTINVDET.get(i).getMEASURESNAME());
									
									//setting our queries for orders details
									Query getORDEREDQUANTITY = entityManager.createNativeQuery("select ItemCount from OrdersOutInvDet where OrdersOutInvId ="+orderid+"and GOODSID ="+outcomeDetails.get(i).getGOODSID());  
									Double orderquantity = (Double)getORDEREDQUANTITY.getSingleResult();
									position.setORDEREDQUANTITY(orderquantity);
									
									Query getORDERUNIT = entityManager.createNativeQuery("select SNAME from MEASURE where ID ="+1);  
									String orderUnit = (String)getORDERUNIT.getSingleResult();
									position.setORDERUNIT(orderUnit);
									
									System.out.println("--------------------------------"+outcomeDetails.get(checknumber).getENDPRICE());
									position.setPRICE(outcomeDetails.get(checknumber).getENDPRICE());
									position.setDESCRIPTION(ORDERSOUTINVDET.get(i).getGOODSNAME());
									
									list.add(position);
									System.out.println("------------------fullFillPosition-------------------");
								}	
							}
						
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
