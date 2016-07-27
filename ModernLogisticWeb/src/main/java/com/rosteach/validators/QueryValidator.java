package com.rosteach.validators;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.rosteach.entities.ClientRequestDetails;
import com.rosteach.entities.Order_info;
import com.rosteach.entities.ResultLog;
import com.rosteach.entities.SPROutcomeInvoice;
import com.rosteach.entities.SPROutcomeInvoiceDetails;
import com.rosteach.util.JsonMapperUtil;
import com.rosteach.util.QueryManagerUtil;

public class QueryValidator{
	public List<ResultLog> checkAllForXMLGen(String request, EntityManager fireBird, EntityManager mySQL){
		/**
		 * create previously ResultLog collection
		 */
		List<ResultLog> resLog = new LinkedList<ResultLog>();
		try {
			/**
			 * map our request from json
			 */
			List<SPROutcomeInvoice> inputInvoices = JsonMapperUtil.getInputInvoices(request);

			for(SPROutcomeInvoice invoice:inputInvoices){
				int exPosition = 0;
				int goodsid = 0;
				int mainclientid= 0;
				int clientid = invoice.getCLIENTID();
				int id = invoice.getID();
				int temp = 0;
				try{
					id=invoice.getID();
					List<SPROutcomeInvoiceDetails> details = QueryManagerUtil.getOutcomeDetailsByID(invoice.getID(), fireBird);
					
					/**
					 * firstly checkout invoices(firebird) and orders(mysql) parameters
					 */
					exPosition+=1;//1
					int orderid = QueryManagerUtil.getOrderIdBySPRoiID(invoice.getID(), fireBird);
					
					exPosition+=1;//2
					String ordernumber = QueryManagerUtil.getOrderNumberBySPRoiID(invoice.getID(), fireBird);
					
					exPosition+=1;//3
					java.sql.Date orderdate = QueryManagerUtil.getOrderDateBySPRoiID(invoice.getID(), fireBird);
					
					exPosition+=1;//4
					String recipient = QueryManagerUtil.getRecipientByClientID(invoice.getCLIENTID(), fireBird);
					
					exPosition+=1;//5
					Order_info order_info = QueryManagerUtil.getOrder_infoByID(orderid, mySQL);
					
					mainclientid = order_info.getOrder_main_clientId();
					
					exPosition+=1;//6
					temp=orderid;
					List<ClientRequestDetails> ordersList = QueryManagerUtil.getOrdersDetailsByID(orderid, fireBird);
					
					exPosition+=1;//7
					String campaignNumber = QueryManagerUtil.getCampaignNumberByClientID(invoice.getCLIENTID(), fireBird);
					
					for(SPROutcomeInvoiceDetails detail: details){
						exPosition+=1;//8
						goodsid = detail.getGOODSID();
						double orderedQuantity = QueryManagerUtil.getOrderQuantityByParam(orderid, detail.getGOODSID(), fireBird);
						
						exPosition+=1;//9
						String productidBuyer = QueryManagerUtil.getProductIdBuyerByParam(detail.getGOODSID(),mainclientid,fireBird);
						fireBird.clear();
					}
					mySQL.clear();
				}
				catch(NoResultException ex){
					ResultLog res = new ResultLog();
					res.setTotalInfo("Ошибка по позиции: "+id+"!");
					System.out.println("-------------"+exPosition);
					res.setTotalname(getFullMessage(exPosition,clientid,goodsid,id,temp,mainclientid));
					System.out.println("---------------totalName-----------------"+res.getTotalname());
					resLog.add(res);
				}
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resLog;
	} 
	
	public static String getFullMessage(int position, int clientid, int goodsid, int id,int orderid,int mainclientid){
	    String res = "";
	    if(position==0){
	    	res = "Товаров по расходной накладной c кодом "+id+"не обнаружено!!!";
	    }
	    else if(position==1){
			res = "Отсутствует Код РН в таблице Заказов; Код РН:"+id;
		}
	    else if(position==2){
			res = "Отсутствует поле 'Особенности заказа' в заявках от клиента; Код РН: "+id;
		}
	    else if(position==4){
			res = "Отсутствует поле 'Почт. индекс' в таблице Клиентов; Код клиента: "+clientid;
		}
	    else if(position==5){
			res = "Заказ не загружен через систему (Код Заявки от клиентов: "+orderid+")! Обратитесь к администратору системы!";
		}
	    else if(position==6){
			res = "Заявка, или ее товары отсутвую; Код заявки: "+orderid+"!";
		}
	    else if(position==7){
			res = "Отсутствует поле 'Договор', в таблице Клиенты; Код клиента: "+clientid+"!";
		}
	    else if(position==8){
			res = "Отсутствует поле 'Количество', в таблице Заявки от клиентов(Товары); Код клиента: "+clientid+"; Товар:"+goodsid+" !";
		}
	    else if(position==9){
			res = "Отсутствует артикул покупателя! Клиент: "+mainclientid+"; Товар: "+goodsid;
		}
	    
	    return res;
	}
}
