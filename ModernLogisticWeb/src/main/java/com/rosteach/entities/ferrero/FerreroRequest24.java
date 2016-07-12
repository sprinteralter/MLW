package com.rosteach.entities.ferrero;

import java.math.BigInteger;

public class FerreroRequest24 {
		
	    String AGENTSNAME;
	    String CLIENTSNAMEORD;
	    String ADRESSLOCORD;
	    String PAYTYPENAME;
	    Integer idorder;
	    java.sql.Date DOCDATEORDER;	 
	    java.sql.Date LOADINGDATE;
	    int zakaz = 1;
	    int nextday;
	    String result;
	    
	    
	    public int getZakaz() {
			return zakaz;
		}

		public void setZakaz(int zakaz) {
			this.zakaz = zakaz;
		}

		public int getNextday() {
			return nextday;
		}

		public void setNextday(int nextday) {
			this.nextday = nextday;
		}

		public String getResult() {
			return result;
		}

		public void setResult(String result) {
			this.result = result;
		}

		public java.sql.Date getLOADINGDATE() {
			return LOADINGDATE;
		}

		public void setLOADINGDATE(java.sql.Date lOADINGDATE) {
			LOADINGDATE = lOADINGDATE;
		}



	public String getPAYTYPENAME() {
			return PAYTYPENAME;
		}



		public void setPAYTYPENAME(String pAYTYPENAME) {
			PAYTYPENAME = pAYTYPENAME;
		}



	public Integer getIdorder() {
			return idorder;
		}



		public void setIdorder(Integer idorder) {
			this.idorder = idorder;
		}


		public String getAGENTSNAME() {
			return AGENTSNAME;
		}



		public void setAGENTSNAME(String aGENTSNAME) {
			AGENTSNAME = aGENTSNAME;
		}


		public String getCLIENTSNAMEORD() {
			return CLIENTSNAMEORD;
		}



		public void setCLIENTSNAMEORD(String cLIENTSNAMEORD) {
			CLIENTSNAMEORD = cLIENTSNAMEORD;
		}



		public String getADRESSLOCORD() {
			return ADRESSLOCORD;
		}



		public void setADRESSLOCORD(String aDRESSLOCORD) {
			ADRESSLOCORD = aDRESSLOCORD;
		}



		public java.sql.Date getDOCDATEORDER() {
			return DOCDATEORDER;
		}



		public void setDOCDATEORDER(java.sql.Date dOCDATEORDER) {
			DOCDATEORDER = dOCDATEORDER;
		}



	public FerreroRequest24() {
		
		// TODO Auto-generated constructor stub
	}
}
