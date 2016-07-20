package com.rosteach.DAO.socrat;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.rosteach.DAO.order_info.Order_infoDAO;
import com.rosteach.services.EdiService;
import com.rosteach.xml.novus.ORDER;
import com.rosteach.xml.novus.ORDER.HEAD.POSITION;

public class SocratDAOImpl implements SocratDAO{

	@Autowired
	public Order_infoDAO ord_info;
	
	@Override
	public String Insert(String database, String name, String password, String path)
			throws SQLException, InstantiationException, IllegalAccessException {

		EdiService es = new EdiService();
		es.setConnection(database, name, password);
		File[] files = es.getXMLlist(path);
		ORDER ord = new ORDER();
		for (File f : files){
			
			//unmarshal file
			ord = (ORDER) es.unmarshal(f, ord);
			
			//check if order exist
			String checkOrderifExist = es.novusOrderCheck(ord.getDATE(), String.valueOf(ord.getNUMBER()), f.getName());
			if (checkOrderifExist != null){
				return checkOrderifExist;
			}
			
			//get clientID (postcode = GLN field)
			int clientID = es.getDeliveryNovusGLN(ord.getHEAD().getDELIVERYPLACE(), "postcode");
			if (clientID == 0){
				return new String("Нет привязки GLN " +ord.getHEAD().getDELIVERYPLACE()+ " (файл: "+f.getName()+")");
			}
			
			//create order and get returned Order ID
			int orderID = es.createOrder(ord.getDATE(), clientID, ord.getDELIVERYDATE(), String.valueOf(ord.getNUMBER()));
			ord_info.createOrder(orderID, String.valueOf(ord.getNUMBER()), ord.getHEAD().getBUYER() , ord.getDATE().toGregorianCalendar().getTime(), name);

			//get list of order goods position
			List<POSITION> positions = ord.getHEAD().getPOSITION();
			
			for (POSITION p : positions){
				 	
				 	//get goods id from position where 13480 = clientID
					int goodsID = es.goodsID(p.getPRODUCTIDBUYER(), 57245);
					//get measure ID
					short mesID;
					try{
					mesID = (short) es.getMeasureid(57245, p.getPRODUCTIDBUYER());
					} catch(IndexOutOfBoundsException e){
						return "Нет привязки артикула (покупатель) " + p.getPRODUCTIDBUYER() + " к товару "+ p.getCHARACTERISTIC().getDESCRIPTION();
					}
					
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
