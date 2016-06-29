package com.rosteach.DAO.lktrans;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Repository;

import com.rosteach.services.EdiService;
import com.rosteach.xml.lktrans.ORDER;
import com.rosteach.xml.lktrans.ORDER.HEAD.POSITION;

@Repository
public class LkTransDAOImpl implements LktransDAO {

	@Override
	public String Insert(String database, String name, String password, String path) throws SQLException, InstantiationException, IllegalAccessException {

		EdiService es = new EdiService();
		es.setConnection(database, name, password);
		File[] files = es.getXMLlist(path);
		ORDER ord = new ORDER();
		for (File f : files){
			
			//unmarshal file
			ord = (ORDER) es.test(f, ord);
			
			//check if order exist
			String checkOrderifExist = es.novusOrderCheck(ord.getDATE(), ord.getNUMBER(), f.getName());
			if (checkOrderifExist != null){
				return checkOrderifExist;
			}
			
			//get clientID (postcode = GLN field)
			int clientID = es.getDeliveryNovusGLN(ord.getHEAD().getDELIVERYPLACE(), "postcode");
			if (clientID == 0){
				return new String("Нет привязки GLN " +ord.getHEAD().getDELIVERYPLACE()+ " (файл: "+f.getName()+")");
			}
			
			//create order and get returned Order ID
			int orderID = es.createOrder(ord.getDATE(), clientID, ord.getDELIVERYDATE(), ord.getNUMBER());
			
			//get list of order goods position
			List<POSITION> positions = ord.getHEAD().getPOSITION();
			
			for (POSITION p : positions){
				 	
				 	//get goods id from position where 13480 = clientID
					int goodsID = es.goodsID(p.getPRODUCTIDBUYER(), 13480);
					
					//get measure ID
					short mesID = (short) es.getMeasureid(13480, p.getPRODUCTIDBUYER());
					
					//add position to order
					es.addPosition(orderID, goodsID, mesID, p.getORDEREDQUANTITY());
			 }
			
			
			
		}
		es.commit();
		try {
			FileUtils.cleanDirectory(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String("Добавлено "+files.length + " заявок");
		
	}
}
