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
			//check if order exist
			Query check = em.createNativeQuery("select id from SPRORDERSOUTINV (1,'"+ord.getDATE()+"',Null,0,Null,0) where comment2='"+ord.getNUMBER()+"'");
			int exOrder = check.getResultList().size();
			if (exOrder > 0){
				result = "Номер накладной " +ord.getNUMBER()+ " уже есть в базе (файл: "+fileName+")";
			return result;
			}
			//������� ������
			Query client = em.createNativeQuery("select id from SPRCLIENT (Null,Null,Null,Null,0) where postcode='"+ord.getHEAD().getDELIVERYPLACE()+"'");
			int clientCode = 0;
			try{
				clientCode= (Integer)client.getResultList().get(0);
			} catch(IndexOutOfBoundsException e) {
				return new String("Нет привязки GLN " +ord.getHEAD().getDELIVERYPLACE()+ " (файл: "+fileName+")");
			}
			
			Query q = em.createNativeQuery("EXECUTE PROCEDURE EPRORDERSOUTINV_INSERT('"
										+ord.getDATE()+"', "
										+clientCode +","	//clientid
												+ " 0, "						//storeid
												+ "NULL, '"						 //outcomeinvociidset
										+ord.getDELIVERYDATE()+ 			//comment
												"', NULL,"						//beepreslinkid
												+ "NULL,"						//beepressstore
												+ "NULL,"						//termdate
												+ "1,"//paytypedid
												+ "NULL, '"						//comment1
										+ord.getNUMBER()+"', "		//comment2
												+ "NULL, NULL, NULL, NULL, NULL, 0)"); 
			//�������� ����� �� ���������
			int id = (Integer)q.getResultList().get(0);
			
			
			//������� ������ ������� ������� � ������
			List<POSITION> positions = ord.getHEAD().getPOSITION();
			
			//��������� ������ ������� �� ������ � ������
			for (POSITION p : positions){
				try{
				tovar = p;
				
				//���� �������� �� ���������
				Query goodsID = em.createNativeQuery("select goodsid from prodlink where  prodcode = '"+p.getPRODUCTIDBUYER()+"' and clientid = '11426'"); //clientid = 11426 and
				
				@SuppressWarnings("unchecked")
				List<Integer> codes = goodsID.getResultList();
				int gid;
				try{
					 gid = codes.get(0);	
				} catch(IndexOutOfBoundsException e){
					return new String("НЕ ТОТ ФАЙЛ?");
					
				}
				
				//������� ��������� �� ���� ��������
				Query mID = em.createNativeQuery("select first(1) case g.CLASS3 when 'S' then 4 else 1 end edizm from prodlink p, goods g where g.id=p.goodsid and p.clientid = 11426 and  p.prodcode = '"+p.getPRODUCTIDBUYER()+"'");
				int mesID = (Integer) mID.getResultList().get(0);
				
				
			 Query qp = em.createNativeQuery("EXECUTE PROCEDURE EPRORDERSOUTINVDET_INSERT("+id+","+gid+","+mesID+",'"+p.getORDEREDQUANTITY()+"',null"+")");
				qp.executeUpdate();
				}catch(IndexOutOfBoundsException ind){
					result = result + "error. GLN = "+clientCode+" in "+fileName+",";	
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
