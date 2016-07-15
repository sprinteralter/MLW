package com.rosteach.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SPROutcomeInvoice {
	@Id
	private Integer ID;
	
	private String REGNUMBER;
	private Integer STOREID;
	private String STORESNAME;
	private Integer CLIENTID;
	private String CLIENTSNAME;
	private String CLIENTNAME;
    private String DOCDATE;
    private Integer PAYTYPEID;
    private String PAYTYPESNAME;
    private Integer PAYTYPEDEFAULTPROFITSTATE;
    private String FOUNDATION;
    private String RESPONSIBLEPERSON;
    private Integer ISRETURN;
    private Double STARTSUMM;
    private Double ENDSUMM;
    private Double ITEMSSUMM;
    private Integer SIGNED;
    private String USERSIGN;
    java.sql.Timestamp TIMESIGN;
    private Double ENDSUMMGOODSBASED;
    private Double BUYPRICESTARTSUMM;
    private Double BUYPRICEENDSUMM;
    private Double BUYPRICEENDSUMMGOODSBASED;
    private Double MSU;
    private Integer CLOSED;
    private Double BUYRATE;
    private Double SELLRATE;
    private Integer CLEARED;
    private Integer PRICETYPEID;
    private Double MAINSUMM;
    private Integer CLIENTTYPEID;
    private String CLIENTTYPESNAME;
    private Integer TILLPAYDAYS;
    private Integer PAYPERCENT;
    private String PROFILESNAME;
    java.sql.Date PAYTERMDATE;
    private Double DEBT;
    private String COMMENT1;
    private String COMMENT2;
    private String USEREDIT;
    java.sql.Timestamp TIMEEDIT;
    private Integer AGENTID;
    private String AGENTSNAME;
    private Integer CLIENTPARENTID;
    private String CLIENTPARENTSNAME;
    private String KKUSER;
    java.sql.Timestamp KKTIME;
    private Double KKSUMM;
    private String INFO3;
    private Integer RETURNID;
    private Float DISCOUNT;
    private Float SPECDISCOUNT;
    java.sql.Date LOADINGDATE;
    private Integer POINTID;
    private String DELIVPOINTSADDRESS;
    private String REGNUMBER2;
    private String CLIENTADRESSLOCATION;
    private Integer REQUIREDTTN;
    private Integer CLIENTCLASS1;
    
    public SPROutcomeInvoice(){}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getREGNUMBER() {
		return REGNUMBER;
	}

	public void setREGNUMBER(String rEGNUMBER) {
		REGNUMBER = rEGNUMBER;
	}

	public Integer getSTOREID() {
		return STOREID;
	}

	public void setSTOREID(Integer sTOREID) {
		STOREID = sTOREID;
	}

	public String getSTORESNAME() {
		return STORESNAME;
	}

	public void setSTORESNAME(String sTORESNAME) {
		STORESNAME = sTORESNAME;
	}

	public Integer getCLIENTID() {
		return CLIENTID;
	}

	public void setCLIENTID(Integer cLIENTID) {
		CLIENTID = cLIENTID;
	}

	public String getCLIENTSNAME() {
		return CLIENTSNAME;
	}

	public void setCLIENTSNAME(String cLIENTSNAME) {
		CLIENTSNAME = cLIENTSNAME;
	}

	public String getCLIENTNAME() {
		return CLIENTNAME;
	}

	public void setCLIENTNAME(String cLIENTNAME) {
		CLIENTNAME = cLIENTNAME;
	}

	public String getDOCDATE() {
		return DOCDATE;
	}

	public void setDOCDATE(String dOCDATE) {
		DOCDATE = dOCDATE;
	}

	public Integer getPAYTYPEID() {
		return PAYTYPEID;
	}

	public void setPAYTYPEID(Integer pAYTYPEID) {
		PAYTYPEID = pAYTYPEID;
	}

	public String getPAYTYPESNAME() {
		return PAYTYPESNAME;
	}

	public void setPAYTYPESNAME(String pAYTYPESNAME) {
		PAYTYPESNAME = pAYTYPESNAME;
	}

	public Integer getPAYTYPEDEFAULTPROFITSTATE() {
		return PAYTYPEDEFAULTPROFITSTATE;
	}

	public void setPAYTYPEDEFAULTPROFITSTATE(Integer pAYTYPEDEFAULTPROFITSTATE) {
		PAYTYPEDEFAULTPROFITSTATE = pAYTYPEDEFAULTPROFITSTATE;
	}

	public String getFOUNDATION() {
		return FOUNDATION;
	}

	public void setFOUNDATION(String fOUNDATION) {
		FOUNDATION = fOUNDATION;
	}

	public String getRESPONSIBLEPERSON() {
		return RESPONSIBLEPERSON;
	}

	public void setRESPONSIBLEPERSON(String rESPONSIBLEPERSON) {
		RESPONSIBLEPERSON = rESPONSIBLEPERSON;
	}

	public Integer getISRETURN() {
		return ISRETURN;
	}

	public void setISRETURN(Integer iSRETURN) {
		ISRETURN = iSRETURN;
	}

	public Double getSTARTSUMM() {
		return STARTSUMM;
	}

	public void setSTARTSUMM(Double sTARTSUMM) {
		STARTSUMM = sTARTSUMM;
	}

	public Double getENDSUMM() {
		return ENDSUMM;
	}

	public void setENDSUMM(Double eNDSUMM) {
		ENDSUMM = eNDSUMM;
	}

	public Double getITEMSSUMM() {
		return ITEMSSUMM;
	}

	public void setITEMSSUMM(Double iTEMSSUMM) {
		ITEMSSUMM = iTEMSSUMM;
	}

	public Integer getSIGNED() {
		return SIGNED;
	}

	public void setSIGNED(Integer sIGNED) {
		SIGNED = sIGNED;
	}

	public String getUSERSIGN() {
		return USERSIGN;
	}

	public void setUSERSIGN(String uSERSIGN) {
		USERSIGN = uSERSIGN;
	}

	public java.sql.Timestamp getTIMESIGN() {
		return TIMESIGN;
	}

	public void setTIMESIGN(java.sql.Timestamp tIMESIGN) {
		TIMESIGN = tIMESIGN;
	}

	public Double getENDSUMMGOODSBASED() {
		return ENDSUMMGOODSBASED;
	}

	public void setENDSUMMGOODSBASED(Double eNDSUMMGOODSBASED) {
		ENDSUMMGOODSBASED = eNDSUMMGOODSBASED;
	}

	public Double getBUYPRICESTARTSUMM() {
		return BUYPRICESTARTSUMM;
	}

	public void setBUYPRICESTARTSUMM(Double bUYPRICESTARTSUMM) {
		BUYPRICESTARTSUMM = bUYPRICESTARTSUMM;
	}

	public Double getBUYPRICEENDSUMM() {
		return BUYPRICEENDSUMM;
	}

	public void setBUYPRICEENDSUMM(Double bUYPRICEENDSUMM) {
		BUYPRICEENDSUMM = bUYPRICEENDSUMM;
	}

	public Double getBUYPRICEENDSUMMGOODSBASED() {
		return BUYPRICEENDSUMMGOODSBASED;
	}

	public void setBUYPRICEENDSUMMGOODSBASED(Double bUYPRICEENDSUMMGOODSBASED) {
		BUYPRICEENDSUMMGOODSBASED = bUYPRICEENDSUMMGOODSBASED;
	}

	public Double getMSU() {
		return MSU;
	}

	public void setMSU(Double mSU) {
		MSU = mSU;
	}

	public Integer getCLOSED() {
		return CLOSED;
	}

	public void setCLOSED(Integer cLOSED) {
		CLOSED = cLOSED;
	}

	public Double getBUYRATE() {
		return BUYRATE;
	}

	public void setBUYRATE(Double bUYRATE) {
		BUYRATE = bUYRATE;
	}

	public Double getSELLRATE() {
		return SELLRATE;
	}

	public void setSELLRATE(Double sELLRATE) {
		SELLRATE = sELLRATE;
	}

	public Integer getCLEARED() {
		return CLEARED;
	}

	public void setCLEARED(Integer cLEARED) {
		CLEARED = cLEARED;
	}

	public Integer getPRICETYPEID() {
		return PRICETYPEID;
	}

	public void setPRICETYPEID(Integer pRICETYPEID) {
		PRICETYPEID = pRICETYPEID;
	}

	public Double getMAINSUMM() {
		return MAINSUMM;
	}

	public void setMAINSUMM(Double mAINSUMM) {
		MAINSUMM = mAINSUMM;
	}

	public Integer getCLIENTTYPEID() {
		return CLIENTTYPEID;
	}

	public void setCLIENTTYPEID(Integer cLIENTTYPEID) {
		CLIENTTYPEID = cLIENTTYPEID;
	}

	public String getCLIENTTYPESNAME() {
		return CLIENTTYPESNAME;
	}

	public void setCLIENTTYPESNAME(String cLIENTTYPESNAME) {
		CLIENTTYPESNAME = cLIENTTYPESNAME;
	}

	public Integer getTILLPAYDAYS() {
		return TILLPAYDAYS;
	}

	public void setTILLPAYDAYS(Integer tILLPAYDAYS) {
		TILLPAYDAYS = tILLPAYDAYS;
	}

	public Integer getPAYPERCENT() {
		return PAYPERCENT;
	}

	public void setPAYPERCENT(Integer pAYPERCENT) {
		PAYPERCENT = pAYPERCENT;
	}

	public String getPROFILESNAME() {
		return PROFILESNAME;
	}

	public void setPROFILESNAME(String pROFILESNAME) {
		PROFILESNAME = pROFILESNAME;
	}

	public java.sql.Date getPAYTERMDATE() {
		return PAYTERMDATE;
	}

	public void setPAYTERMDATE(java.sql.Date pAYTERMDATE) {
		PAYTERMDATE = pAYTERMDATE;
	}

	public Double getDEBT() {
		return DEBT;
	}

	public void setDEBT(Double dEBT) {
		DEBT = dEBT;
	}

	public String getCOMMENT1() {
		return COMMENT1;
	}

	public void setCOMMENT1(String cOMMENT1) {
		COMMENT1 = cOMMENT1;
	}

	public String getCOMMENT2() {
		return COMMENT2;
	}

	public void setCOMMENT2(String cOMMENT2) {
		COMMENT2 = cOMMENT2;
	}

	public String getUSEREDIT() {
		return USEREDIT;
	}

	public void setUSEREDIT(String uSEREDIT) {
		USEREDIT = uSEREDIT;
	}

	public java.sql.Timestamp getTIMEEDIT() {
		return TIMEEDIT;
	}

	public void setTIMEEDIT(java.sql.Timestamp tIMEEDIT) {
		TIMEEDIT = tIMEEDIT;
	}

	public Integer getAGENTID() {
		return AGENTID;
	}

	public void setAGENTID(Integer aGENTID) {
		AGENTID = aGENTID;
	}

	public String getAGENTSNAME() {
		return AGENTSNAME;
	}

	public void setAGENTSNAME(String aGENTSNAME) {
		AGENTSNAME = aGENTSNAME;
	}

	public Integer getCLIENTPARENTID() {
		return CLIENTPARENTID;
	}

	public void setCLIENTPARENTID(Integer cLIENTPARENTID) {
		CLIENTPARENTID = cLIENTPARENTID;
	}

	public String getCLIENTPARENTSNAME() {
		return CLIENTPARENTSNAME;
	}

	public void setCLIENTPARENTSNAME(String cLIENTPARENTSNAME) {
		CLIENTPARENTSNAME = cLIENTPARENTSNAME;
	}

	public String getKKUSER() {
		return KKUSER;
	}

	public void setKKUSER(String kKUSER) {
		KKUSER = kKUSER;
	}

	public java.sql.Timestamp getKKTIME() {
		return KKTIME;
	}

	public void setKKTIME(java.sql.Timestamp kKTIME) {
		KKTIME = kKTIME;
	}

	public Double getKKSUMM() {
		return KKSUMM;
	}

	public void setKKSUMM(Double kKSUMM) {
		KKSUMM = kKSUMM;
	}

	public String getINFO3() {
		return INFO3;
	}

	public void setINFO3(String iNFO3) {
		INFO3 = iNFO3;
	}

	public Integer getRETURNID() {
		return RETURNID;
	}

	public void setRETURNID(Integer rETURNID) {
		RETURNID = rETURNID;
	}

	public Float getDISCOUNT() {
		return DISCOUNT;
	}

	public void setDISCOUNT(Float dISCOUNT) {
		DISCOUNT = dISCOUNT;
	}

	public Float getSPECDISCOUNT() {
		return SPECDISCOUNT;
	}

	public void setSPECDISCOUNT(Float sPECDISCOUNT) {
		SPECDISCOUNT = sPECDISCOUNT;
	}

	public java.sql.Date getLOADINGDATE() {
		return LOADINGDATE;
	}

	public void setLOADINGDATE(java.sql.Date lOADINGDATE) {
		LOADINGDATE = lOADINGDATE;
	}

	public Integer getPOINTID() {
		return POINTID;
	}

	public void setPOINTID(Integer pOINTID) {
		POINTID = pOINTID;
	}

	public String getDELIVPOINTSADDRESS() {
		return DELIVPOINTSADDRESS;
	}

	public void setDELIVPOINTSADDRESS(String dELIVPOINTSADDRESS) {
		DELIVPOINTSADDRESS = dELIVPOINTSADDRESS;
	}

	public String getREGNUMBER2() {
		return REGNUMBER2;
	}

	public void setREGNUMBER2(String rEGNUMBER2) {
		REGNUMBER2 = rEGNUMBER2;
	}

	public String getCLIENTADRESSLOCATION() {
		return CLIENTADRESSLOCATION;
	}

	public void setCLIENTADRESSLOCATION(String cLIENTADRESSLOCATION) {
		CLIENTADRESSLOCATION = cLIENTADRESSLOCATION;
	}

	public Integer getREQUIREDTTN() {
		return REQUIREDTTN;
	}

	public void setREQUIREDTTN(Integer rEQUIREDTTN) {
		REQUIREDTTN = rEQUIREDTTN;
	}

	public Integer getCLIENTCLASS1() {
		return CLIENTCLASS1;
	}

	public void setCLIENTCLASS1(Integer cLIENTCLASS1) {
		CLIENTCLASS1 = cLIENTCLASS1;
	}
}
