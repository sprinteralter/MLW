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
import javax.validation.constraints.NotNull;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeFactory;

import org.springframework.beans.factory.annotation.Autowired;

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
import com.rosteach.util.JsonMapperUtil;
import com.rosteach.util.QueryManagerUtil;
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
					ord.getHEAD().setBuyer(Long.valueOf(order.getBuyer()));//order.getBuyer()); 	//BUYER FROM ORDER.XML
					ord.getHEAD().setDeliveryplace(Long.valueOf(glnQ));
					ord.getHEAD().setSender(Long.valueOf("9863762978175"));
					ord.getHEAD().setRecipient(Long.valueOf(order.getBuyer()));
					
					//add ORDERSP forming date and number
					order.setOrdersp_date(ord.getDATE().toGregorianCalendar().getTime());
					order.setOrdersp_user(userdet.getName());
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
		/**
		 * Initialize our result collection to send data as response
		 */
		List<ResultLog> resultList = new LinkedList<ResultLog>();
		try{
			/**
			 * creating JAXB context and Marshaller for XML generation
			 */
			JAXBContext context = JAXBContext.newInstance(DESADV.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			/**
			 * get our input List as OutcomeInvoices
			 */
			List<SPROutcomeInvoice> inputInvoices=JsonMapperUtil.getInputInvoices(request);
			/**
			 * create our JPA manager and connect to FireBird and begin transaction
			 */
			EntityManager entityManager = new EntityManagerReferee().getConnection(userdet.getDB(), userdet.getName(), userdet.getPass());
			entityManager.getTransaction().begin();
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("SQL"); 
		    EntityManager em =  emf.createEntityManager();
		    em.getTransaction().begin();
		    
			for(SPROutcomeInvoice invoice: inputInvoices){
				//creating our notification entity and setters parameters
				DESADV note = new DESADV();
				/**
				 * get parameters for Order from QueryManager
				 */
				int orderid = QueryManagerUtil.getOrderIdBySPRoiID(invoice.getID(), entityManager);
				String ordernumber = QueryManagerUtil.getOrderNumberBySPRoiID(invoice.getID(), entityManager);
				java.sql.Date orderdate = QueryManagerUtil.getOrderDateBySPRoiID(invoice.getID(), entityManager);
				/**
				 * get Order from QueryManager as MySQL instance
				 */				
			    Order_info order =QueryManagerUtil.getOrder_infoByID(orderid, em);
			    /**
				 * begin to setUp or DESADV entity and ResultList according to XML notification parameters
				 */
			    note.setPreHEADParameters(order.getId(), LocalDate.now().toString(), invoice.getDOCDATE(),
			    						  ordernumber, orderdate.toString(), invoice.getREGNUMBER(),
			    						  invoice.getDOCDATE(), QueryManagerUtil.getCampaignNumberByClientID(invoice.getCLIENTID(), entityManager));
					//head settings
					DESADV.HEAD head = new HEAD();
					String postcode = QueryManagerUtil.getRecipientByClientID(invoice.getCLIENTID(), entityManager);
					
					head.setPrePositionPsrameters(order.getBuyer(), postcode,order.getBuyer());
					//head.setEDIINTERCHANGEID(0);			
						//setting details into packingsequence
						DESADV.HEAD.PACKINGSEQUENCE packingSequence = new DESADV.HEAD.PACKINGSEQUENCE();
						List<DESADV.HEAD.PACKINGSEQUENCE.POSITION> list= new ArrayList<DESADV.HEAD.PACKINGSEQUENCE.POSITION>();
						
						//query for details of OrdersOutcomeInvoices
						List<ClientRequestDetails> ORDERSOUTINVDET = QueryManagerUtil.getOrdersDetailsByID(orderid, entityManager);
						
						//query for details of OutcomeInvoices
						List<SPROutcomeInvoiceDetails> outcomeDetails = QueryManagerUtil.getOutcomeDetailsByID(invoice.getID(), entityManager);
						
						int orderedquantity = 0;
						int deliveryquantity = 0;
						double deliveryprice = 0;
						
							for(int i =0; i<ORDERSOUTINVDET.size();i++){
								DESADV.HEAD.PACKINGSEQUENCE.POSITION position = new DESADV.HEAD.PACKINGSEQUENCE.POSITION();
								position.setParameters(i+1, ORDERSOUTINVDET.get(i).getGOODSCODE(),
														ORDERSOUTINVDET.get(i).getMEASURESNAME(), 
														QueryManagerUtil.getOrderQuantityByParam(orderid, ORDERSOUTINVDET.get(i).getGOODSID(), entityManager),
														ORDERSOUTINVDET.get(i).getMEASURESNAME(), ORDERSOUTINVDET.get(i).getGOODSNAME(),
														QueryManagerUtil.getProductIdBuyerByParam(ORDERSOUTINVDET.get(i).getGOODSID(),invoice.getCLIENTID(),entityManager));
								
								int OrderGOODSID = ORDERSOUTINVDET.get(i).getGOODSID();
								boolean checkpoint = false;
								int checknumber=0;
								for(int j =0; j<outcomeDetails.size();j++){
									int OutcomeGOODSID = outcomeDetails.get(j).getGOODSID(); 
									if(OutcomeGOODSID==OrderGOODSID){
										checkpoint=true;
										checknumber=j;
										break;
									}
								}
								/**
								 * check our markup to set position para depends on input Invoices 
								 */
								if(checkpoint==false){
									position.setDelPriceAndQuantity(0.0, 0);
								}
								else{
									position.setDelPriceAndQuantity(outcomeDetails.get(checknumber).getITEMCOUNT(), outcomeDetails.get(checknumber).getENDPRICE());
								}
								
								orderedquantity+=position.getORDEREDQUANTITY();
								deliveryquantity+=position.getDELIVEREDQUANTITY();
								deliveryprice+=outcomeDetails.get(checknumber).getSUMITEMPRICE();
								list.add(position);
								entityManager.clear();
							}
						//block for updating our MySQL DATA
							order.setDesadv_date(DateUtils.asDate(LocalDate.now()));
							order.setDesadv_user(userdet.getName());
							em.persist(order);
							em.clear();
						/**
						 * ResultList creation with result parameters
						 */
						ResultLog result = new ResultLog("Ок","Ув. об отгрузке("+invoice.getREGNUMBER()+")",
														LocalDate.now().toString(),
														deliveryquantity,
														deliveryprice,
														orderedquantity);
					
						packingSequence.setPOSITION(list);
					
					head.setPACKINGSEQUENCE(packingSequence);
				
				note.setHEAD(head);
				//creating our xml document
				File directory = new File("C:/MLW/XMLDESADV/"+LocalDate.now()+"/");
				if(!directory.exists()){
					directory.mkdirs();
				}
				marshaller.marshal(note, new File("C:/MLW/XMLDESADV/"+LocalDate.now()+"/","DESADV_"+userdet.getName()+"_"+invoice.getREGNUMBER()+".xml"));
				resultList.add(result);
			}
			
			em.getTransaction().commit();
			em.clear();
			em.close();
			emf.close();
			
			EntityManagerFactory emfBird = entityManager.getEntityManagerFactory();
			entityManager.getTransaction().commit();
			entityManager.close();
			emfBird.close();
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
		} 
		return resultList;
	}
}
