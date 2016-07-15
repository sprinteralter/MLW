package com.rosteach.entities;

public class ResultLog {
	private String totalname;
	private String totaldate;
	private String totalbuyer;
	private String totaldeliveryplace;
	private Integer totaldeliveryquantity;
	private Double totaldeliveryprice;
	private Integer totalorderedquantity;
	private Double totalorderedprice;
	
	public ResultLog(){
		
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

	public String getTotalbuyer() {
		return totalbuyer;
	}

	public void setTotalbuyer(String totalbuyer) {
		this.totalbuyer = totalbuyer;
	}

	public String getTotaldeliveryplace() {
		return totaldeliveryplace;
	}

	public void setTotaldeliveryplace(String totaldeliveryplace) {
		this.totaldeliveryplace = totaldeliveryplace;
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
