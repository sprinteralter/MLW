/**
 * @author Pavlenko R. --Sprinter K-- 
 * Java XMLBinding
 * */
package com.rosteach.DAO;

import java.io.File;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.stereotype.Repository;

import com.rosteach.xml.DocListInvoice;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Repository
public class InsertionDocInvoice {
	/**
	 * Initializing our directories and files to scann
	 * Initializing our variables
	 * */
	public static final String path = "C:/MLW/XMLDOC/";
	public static final File pack = new File(path);
	
	//method for checking date of input xml file
	public boolean checkDate() throws JAXBException{
		if(pack.isDirectory()){
			String [] s= pack.list();
			for(int i=0;i<s.length;i++){						
				DocListInvoice document = (DocListInvoice)((JAXBContext.newInstance(DocListInvoice.class)).createUnmarshaller()).unmarshal(new File(path,s[i]));
					for(int x=0;x<=document.getDocumentInvoice().size()-1;x++){
						XMLGregorianCalendar documentDate = document.getDocumentInvoice().get(x).getInvoiceHeader().get(0).getInvoiceDate();
						System.out.println("--------------document-date--------------"+documentDate.getDay());
						System.out.println("--------------document-month--------------"+documentDate.getMonth());
						System.out.println("--------------document-year--------------"+documentDate.getYear());
						
						//get currentDate
						LocalDate currentDate = LocalDate.now();
						
						Integer currentYear = currentDate.getYear();
						Integer currentMonth = currentDate.getMonthValue();
						Integer currentDay = currentDate.getDayOfMonth();
						
						System.out.println("--------------current-date---------------"+currentDay);
						System.out.println("--------------current-month---------------"+currentMonth);
						System.out.println("--------------current-year---------------"+currentYear);
						
						if(currentMonth<documentDate.getMonth()){
							return true;
						}
						else if(currentMonth==documentDate.getMonth()){
							if(currentDay<=documentDate.getDay()){
								return true;
							}
							else{
								return false;
							}
						}
					}
			}
		}
		return true;
	}
	
	//method which return date of the document 
	public String getDate() throws JAXBException{
		String result = "";
		if(pack.isDirectory()){
			String [] s= pack.list();
			for(int i=0;i<s.length;i++){						
				DocListInvoice document = (DocListInvoice)((JAXBContext.newInstance(DocListInvoice.class)).createUnmarshaller()).unmarshal(new File(path,s[i]));
					for(int x=0;x<=document.getDocumentInvoice().size()-1;){
						XMLGregorianCalendar date = document.getDocumentInvoice().get(x).getInvoiceHeader().get(0).getInvoiceDate();
						result = date+"";
						break;
					}
			}
		}
		return result;
	}
	
	//method for insert input data from xml file(DoclistInvoice entity!)
	public void insertData(String dataBase,String login, String password) throws JAXBException{ 	
		try{
			/**
			 * set Properties for our connection
			 * */
			
			Map<String,String> properties = new HashMap<String,String>();
			properties.put("javax.persistence.jdbc.url", dataBase);
			properties.put("javax.persistence.jdbc.user", login);
			properties.put("javax.persistence.jdbc.password", password);
			
			/**
			 * get connection from EntityManager to database
			 * */
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("database", properties); 
		    EntityManager em =  emf.createEntityManager();
		    em.getTransaction().begin();
			/**
			 * Creation of GetClientID for getClientId
			 * */
			GetClientID id = new GetClientID();
			/**
			 * Check our directories & files and getData for each
			 * */
			if(pack.isDirectory()){
				String s[] = pack.list();
				for(int j=0;j<s.length;j++){						
					/**
					 * Create an Object with JAXBContext with unmarshalling
					 * */
					if (new File(path,s[j]).isFile()){
					DocListInvoice document = (DocListInvoice)((JAXBContext.newInstance(DocListInvoice.class)).createUnmarshaller()).unmarshal(new File(path,s[j]));
					
						for(int x=0;x<=document.getDocumentInvoice().size()-1;x++){
								double tempSum = 0;
								double temp = 0;
								
								String parse = document.getDocumentInvoice().get(x).getInvoiceHeader().get(0).getInvoiceNumber();
								
								
								String invoiceNumber = "CTT"+parse.substring(3, parse.length()); 
								
								/**
								 * SQL query creation (insertion)
								 * */
								Query headerQuery = em.createNativeQuery("EXECUTE PROCEDURE EPRORDERSOUTINV_INSERT('"
										+document.getDocumentInvoice().get(x).getInvoiceHeader().get(0).getInvoiceDate()+"', "
										+id.getClientId(path+s[j], x, em)+", 0, NULL, '"
										+document.getDocumentInvoice().get(x).getTaxInvoice().get(0).getTaxNumber()+"', NULL, NULL, NULL, NULL, NULL, '"
										+invoiceNumber+"', NULL, NULL, NULL, NULL, NULL, 0)");

								/**
								 * process the output id
								 * */
								int outputId = (Integer)headerQuery.getSingleResult();
								
								for(int i=0;i<=document.getDocumentInvoice().get(x).getInvoiceLines().size()-1;i++){
											/**
											 * SQL query for details creation (insertion)
											 * */
											for(int z=0;z<=document.getDocumentInvoice().get(x).getInvoiceLines().get(i).getLines().size()-1;z++){
												/*String sqlD ="EXECUTE PROCEDURE EPRORDERSOUTINVDET_INSERT_CODE("+val+", "
														+document.getDocumentInvoice().get(x).getInvoiceLines().get(i).getLines().get(z).getEAN()+", "+"Null"+", "
														+document.getDocumentInvoice().get(x).getInvoiceLines().get(i).getLines().get(z).getInvoiceQuantity()+", Null)";
												stm.executeUpdate(sqlD);*/
												Query detailsQuery = em.createNativeQuery("EXECUTE PROCEDURE EPRORDERSOUTINVDET_INSERT_CODE("+outputId+", "
														+document.getDocumentInvoice().get(x).getInvoiceLines().get(i).getLines().get(z).getEAN()+", "+"Null"+", "
														+document.getDocumentInvoice().get(x).getInvoiceLines().get(i).getLines().get(z).getInvoiceQuantity()+", Null)");
												/**
												*executing our query
												  */
												detailsQuery.executeUpdate();
												temp = Double.parseDouble(document.getDocumentInvoice().get(x).getInvoiceLines().get(i).getLines().get(z).getNetAmount());
												System.out.println(temp);
												tempSum+=temp;
											}
	
								}
								System.out.println("");
								System.out.println("______________Total report for "+s[j]+"______________");
								System.out.println("Header id: "+outputId);
								System.out.println("Details inserted: "+document.getDocumentInvoice().get(x).getInvoiceLines().size());
								System.out.println("totalTempSum: "+tempSum);
								System.out.println("");
						}
					}
				}}
			em.getTransaction().commit();
			}
			catch(Exception ex){
				ex.getStackTrace();
			}
	}
}
