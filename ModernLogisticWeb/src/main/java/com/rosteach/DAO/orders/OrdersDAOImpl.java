package com.rosteach.DAO.orders;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rosteach.DAO.order_info.Order_infoDAO;
import com.rosteach.DAO.orders.ORDER.HEAD.POSITION;
import com.rosteach.services.EdiService;


@Repository
public class OrdersDAOImpl implements OrdersDAO {
	
	int main_id;
	
	@Autowired
	public Order_infoDAO ord_info;
	
	@Override
	public String Insert(String database, String name, String password, String path, int mainID) throws SQLException, InstantiationException, IllegalAccessException {
		main_id = mainID;
		
		EdiService es = new EdiService();
		es.setConnection(database, name, password);
		File[] files = es.getXMLlist(path);
		ORDER ord = new ORDER();
		for (File f : files){
			
			//unmarshal file
			ord = (ORDER) es.unmarshal(f, ord);
			
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
			
			//add buyer and orderID to mysql
			ord_info.createOrder(orderID, ord.getNUMBER(), ord.getHEAD().getBUYER(), ord.getDATE().toGregorianCalendar().getTime(), name, main_id, ord.getHEAD().getSUPPLIER(), ord.getHEAD().getDELIVERYPLACE(), ord.getHEAD().getSENDER(), ord.getHEAD().getRECIPIENT());

		    
			
			//get list of order goods position
			List<POSITION> positions = ord.getHEAD().getPOSITION();
			
			for (POSITION p : positions){
				 	
				 	//get goods id from position where 13480 = clientID
					int goodsID = es.goodsID(p.getPRODUCTIDBUYER(), main_id);
					//get measure ID
					short mesID;
					try{
					mesID = (short) es.getMeasureid(main_id, p.getPRODUCTIDBUYER());
					} catch(IndexOutOfBoundsException e){
						return "Нет привязки артикула (покупатель) " + p.getPRODUCTIDBUYER() + " к товару "+ p.getCHARACTERISTIC().getDESCRIPTION();
					}
					
					if (main_id == 13036)
						mesID=2;
					
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
