package com.rosteach.entities;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="sverkaList")
public class SverkaPoVigruzkeBoxList {
	private List<SverkaPoVigruzkeBox> sverkaList;

	public List<SverkaPoVigruzkeBox> getSverkaList() {
		return sverkaList;
	}
	@XmlElement
	public void setSverkaList(List<SverkaPoVigruzkeBox> sverkaList) {
		this.sverkaList = sverkaList;
	}
}
