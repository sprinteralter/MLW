/**
 * @author Pavlenko R. --Sprinter K-- 
 * Java XMLBinding
 * */
package com.rosteach.DAO;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.XMLGregorianCalendar;

import com.rosteach.xml.DocListInvoice;

import java.sql.*;
import java.time.LocalDate;
import java.util.Properties;

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
			Properties prop = new Properties();
			prop.setProperty("user", login);
			prop.setProperty("password", password);
			prop.setProperty("encoding", "win1251");
			/**
			 * get connection to database method getConnection(url,user,password,encoding)
			 * */
			Class.forName("org.firebirdsql.jdbc.FBDriver").newInstance();
			Connection conn= DriverManager.getConnection(dataBase,prop);
			System.out.println("Connection for Header execution Start with success!!!");
			/**
			 * statement creation
			 * */
			Statement stm = conn.createStatement();
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
								
								
								/**
								 * SQL query creation (insertion)
								 * */
								String sqlH ="EXECUTE PROCEDURE EPRORDERSOUTINV_INSERT('"
											+document.getDocumentInvoice().get(x).getInvoiceHeader().get(0).getInvoiceDate()+"', "
											+id.getClientId(path+s[j], x, stm)+", 0, NULL, '"
											+document.getDocumentInvoice().get(x).getTaxInvoice().get(0).getTaxNumber()+"', NULL, NULL, NULL, NULL, NULL, '"
											+document.getDocumentInvoice().get(x).getInvoiceHeader().get(0).getInvoiceNumber()+"', NULL, NULL, NULL, NULL, NULL, 0)";
								/**
								 * process the result set
								 * */
								ResultSet rsHeader = stm.executeQuery(sqlH);
								String val = "";
									while(rsHeader.next()) {
										val = rsHeader.getString("ID");
									}
								/**
								 * Closing our statement and connection!
								 * */
								
								System.out.println("Connection for Header execution End with success!!! Output ID is: "+val);
								for(int i=0;i<=document.getDocumentInvoice().get(x).getInvoiceLines().size()-1;i++){
											/**
											 * SQL query creation (insertion)
											 * */
											for(int z=0;z<=document.getDocumentInvoice().get(x).getInvoiceLines().get(i).getLines().size()-1;z++){
												String sqlD ="EXECUTE PROCEDURE EPRORDERSOUTINVDET_INSERT_CODE("+val+", "
														+document.getDocumentInvoice().get(x).getInvoiceLines().get(i).getLines().get(z).getEAN()+", "+"Null"+", "
														+document.getDocumentInvoice().get(x).getInvoiceLines().get(i).getLines().get(z).getInvoiceQuantity()+", Null)";
												stm.executeUpdate(sqlD);
												
												temp = Double.parseDouble(document.getDocumentInvoice().get(x).getInvoiceLines().get(i).getLines().get(z).getNetAmount());
												System.out.println(temp);
												tempSum+=temp;
											}
	
								}
								System.out.println("");
								System.out.println("______________Total report for "+s[j]+"______________");
								System.out.println("Header id: "+val);
								System.out.println("Details inserted: "+document.getDocumentInvoice().get(x).getInvoiceLines().size());
								System.out.println("totalTempSum: "+tempSum);
								System.out.println("");
						}
					}
				}}
				stm.close();
				conn.close();		
			}
			catch(Exception ex){
				ex.getStackTrace();
			}
	}
}
