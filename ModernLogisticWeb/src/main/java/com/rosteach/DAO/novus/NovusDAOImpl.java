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
import com.rosteach.xml.novus.DESADV.HEAD.PACKINGSEQUENCE.POSITION;

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
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("database", properties); 
	    EntityManager em =  emf.createEntityManager();
		
	    String fileName = "";
		String result = "";
	
	    
	    JAXBContext jc;
		Unmarshaller u;
		DESADV novusXML = new DESADV();
		File[] files = new File(path).listFiles();
		POSITION tovar = null;
		
		em.getTransaction().begin();
		
		//Перебираем все файлы в каталоге <String path>
		for (File f : files){
			fileName = f.getName();
			
			try{	
			try {
				jc = JAXBContext.newInstance(DESADV.class);
				u = jc.createUnmarshaller();
				novusXML = (DESADV) u.unmarshal(f);
			} catch (JAXBException e) {
				e.printStackTrace();
				}
			
			//создаем заявку
			Query q = em.createNativeQuery("EXECUTE PROCEDURE EPRORDERSOUTINV_INSERT('"
										+novusXML.getDATE()+"', '"
										+"11426" +"',"	//clientid
												+ " 0, "						//storeid
												+ "NULL, '"						 //outcomeinvociidset
										+novusXML.getDELIVERYDATE()+ 			//comment
												"', NULL, "						//beepreslinkid
												+ "NULL, "						//beepressstore
												+ "NULL,'"						//termdate
												+ novusXML.getSHIPMENTS() +"', "//paytypedid
												+ "NULL, '"						//comment1
										+novusXML.getORDERNUMBER()+"', "		//comment2
												+ "NULL, NULL, NULL, NULL, NULL, 0)"); 
			//получаем ответ от процедуры
			int id = (Integer)q.getSingleResult();
			
			//создаем массив позиций товаров в заявке
			List<POSITION> positions = novusXML.getHEAD().getPACKINGSEQUENCE().getPOSITION();
			
			//добавляем каждую позицию из масива в заявку
			for (POSITION p : positions){
				try{
				tovar = p;
				
				//АйДи продукта по штрихкоду
				Query goodsID = em.createNativeQuery("select goodsid from prodlink where  prodcode = '"+p.getPRODUCT()+"'"); //clientid = 11426 and
				
				@SuppressWarnings("unchecked")
				List<Integer> codes = goodsID.getResultList();
				int gid = codes.get(0);	
				
				//еденицу измерения по АйДи продукта
				Query mID = em.createNativeQuery("select measureid from goods where id ="+gid);
				short mesID = (Short) mID.getResultList().get(0);
				
				
			 Query qp = em.createNativeQuery("EXECUTE PROCEDURE EPRORDERSOUTINVDET_INSERT("+id+","+gid+","+mesID+",'"+p.getORDEREDQUANTITY()+"',null"+")");
				qp.executeUpdate();
				}catch(IndexOutOfBoundsException ind){
					result = result + "НЕ НАЙДЕН (ШТРИХКОД = "+tovar.getPRODUCT()+") ТОВАРА "+tovar.getDESCRIPTION()+" В (ФАЙЛЕ= "+fileName+") ,";
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
