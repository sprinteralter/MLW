package com.rosteach.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SPROutcomeInvoiceDetails {
	@Id
	private Integer GOODSID;
	private String GOODSSNAME;
	private String GOODSNAME;
	private String GOODSCODE;
	private Integer GOODSGROUPID;
	private String GOODSGROUPSNAME;
	private Integer MEASUREID;
	private String MEASURESNAME;
	private Double SUMREMAINDER;
	private Integer PRICETYPEID;
	private String PRICETYPESNAME;
	private Double ITEMCOUNT;
	private Double ENDPRICE;
	private Double SUMITEMPRICE;
	private Double ENDPRICEWITHOVERH;
	private Double SUMITEMPRICEWITHOVERH;
	private Double COEF;
	private String GOODSCODE1;
	private String GOODSCODE2;
	private String GOODSCODE3;
	private Integer PRODSEQ;
    private Integer PRODID;
    private String PRODCODE;
    private String PRODSNAME;
    private String PRODNAME;
    private String PRODTREESNAME;
    private String FABTYPESTR;
    private Integer RECEIPTED;

	public SPROutcomeInvoiceDetails(){
		
	}

	public Integer getGOODSID() {
		return GOODSID;
	}

	public void setGOODSID(Integer gOODSID) {
		GOODSID = gOODSID;
	}

	public String getGOODSSNAME() {
		return GOODSSNAME;
	}

	public void setGOODSSNAME(String gOODSSNAME) {
		GOODSSNAME = gOODSSNAME;
	}

	public String getGOODSNAME() {
		return GOODSNAME;
	}

	public void setGOODSNAME(String gOODSNAME) {
		GOODSNAME = gOODSNAME;
	}

	public String getGOODSCODE() {
		return GOODSCODE;
	}

	public void setGOODSCODE(String gOODSCODE) {
		GOODSCODE = gOODSCODE;
	}

	public Integer getGOODSGROUPID() {
		return GOODSGROUPID;
	}

	public void setGOODSGROUPID(Integer gOODSGROUPID) {
		GOODSGROUPID = gOODSGROUPID;
	}

	public String getGOODSGROUPSNAME() {
		return GOODSGROUPSNAME;
	}

	public void setGOODSGROUPSNAME(String gOODSGROUPSNAME) {
		GOODSGROUPSNAME = gOODSGROUPSNAME;
	}

	public Integer getMEASUREID() {
		return MEASUREID;
	}

	public void setMEASUREID(Integer mEASUREID) {
		MEASUREID = mEASUREID;
	}

	public String getMEASURESNAME() {
		return MEASURESNAME;
	}

	public void setMEASURESNAME(String mEASURESNAME) {
		MEASURESNAME = mEASURESNAME;
	}

	public Double getSUMREMAINDER() {
		return SUMREMAINDER;
	}

	public void setSUMREMAINDER(Double sUMREMAINDER) {
		SUMREMAINDER = sUMREMAINDER;
	}

	public Integer getPRICETYPEID() {
		return PRICETYPEID;
	}

	public void setPRICETYPEID(Integer pRICETYPEID) {
		PRICETYPEID = pRICETYPEID;
	}

	public String getPRICETYPESNAME() {
		return PRICETYPESNAME;
	}

	public void setPRICETYPESNAME(String pRICETYPESNAME) {
		PRICETYPESNAME = pRICETYPESNAME;
	}

	public Double getITEMCOUNT() {
		return ITEMCOUNT;
	}

	public void setITEMCOUNT(Double iTEMCOUNT) {
		ITEMCOUNT = iTEMCOUNT;
	}

	public Double getENDPRICE() {
		return ENDPRICE;
	}

	public void setENDPRICE(Double eNDPRICE) {
		ENDPRICE = eNDPRICE;
	}

	public Double getSUMITEMPRICE() {
		return SUMITEMPRICE;
	}

	public void setSUMITEMPRICE(Double sUMITEMPRICE) {
		SUMITEMPRICE = sUMITEMPRICE;
	}

	public Double getENDPRICEWITHOVERH() {
		return ENDPRICEWITHOVERH;
	}

	public void setENDPRICEWITHOVERH(Double eNDPRICEWITHOVERH) {
		ENDPRICEWITHOVERH = eNDPRICEWITHOVERH;
	}

	public Double getSUMITEMPRICEWITHOVERH() {
		return SUMITEMPRICEWITHOVERH;
	}

	public void setSUMITEMPRICEWITHOVERH(Double sUMITEMPRICEWITHOVERH) {
		SUMITEMPRICEWITHOVERH = sUMITEMPRICEWITHOVERH;
	}

	public Double getCOEF() {
		return COEF;
	}

	public void setCOEF(Double cOEF) {
		COEF = cOEF;
	}

	public String getGOODSCODE1() {
		return GOODSCODE1;
	}

	public void setGOODSCODE1(String gOODSCODE1) {
		GOODSCODE1 = gOODSCODE1;
	}

	public String getGOODSCODE2() {
		return GOODSCODE2;
	}

	public void setGOODSCODE2(String gOODSCODE2) {
		GOODSCODE2 = gOODSCODE2;
	}

	public String getGOODSCODE3() {
		return GOODSCODE3;
	}

	public void setGOODSCODE3(String gOODSCODE3) {
		GOODSCODE3 = gOODSCODE3;
	}

	public Integer getPRODSEQ() {
		return PRODSEQ;
	}

	public void setPRODSEQ(Integer pRODSEQ) {
		PRODSEQ = pRODSEQ;
	}

	public Integer getPRODID() {
		return PRODID;
	}

	public void setPRODID(Integer pRODID) {
		PRODID = pRODID;
	}

	public String getPRODCODE() {
		return PRODCODE;
	}

	public void setPRODCODE(String pRODCODE) {
		PRODCODE = pRODCODE;
	}

	public String getPRODSNAME() {
		return PRODSNAME;
	}

	public void setPRODSNAME(String pRODSNAME) {
		PRODSNAME = pRODSNAME;
	}

	public String getPRODNAME() {
		return PRODNAME;
	}

	public void setPRODNAME(String pRODNAME) {
		PRODNAME = pRODNAME;
	}

	public String getPRODTREESNAME() {
		return PRODTREESNAME;
	}

	public void setPRODTREESNAME(String pRODTREESNAME) {
		PRODTREESNAME = pRODTREESNAME;
	}

	public String getFABTYPESTR() {
		return FABTYPESTR;
	}

	public void setFABTYPESTR(String fABTYPESTR) {
		FABTYPESTR = fABTYPESTR;
	}

	public Integer getRECEIPTED() {
		return RECEIPTED;
	}

	public void setRECEIPTED(Integer rECEIPTED) {
		RECEIPTED = rECEIPTED;
	}
}