package com.rosteach.entities;

public class DOCwithName {
	private String name;
	private Object obj;
	
	public DOCwithName(){}

	public DOCwithName(String name, Object obj) {
		this.name = name;
		this.obj = obj;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
}
