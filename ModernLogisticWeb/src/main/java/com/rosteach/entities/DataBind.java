package com.rosteach.entities;

public class DataBind {
	private String name;
	private String password;
	private String databaseFrom;
	private String databaseTo;
	private String ids;
	private String startDate;
	private String endDate;
	
	public DataBind(){}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDatabaseFrom() {
		return databaseFrom;
	}

	public void setDatabaseFrom(String databaseFrom) {
		this.databaseFrom = databaseFrom;
	}

	public String getDatabaseTo() {
		return databaseTo;
	}

	public void setDatabaseTo(String databaseTo) {
		this.databaseTo = databaseTo;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
