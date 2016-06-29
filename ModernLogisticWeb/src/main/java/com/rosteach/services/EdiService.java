package com.rosteach.services;

import java.io.File;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.XMLGregorianCalendar;


public class EdiService {
	
	private EntityManager em;
	
	//create connection
	public void setConnection(String database, String name, String password){
		Map<String,String> properties = new HashMap<String,String>();
		properties.put("javax.persistence.jdbc.url", database);
		properties.put("javax.persistence.jdbc.user", name);
		properties.put("javax.persistence.jdbc.password", password);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("database", properties); 
	     em =  emf.createEntityManager();
	     em.getTransaction().begin();
		
	}
	
	//get list files from path
	public File[] getXMLlist(String path){
		File[] files = new File(path).listFiles();	
		return files;
	}
	
	public String novusOrderCheck(XMLGregorianCalendar date, String number, String filename){
		Query check = em.createNativeQuery("select id from SPRORDERSOUTINV (1,'"+date+"',Null,0,Null,0) where comment2='"+number+"'");
		int exOrder = check.getResultList().size();
		String result = null;
		if (exOrder > 0){
			result = "Номер накладной " +number+ " уже есть в базе (файл: "+filename+")";
		return result;
		}
		return result;
		
	}
	
	public int getDeliveryNovusGLN(long GLN, String columnGLN){
		Query client = em.createNativeQuery("select id from SPRCLIENT (Null,Null,Null,Null,0) where "+columnGLN+"='"+GLN+"'");
		int clientCode = 0;
		
		try{
			clientCode= (Integer)client.getResultList().get(0);
		} catch(IndexOutOfBoundsException e) {
			return clientCode;
		}
		
		return clientCode;
	}
	
	public int createOrder(XMLGregorianCalendar date, long clientID, XMLGregorianCalendar deliveryDate, String orderNum ){
		
		Query q = em.createNativeQuery("EXECUTE PROCEDURE EPRORDERSOUTINV_INSERT('"
				+date+"', "
				+clientID +","	//clientid
						+ " 0, "						//storeid
						+ "NULL, '"						 //outcomeinvociidset
				+deliveryDate+ 			//comment
						"', NULL,"						//beepreslinkid
						+ "NULL,"						//beepressstore
						+ "NULL,"						//termdate
						+ "1,"//paytypedid
						+ "NULL, '"						//comment1
				+orderNum+"', "		//comment2
						+ "NULL, NULL, NULL, NULL, NULL, 0)"); 
				int id = (Integer)q.getResultList().get(0);
				return id;
		
	}
	
	public int goodsID(int buyer, int client){
		Query goodsID = em.createNativeQuery("select goodsid from prodlink where  prodcode = '"+buyer+"' and clientid = '"+client+"'"); //clientid = 11426 and
		System.out.println(buyer + " BUYERRRRRR" + client + "CLIENTTTTTTTTTTTTTTTTT");
		List<Integer> codes = goodsID.getResultList();
		int gid;
		try{
			 gid = codes.get(0);	
		} catch(IndexOutOfBoundsException e){
			return 0;
			
		}
		return gid;
	}
	
	public void addPosition(int id, int goodsID, short measureID, float orderQuantity){
		try{
		Query qp = em.createNativeQuery("EXECUTE PROCEDURE EPRORDERSOUTINVDET_INSERT("+id+","+goodsID+","+measureID+",'"+orderQuantity+"',null"+")");
		 qp.executeUpdate();
		}
		catch(TransactionRequiredException e){
			e.printStackTrace();
		}
		
	}
	
	public int getMeasureid(int clientID, int productBuyer){
		Query mID = em.createNativeQuery("select first(1) case g.CLASS3 when 'S' then 4 else 1 end edizm from prodlink p, goods g where g.id=p.goodsid and p.clientid = "+clientID+" and  p.prodcode = '"+productBuyer+"'");//"select measureid from goods where id ="+goodsID);
		int mesID = (Integer) mID.getResultList().get(0);
		return mesID;
	}
	
	public void commit(){
		em.getTransaction().commit();
	}
	
	
	JAXBContext jc;
	Unmarshaller u;
	
	public Object test(File f ,Object o) throws InstantiationException, IllegalAccessException{
		
		Class ord = o.getClass(); //Class.forName(o.getClass().getName());
								//Class supercalass = Ord.getSuperclass();
		Object ORDER = ord.newInstance();
		try {
			jc = JAXBContext.newInstance(ord);
			u = jc.createUnmarshaller();
			ORDER =  u.unmarshal(f);
		} catch (JAXBException e) {
			e.printStackTrace();
			}
		return ORDER;
	}
	
}
