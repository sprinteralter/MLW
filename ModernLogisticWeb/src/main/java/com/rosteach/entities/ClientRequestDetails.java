package com.rosteach.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ClientRequestDetails {
	@Id
	private Integer GOODSID;
    private String GOODSSNAME;
    private String GOODSNAME; 
    private Integer GOODSGROUPID; 
    private String GOODSGROUPSNAME; 
    private Integer GOODSMEASUREID; 
    private String MEASURESNAME; 
    private Double SUMREMAINDER; 
    private Double ITEMPRICE; 
    private Double ITEMCOUNT; 
    private Double SUMITEMPRICE; 
    private Double ITEMPRICEWITHOVERH; 
    private Double SUMITEMPRICEWITHOVERH; 
    private Double COEF;
    private Double IMPLEMENTATION; 
    private Double ITEMCOUNTDONE;
    private String GOODSCODE; 
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
    private Double QUOTACOUNT; 
    private Integer QUOTATYPE; 
    private Double QUOTASVAL_REM;
    private Double QUOTASVAL_REM_ALLOWED; 

    public ClientRequestDetails(){
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

	public Integer getGOODSMEASUREID() {
		return GOODSMEASUREID;
	}

	public void setGOODSMEASUREID(Integer gOODSMEASUREID) {
		GOODSMEASUREID = gOODSMEASUREID;
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

	public Double getITEMPRICE() {
		return ITEMPRICE;
	}

	public void setITEMPRICE(Double iTEMPRICE) {
		ITEMPRICE = iTEMPRICE;
	}

	public Double getITEMCOUNT() {
		return ITEMCOUNT;
	}

	public void setITEMCOUNT(Double iTEMCOUNT) {
		ITEMCOUNT = iTEMCOUNT;
	}

	public Double getSUMITEMPRICE() {
		return SUMITEMPRICE;
	}

	public void setSUMITEMPRICE(Double sUMITEMPRICE) {
		SUMITEMPRICE = sUMITEMPRICE;
	}

	public Double getITEMPRICEWITHOVERH() {
		return ITEMPRICEWITHOVERH;
	}

	public void setITEMPRICEWITHOVERH(Double iTEMPRICEWITHOVERH) {
		ITEMPRICEWITHOVERH = iTEMPRICEWITHOVERH;
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

	public Double getIMPLEMENTATION() {
		return IMPLEMENTATION;
	}

	public void setIMPLEMENTATION(Double iMPLEMENTATION) {
		IMPLEMENTATION = iMPLEMENTATION;
	}

	public Double getITEMCOUNTDONE() {
		return ITEMCOUNTDONE;
	}

	public void setITEMCOUNTDONE(Double iTEMCOUNTDONE) {
		ITEMCOUNTDONE = iTEMCOUNTDONE;
	}

	public String getGOODSCODE() {
		return GOODSCODE;
	}

	public void setGOODSCODE(String gOODSCODE) {
		GOODSCODE = gOODSCODE;
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

	public Double getQUOTACOUNT() {
		return QUOTACOUNT;
	}

	public void setQUOTACOUNT(Double qUOTACOUNT) {
		QUOTACOUNT = qUOTACOUNT;
	}

	public Integer getQUOTATYPE() {
		return QUOTATYPE;
	}

	public void setQUOTATYPE(Integer qUOTATYPE) {
		QUOTATYPE = qUOTATYPE;
	}

	public Double getQUOTASVAL_REM() {
		return QUOTASVAL_REM;
	}

	public void setQUOTASVAL_REM(Double qUOTASVAL_REM) {
		QUOTASVAL_REM = qUOTASVAL_REM;
	}

	public Double getQUOTASVAL_REM_ALLOWED() {
		return QUOTASVAL_REM_ALLOWED;
	}

	public void setQUOTASVAL_REM_ALLOWED(Double qUOTASVAL_REM_ALLOWED) {
		QUOTASVAL_REM_ALLOWED = qUOTASVAL_REM_ALLOWED;
	}
    
}
