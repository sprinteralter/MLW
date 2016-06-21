package com.rosteach.entities;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReestrForaVeres implements Serializable{
	private static final long serialVersionUID = 1L;
	private String regnumber;
	private String clientsName;
	private String clientadresslocation;
	private String comment1;
	private String foundation;
	private double mainsumm;
	
	public ReestrForaVeres(){
	}
	public ReestrForaVeres(String regnumber, String clientsName, String clientadresslocation,
			String comment1, String foundation, double mainsumm) {
		this.regnumber = regnumber;
		this.clientsName = clientsName;
		this.clientadresslocation = clientadresslocation;
		this.comment1 = comment1;
		this.foundation = foundation;
		this.mainsumm = mainsumm;
	}
	
	public String getRegnumber() {
		return regnumber;
	}
	@XmlElement
	public void setRegnumber(String regnumber) {
		this.regnumber = regnumber;
	}
	public String getClientsName() {
		return clientsName;
	}
	@XmlElement
	public void setClientsName(String clientsName) {
		this.clientsName = clientsName;
	}
	public String getClientadresslocation() {
		return clientadresslocation;
	}
	@XmlElement
	public void setClientadresslocation(String clientadresslocation) {
		this.clientadresslocation = clientadresslocation;
	}
	public String getComment1() {
		return comment1;
	}
	@XmlElement
	public void setComment1(String comment1) {
		this.comment1 = comment1;
	}
	public String getFoundation() {
		return foundation;
	}
	@XmlElement
	public void setFoundation(String foundation) {
		this.foundation = foundation;
	}
	public double getMainsumm() {
		return mainsumm;
	}
	@XmlElement
	public void setMainsumm(double mainsumm) {
		this.mainsumm = mainsumm;
	}
}
