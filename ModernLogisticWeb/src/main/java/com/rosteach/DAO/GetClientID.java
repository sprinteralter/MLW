package com.rosteach.DAO;
/**
 * @author Rostislav Pavlenko
 * */
import java.io.File;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.rosteach.xml.DocListInvoice;


public class GetClientID {

	/**
	 * getClientId method with input File reference  
	 * */
	public int getClientId(String file, int index,EntityManager em) throws JAXBException{
		int temp=0;
		try{
			/**
			 * Create an Object with JAXBContext with unmarshalling
			 * */
			DocListInvoice document = (DocListInvoice)((JAXBContext.newInstance(DocListInvoice.class)).createUnmarshaller()).unmarshal(new File(file));
			
				/**
				 * SQL query creation (insertion)
				 * */
				/*String sqlH ="SELECT id FROM client WHERE info3='"+
								document.getDocumentInvoice().get(index).getInvoiceParties().get(0).getDeliveryKod().get(0).getTaxID()+"'";
				ResultSet rsId = stm.executeQuery(sqlH);*/
				Query clientIdQuery = em.createNativeQuery("SELECT id FROM client WHERE info3='"+
						document.getDocumentInvoice().get(index).getInvoiceParties().get(0).getDeliveryKod().get(0).getTaxID()+"'");
				/**
				 * process the result set
				 * */
				temp=(Integer)clientIdQuery.getSingleResult();
				/*while(rsId.next()) {
					temp = rsId.getInt("ID");
				}*/
				System.out.println("ClientId !!!"+temp);
				/**
				 * Closing our statement and connection!
				 * */
		}
		catch(Exception e){
			e.getMessage();
		}
		return temp;
	}
}
