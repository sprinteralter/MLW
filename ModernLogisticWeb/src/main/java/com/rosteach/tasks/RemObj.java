package com.rosteach.tasks;

public class RemObj {
public RemObj(){}
public String date;
public String prodCode;
public String col;
public String sumPrice;
public String sklad;
public RemObj(String sklad, String date, String prodCode, String col, String sumPrice) {
	super();
	this.sklad = sklad;
	this.date = date;
	this.prodCode = prodCode;
	this.col = col;
	this.sumPrice = sumPrice;
}
public String getDate() {
	return date;
}

public String getSklad() {
	return sklad;
}
public void setSklad(String sklad) {
	this.sklad = sklad;
}
public void setDate(String date) {
	this.date = date;
}
public String getProdCode() {
	return prodCode;
}
public void setProdCode(String prodCode) {
	this.prodCode = prodCode;
}
public String getCol() {
	return col;
}
public void setCol(String col) {
	this.col = col;
}
public String getSumPrice() {
	return sumPrice;
}
public void setSumPrice(String sumPrice) {
	this.sumPrice = sumPrice;
}

}


class Client{
	public String clientCode;
	public String clientName;
	public String clientAdr;
	public String location;
	
	
	
	public Client(String clientCode, String clientName, String clientAdr, String location) {
		super();
		this.clientCode = clientCode;
		this.clientName = clientName;
		this.clientAdr = clientAdr;
		this.location = location;
	}
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientAdr() {
		return clientAdr;
	}
	public void setClientAdr(String clientAdr) {
		this.clientAdr = clientAdr;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
	
}