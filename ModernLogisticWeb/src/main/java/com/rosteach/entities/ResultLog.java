package com.rosteach.entities;

public class ResultLog {
	private String totalInfo; 
	private String totalname;
	private String totaldate;
	private Integer totaldeliveryquantity;
	private Double totaldeliveryprice;
	private Integer totalorderedquantity;
	private Double totalorderedprice;
	
	public ResultLog(){
		
	}

	public String getTotalInfo() {
		return totalInfo;
	}

	public void setTotalInfo(String totalInfo) {
		this.totalInfo = totalInfo;
	}

	public String getTotalname() {
		return totalname;
	}

	public void setTotalname(String totalname) {
		this.totalname = totalname;
	}

	public String getTotaldate() {
		return totaldate;
	}

	public void setTotaldate(String date) {
		this.totaldate = date;
	}

	public Integer getTotaldeliveryquantity() {
		return totaldeliveryquantity;
	}

	public void setTotaldeliveryquantity(Integer totaldeliveryquantity) {
		this.totaldeliveryquantity = totaldeliveryquantity;
	}

	public Double getTotaldeliveryprice() {
		return totaldeliveryprice;
	}

	public void setTotaldeliveryprice(Double totaldeliveryprice) {
		this.totaldeliveryprice = totaldeliveryprice;
	}

	public Integer getTotalorderedquantity() {
		return totalorderedquantity;
	}

	public void setTotalorderedquantity(Integer totalorderedquantity) {
		this.totalorderedquantity = totalorderedquantity;
	}

	public Double getTotalorderedprice() {
		return totalorderedprice;
	}

	public void setTotalorderedprice(Double totalorderedprice) {
		this.totalorderedprice = totalorderedprice;
	}
}
