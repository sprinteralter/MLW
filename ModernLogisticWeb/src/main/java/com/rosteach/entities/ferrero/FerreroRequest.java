package com.rosteach.entities.ferrero;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Persistence;

import com.rosteach.DAO.security.GetDetails;


public class FerreroRequest {

	
	Integer idorder;
	java.sql.Date DOCDATEORDER;
    Integer AGENTID;
    Integer CLIENTID;
    Integer GOODSID;
    BigInteger ITEMCOUNTORDER;
   String IDOUT;
   java.sql.Date LOADINGDATE;
   BigInteger ITEMCOUNTOUT;
    Integer RES;
    Integer RESRATE;
    String CLIENTSNAMEORD;
    String ADRESSLOCORD;
    String GOODSNAME;
    Integer GOODSGROUP;
    String GROUPNAME;
    String AGENTSNAME;
    Integer PRODTREEID;
    String PRODTREENAME;
    String PAYTYPENAME;
    
    public FerreroRequest() {
		
		// TODO Auto-generated constructor stub
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
	public java.sql.Date getDOCDATEORDER() {
				return DOCDATEORDER;
	}
	public void setDOCDATEORDER(java.sql.Date dOCDATEORDER) {
		DOCDATEORDER = dOCDATEORDER;
		
	}
	public Integer getAGENTID() {
		return AGENTID;
	}
	public void setAGENTID(int aGENTID) {
		AGENTID = aGENTID;
	}
	public Integer getCLIENTID() {
		return CLIENTID;
	}
	public void setCLIENTID(Integer cLIENTID) {
		CLIENTID = cLIENTID;
	}
	public Integer getGOODSID() {
		return GOODSID;
	}
	public void setGOODSID(int gOODSID) {
		GOODSID = gOODSID;
	}
	public BigInteger getITEMCOUNTORDER() {
		return ITEMCOUNTORDER;
	}
	public void setITEMCOUNTORDER(BigInteger r) {
		ITEMCOUNTORDER = r;
	}
	public String getIDOUT() {
		return IDOUT;
	}
	public void setIDOUT(String iDOUT) {
		IDOUT = iDOUT;
	}
	public java.sql.Date getLOADINGDATE() {
		
		return LOADINGDATE;
		
	}
	public void setLOADINGDATE(java.sql.Date lOADINGDATE) {
		
		LOADINGDATE = lOADINGDATE;
	}
	public BigInteger getITEMCOUNTOUT() {
		return ITEMCOUNTOUT;
	}
	public void setITEMCOUNTOUT(BigInteger r) {
		ITEMCOUNTOUT = r;
	}
	public Integer getRES() {
		return RES;
	}
	public void setRES(int rES) {
		RES = rES;
	}
	public Integer getRESRATE() {
		return RESRATE;
	}
	public void setRESRATE(Integer r) {
		RESRATE = r;
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
	public String getGOODSNAME() {
		return GOODSNAME;
	}
	public void setGOODSNAME(String gOODSNAME) {
		GOODSNAME = gOODSNAME;
	}
	public Integer getGOODSGROUP() {
		return GOODSGROUP;
	}
	public void setGOODSGROUP(int gOODSGROUP) {
		GOODSGROUP = gOODSGROUP;
	}
	public String getGROUPNAME() {
		return GROUPNAME;
	}
	public void setGROUPNAME(String gROUPNAME) {
		GROUPNAME = gROUPNAME;
	}
	public String getAGENTSNAME() {
		return AGENTSNAME;
	}
	public void setAGENTSNAME(String aGENTSNAME) {
		AGENTSNAME = aGENTSNAME;
	}
	public int getPRODTREEID() {
		return PRODTREEID;
	}
	public void setPRODTREEID(int pRODTREEID) {
		PRODTREEID = pRODTREEID;
	}
	public String getPRODTREENAME() {
		return PRODTREENAME;
	}
	public void setPRODTREENAME(String pRODTREENAME) {
		PRODTREENAME = pRODTREENAME;
	}
	
    
	
	
	
	/* @Id
	private int id;
	private int clientid;
	private String clientsname;
	private String clientadresslocation;
	private String agentsname;
	private java.sql.Date docdate;
	private String paytypesname;
	//private java.sql.Date termdate;
	private int ordersByPeriod;
	//private int h24;
	//private int outLater;
	
	public FerreroRequest(){}; 
	
	public FerreroRequest(int id, int cLIENTID, String clientsname, String cLIENTADRESSLOCATION, String agentsname,
			int ordersByPeriod, Date docdate, String paytypesname, Date termdate, int h24, int outLater) {
		super();
		this.id = id;
		this.clientid = cLIENTID;
		this.clientsname = clientsname;
		this.clientadresslocation = cLIENTADRESSLOCATION;
		this.agentsname = agentsname;
		this.ordersByPeriod = ordersByPeriod;
		this.docdate = docdate;
		this.paytypesname = paytypesname;
		//this.termdate = termdate;
		//this.h24 = h24;
		//this.outLater = outLater;
	}
	public int getOrdersByPeriod() {
		GetDetails ud = new GetDetails();
		Map<String,String> properties = new HashMap<String,String>();
		properties.put("javax.persistence.jdbc.url", ud.getDB());
		properties.put("javax.persistence.jdbc.user", ud.getName());
		properties.put("javax.persistence.jdbc.password", ud.getPass());
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("database", properties); 
	    EntityManager em =  emf.createEntityManager();
	    
		return ordersByPeriod;
	}
	public void setOrdersByPeriod(int ordersByPeriod) {
		this.ordersByPeriod = ordersByPeriod;
	}
	public int getH24() {
		return h24;
	}
	public void setH24(int h24) {
		this.h24 = h24;
	}
	public int getOutLater() {
		return outLater;
	}
	public void setOutLater(int outLater) {
		this.outLater = outLater;
	}
	
	public java.sql.Date getTermdate() {
		return termdate;
	}
	public void setTermdate(java.sql.Date termdate) {
		this.termdate = termdate;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCLIENTID() {
		return clientid;
	}
	public void setCLIENTID(int cLIENTID) {
		clientid = cLIENTID;
	}
	public String getClientsname() {
		return clientsname;
	}
	public void setClientsname(String clientsname) {
		this.clientsname = clientsname;
	}
	public String getCLIENTADRESSLOCATION() {
		return clientadresslocation;
	}
	public void setCLIENTADRESSLOCATION(String cLIENTADRESSLOCATION) {
		clientadresslocation = cLIENTADRESSLOCATION;
	}
	public String getAgentsname() {
		return agentsname;
	}
	public void setAgentsname(String agentsname) {
		this.agentsname = agentsname;
	}
	public java.sql.Date getDocdate() {
		return docdate;
	}
	public void setDocdate(java.sql.Date docdate) {
		this.docdate = docdate;
	}
	
	public String getPaytypesname() {
		return paytypesname;
	}
	public void setPaytypesname(String paytypesname) {
		this.paytypesname = paytypesname;
	}
	*/
	
}
