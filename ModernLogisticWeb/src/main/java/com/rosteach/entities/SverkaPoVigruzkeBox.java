package com.rosteach.entities;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SverkaPoVigruzkeBox implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String clientsName;
	private double Order;
	private double Shipment;

	public SverkaPoVigruzkeBox(){
	}
	public SverkaPoVigruzkeBox(double O, double S, String name){
		this.clientsName=name;
		this.Order=O;
		this.Shipment=S;
	}
	
	public double getOrder() {
		return Order;
	}
	@XmlElement
	public void setOrder(double order) {
		Order = order;
	}
	public double getShipment() {
		return Shipment;
	}
	@XmlElement
	public void setShipment(double shipment) {
		Shipment = shipment;
	}
	public String getClientsName() {
		return clientsName;
	}
	@XmlElement
	public void setClientsName(String clientsName) {
		this.clientsName = clientsName;
	}
}
