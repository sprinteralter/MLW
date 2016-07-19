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
		private String desadv_number;
		private Date desadv_date;
		private String ordersp_number;
		private Date ordersp_date;
		private Date order_date;
		private String order_number;
		
		public Order_info(){
			order_kod=0;
			buyer=0;
			desadv_number=null;
			desadv_date = null;
			ordersp_number = null;
			ordersp_date = null;
			order_date = null;
			order_number = null;
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

		public String getDesadv_number() {
			return desadv_number;
		}

		public void setDesadv_number(String desadv_number) {
			this.desadv_number = desadv_number;
		}

		public String getOrdersp_number() {
			return ordersp_number;
		}

		public void setOrdersp_number(String ordersp_number) {
			this.ordersp_number = ordersp_number;
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
