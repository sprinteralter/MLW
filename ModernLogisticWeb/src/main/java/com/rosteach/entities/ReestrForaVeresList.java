package com.rosteach.entities;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.rosteach.entities.ReestrForaVeres;

@XmlRootElement(name="reestrList")
public class ReestrForaVeresList {
	private List<ReestrForaVeres> reestrList;

	 @XmlElement(name="reestr")
	 public List<ReestrForaVeres> getReestrList() {
		return reestrList;
	 }

	 public void setReestrList(List<ReestrForaVeres> reestrList) {
		this.reestrList = reestrList;
	 }  
}
