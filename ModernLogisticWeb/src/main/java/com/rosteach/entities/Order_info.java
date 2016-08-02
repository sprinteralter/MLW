package com.rosteach.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Order_info {
	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long id;
		private long order_kod;
		private long buyer;
		private String desadv_user;
		private Date desadv_date;
		private String ordersp_user;
		private Date ordersp_date;
		private Date order_date;
		private String order_number;
		private String order_user;
		private Integer order_main_clientId;
		private long order_suplier;
		private long order_deliveryplace;
		private long order_sender;
		private long order_recipient;
		
		
public Order_info(){
			
		}
		
		
		public long getOrder_recipient() {
	return order_recipient;
}


public void setOrder_recipient(long order_recipient) {
	this.order_recipient = order_recipient;
}


		public long getOrder_suplier() {
			return order_suplier;
		}

		public void setOrder_suplier(long order_suplier) {
			this.order_suplier = order_suplier;
		}

		

		public long getOrder_deliveryplace() {
			return order_deliveryplace;
		}

		public void setOrder_deliveryplace(long order_deliveryplace) {
			this.order_deliveryplace = order_deliveryplace;
		}

		public long getOrder_sender() {
			return order_sender;
		}

		public void setOrder_sender(long order_sender) {
			this.order_sender = order_sender;
		}

		
		
		
		public long getId() {
			return id;
		}
		public long getOrder_kod() {
			return order_kod;
		}
		public void setOrder_kod(long order_kod) {
			this.order_kod = order_kod;
		}
		public long getBuyer() {
			return buyer;
		}
		public void setBuyer(long buyer) {
			this.buyer = buyer;
		}
		public Date getDesadv_date() {
			return desadv_date;
		}
		public void setDesadv_date(Date desadv) {
			this.desadv_date = desadv;
		}
		public Date getOrdersp_date() {
			return ordersp_date;
		}
		public void setOrdersp_date(Date ordersp) {
			this.ordersp_date = ordersp;
		}

		public String getDesadv_user() {
			return desadv_user;
		}

		public void setDesadv_user(String desadv_user) {
			this.desadv_user = desadv_user;
		}

		public String getOrdersp_user() {
			return ordersp_user;
		}

		public void setOrdersp_user(String ordersp_user) {
			this.ordersp_user = ordersp_user;
		}

		public String getOrder_user() {
			return order_user;
		}

		public void setOrder_user(String order_user) {
			this.order_user = order_user;
		}

		public Date getOrder_date() {
			return order_date;
		}

		public void setOrder_date(Date order_date) {
			this.order_date = order_date;
		}

		public String getOrder_number() {
			return order_number;
		}

		public void setOrder_number(String order_number) {
			this.order_number = order_number;
		}

		public int getOrder_main_clientId() {
			return order_main_clientId;
		}

		public void setOrder_main_clientId(int order_main_clientId) {
			this.order_main_clientId = order_main_clientId;
		}
}
