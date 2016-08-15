package com.rosteach.entities;

public class Links {
	private String docName;
	private String headersdate;
	private String headerstype;
	
	public Links(){
	}

	public Links(String docName, String headersdate, String headerstype) {
		this.docName=docName;
		this.headersdate = headersdate;
		this.headerstype = headerstype;
	}
	
	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getHeadersdate() {
		return headersdate;
	}
	public void setHeadersdate(String headersdate) {
		this.headersdate = headersdate;
	}
	public String getHeaderstype() {
		return headerstype;
	}
	public void setHeaderstype(String headerstype) {
		this.headerstype = headerstype;
	}
}
