package com.rosteach.xmlgen;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
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
import com.rosteach.entities.EntityManagerReferee;
import com.rosteach.entities.SPROutcomeInvoice;
import com.rosteach.entities.SPROutcomeInvoiceDetails;
import com.rosteach.util.DateUtils;
import com.rosteach.xml.DESADV;
import com.rosteach.xml.DESADV.HEAD;

public class XmlGenerator {
	
	private final GetDetails userdet = new GetDetails();
	
	public XmlGenerator(){
	}
	
	public boolean generateConfirmation(String request){
		return true;
	}
	
	public boolean generateNotification(String request){
		boolean result = false;
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
			
			c.setTime(inputInvoices.get(0).getDOCDATE());
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
				note.setDELIVERYDATE(deliverydate);
				note.setORDERNUMBER(0);
				note.setORDERDATE(null);
				note.setDELIVERYNOTENUMBER(invoice.getREGNUMBER());//regnumber from SPROutcomeinvoice
				note.setDELIVERYNOTEDATE(deliverydate);
				note.setCAMPAIGNNUMBER(0);
					
					//head settings
					DESADV.HEAD head = new HEAD();
					
					head.setSUPPLIER("9863762978175");//final value
					head.setBUYER(0);
					
					Query getPOSTCODE = entityManager.createNativeQuery("select postcode from client where id ="+ invoice.getCLIENTID());  
					Integer postcode = Integer.parseInt((String)getPOSTCODE.getSingleResult());
					
					head.setDELIVERYPLACE(postcode);//postcode from clients
					head.setSENDER("9863762978175");//final value
					head.setRECIPIENT(0);
					head.setEDIINTERCHANGEID(0);
			
						//setting details into packingsequence
						DESADV.HEAD.PACKINGSEQUENCE packingSequence = new DESADV.HEAD.PACKINGSEQUENCE();
						List<DESADV.HEAD.PACKINGSEQUENCE.POSITION> list= new ArrayList<DESADV.HEAD.PACKINGSEQUENCE.POSITION>();
						
						//query for details
						Query query = entityManager.createNativeQuery("select * from SPROUTCOMEINVOICEDET ("+invoice.getID()+",Null,0,Null,Null,0,0)", SPROutcomeInvoiceDetails.class);	
						@SuppressWarnings("unchecked")
						List<SPROutcomeInvoiceDetails> details = query.getResultList();
						//query for client table from our database
							for(int i =0; i<details.size();i++){
								DESADV.HEAD.PACKINGSEQUENCE.POSITION position = new DESADV.HEAD.PACKINGSEQUENCE.POSITION();
								position.setPOSITIONNUMBER(i+1);
								position.setPRODUCT(details.get(i).getGOODSCODE());
								
								position.setPRODUCTIDBUYER(0);//from clients 
								position.setDELIVEREDQUANTITY(details.get(i).getITEMCOUNT());
								position.setDELIVEREDUNIT(details.get(i).getMEASURESNAME());
								position.setORDEREDQUANTITY(0);
								position.setORDERUNIT(null);
								position.setPRICE(details.get(i).getENDPRICE());
								position.setDESCRIPTION(details.get(i).getGOODSNAME());
								
								list.add(position);
							}
						
				packingSequence.setPOSITION(list);
				head.setPACKINGSEQUENCE(packingSequence);
			
				note.setHEAD(head);
				//creating our xml document
				marshaller.marshal(note, new File("C:/Users/admin/Desktop/forgenxml","DESADV_"+userdet.getName()+"_"+invoice.getREGNUMBER()+".xml"));
			}
			EntityManagerFactory emf = entityManager.getEntityManagerFactory();
			entityManager.getTransaction().commit();
			entityManager.close();
			emf.close();
			result=true;
		}
		catch(JAXBException ex){
			System.out.println("-----------------------"+ex.getMessage());
		}
		catch(JsonMappingException ex){
			System.out.println("-----------------------"+ex.getStackTrace());
		}
		catch(JsonParseException ex){
			ex.getStackTrace();
		}
		catch(IOException ex){
			ex.getStackTrace();
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		return result;
	}
}
