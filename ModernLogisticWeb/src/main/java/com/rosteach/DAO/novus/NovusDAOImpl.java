package com.rosteach.DAO.novus;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Repository;
import com.rosteach.xml.novus.DESADV;
import com.rosteach.xml.novus.ORDER.HEAD.POSITION;
import com.rosteach.xml.novus.ORDER;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

@Repository
public class NovusDAOImpl implements NovusDAO {
	
	@Override
	public String Insert(String database, String name, String password, String path) throws SQLException{
		
		
		Map<String,String> properties = new HashMap<String,String>();
		properties.put("javax.persistence.jdbc.url", database);
		properties.put("javax.persistence.jdbc.user", name);
		properties.put("javax.persistence.jdbc.password", password);
		System.out.println(database + " " + name + " " + password);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("database", properties); 
	    EntityManager em =  emf.createEntityManager();
		
	    String fileName = "";
		String result = "";
	
	    
	    JAXBContext jc;
		Unmarshaller u;
		//DESADV novusXML = new DESADV();
		
		ORDER ord = new ORDER(); 
		File[] files = new File(path).listFiles();
		POSITION tovar = null;
		
		em.getTransaction().begin();
		
		//���������� ��� ����� � �������� <String path>
		for (File f : files){
			fileName = f.getName();
			
			try{	
			try {
				jc = JAXBContext.newInstance(ORDER.class);
				u = jc.createUnmarshaller();
				ord = (ORDER) u.unmarshal(f);
			} catch (JAXBException e) {
				e.printStackTrace();
				}
			
			//������� ������
			Query client = em.createNativeQuery("select id from SPRCLIENT (Null,Null,Null,Null,0) where postcode='"+ord.getHEAD().getDELIVERYPLACE()+"'");
			int clientCode = (Integer)client.getResultList().get(0);
			
			Query q = em.createNativeQuery("EXECUTE PROCEDURE EPRORDERSOUTINV_INSERT('"
										+ord.getDATE()+"', "
										+clientCode +","	//clientid
												+ " 0, "						//storeid
												+ "NULL, '"						 //outcomeinvociidset
										+ord.getDELIVERYDATE()+ 			//comment
												"', NULL,"						//beepreslinkid
												+ "NULL,"						//beepressstore
												+ "NULL,"						//termdate
												+ "NULL,"//paytypedid
												+ "NULL, '"						//comment1
										+ord.getNUMBER()+"', "		//comment2
												+ "NULL, NULL, NULL, NULL, NULL, 0)"); 
			//�������� ����� �� ���������
			int id = (Integer)q.getResultList().get(0);
			System.out.println(id+"--------------------------------------------------------------------------------------------------");
			
			//������� ������ ������� ������� � ������
			List<POSITION> positions = ord.getHEAD().getPOSITION();
			
			//��������� ������ ������� �� ������ � ������
			for (POSITION p : positions){
				try{
				tovar = p;
				
				//���� �������� �� ���������
				Query goodsID = em.createNativeQuery("select goodsid from prodlink where  prodcode = '"+p.getPRODUCTIDBUYER()+"'");// and clientid ='"+clientCode+"'"); //clientid = 11426 and
				
				@SuppressWarnings("unchecked")
				List<Integer> codes = goodsID.getResultList();
				int gid = codes.get(0);	
				
				//������� ��������� �� ���� ��������
				Query mID = em.createNativeQuery("select measureid from goods where id ="+gid);
				short mesID = (Short) mID.getResultList().get(0);
				
				
			 Query qp = em.createNativeQuery("EXECUTE PROCEDURE EPRORDERSOUTINVDET_INSERT("+id+","+gid+","+mesID+",'"+p.getORDEREDQUANTITY()+"',null"+")");
				qp.executeUpdate();
				}catch(IndexOutOfBoundsException ind){
					result = result + "product code error. Not found = "+tovar.getPRODUCT()+" in "+fileName+",";
					System.out.println(result+") --------------------------------------------------------------------------------------------");				
					em.getTransaction().rollback();
					em.getTransaction().begin();
					break;
					
				}
				
				}
					
			
			}
				catch(TransactionRequiredException e){
					e.printStackTrace();
				}

			}
		em.getTransaction().commit();
		try {
			FileUtils.cleanDirectory(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}   
}
