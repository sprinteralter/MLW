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
		
		public Order_info(){
			
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
		
		
		
}
