package com.rosteach.DAO.order_info;

import java.util.Date;

import com.rosteach.entities.Order_info;


public interface Order_infoDAO {

	void createOrder(long order_kod, String order_number, long buyer, Date order_date, String user, int order_main_clientId, long suplier, long deliveryplace, long sender, long recipient);
	public Order_info getOrder_info_byKod(long order_kod);
	void persistOrder(Order_info orderInfo);
}
