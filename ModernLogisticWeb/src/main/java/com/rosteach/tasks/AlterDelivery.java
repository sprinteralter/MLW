package com.rosteach.tasks;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

public class AlterDelivery {
	
	LocalDate today = LocalDate.now(); //LocalDate.of(2016, 04, 29);//
	LocalDate minus45 = today.minusDays(45);
	int iter = today.getDayOfYear() - minus45.getDayOfYear()+1;
	
	//int delta = today.getDayOfYear() - minus45.getDayOfYear();
	
	public AlterDelivery(LocalDate s, LocalDate po){
		today = po;
		minus45 = s;
		iter = today.getDayOfYear() - minus45.getDayOfYear()+1;
	}
	
	public AlterDelivery(){
	
	}
	
	
public List<Delivery> delivery() throws IOException{	

	List<Delivery> del = new ArrayList();
	int store = 0;
	
	Map<String,String> props = new HashMap<String,String>();
	props.put("javax.persistence.jdbc.url", "jdbc:firebirdsql:192.168.20.17/3050:alter?lc_ctype=WIN1251;sql_dialect=3");
	props.put("javax.persistence.jdbc.user","SYSDBA");
	props.put("javax.persistence.jdbc.password","strongpass");
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("database",props);
	EntityManager em = emf.createEntityManager();
	Query form1 = em.createNativeQuery("select "
			+ "c.id Client_Code,  "
			+ "dc.docdate Doc_Date, "
			+ "g.code1 SKU_Code, "
			+ "outcards.itemcount itemcount,  "
			+ "id.itemcount * id.endprice * g.coef Del_Amount, " //+ "o.SUMITEMPRICEWITHOVERH, " //
			+ "dc.agentid TA, "
			+"g.id goodsid, "
			+ "ca.buyprice * g.coef, "
			+ "ou.regnumber regnum, "
			+ "ou.id outid" 
			+ " from "
			+ "doccommon dc, "
			+ "outcomeinvoicedet id, "
			+ "goods g, "
			+ "client c, "
			+ "cardindex ca, "
			+ "OUTCOMEINVOICECARDS outcards, "
			+ "outcomeinvoice ou"
			+ " where "
			+ "c.id=dc.clientid"
			+ " and dc.id = id.outcomeinvoiceid"
			+ " and g.id = id.goodsid "
			+ "and dc.STOREIDSRC >="+store
			+ " and id.receipted = 1"
			+ " and dc.signed = 3"
			+ " and dc.isreturn = 0"
			+ " and g.prodtreeid in (5062)"
			+ " and dc.clientid not in (907,300003,300701,301271,8169,14970,1823)" //4151, //
			+ " and dc.docdate >= '"+minus45+"' "
			+ "and dc.docdate <= '"+today+"' "
			+ "and ca.id = outcards.cardindexid "
			+ "and id.outcomeinvoiceid = outcards.outcomeinvoiceid"
			+ " and ca.goodsid = g.id "
			+ " and ou.id = id.outcomeinvoiceid"); 
	
	Query form2 = em.createNativeQuery("select "
			+ "c.id Client_Code,  "
			+ "dc.docdate Doc_Date, "
			+ "g.code1 SKU_Code, "
			+ "mgrca.itemcount itemcount,  "
			+ "id.itemcount * id.endprice * g.coef Del_Amount, "
			+ "dc.agentid TA, "
			+"g.id goodsid, "
			+ "ca.buyprice * g.coef, "
			+ "im.regnumber regnum, "
			+ "im.id inrmigrID "
			+ "from "
			+ "doccommon dc, "
			+ "innermigrationdet id, "
			+ "goods g, "
			+ "client c, "
			+ "cardindex ca,"
			+ "INNERMIGRATIONCARDS mgrca,"
			+ " innermigration im "
			+ "where "
			+ "c.id=dc.clientid"
			+ " and dc.id = id.innermigrationid"
			+ " and g.id = id.goodsid "
			//+ " and dc.STOREIDSRC >="+store
			+ " and id.receipted = 1"
			+ " and dc.storeidsrc ="+store
			+ " and dc.signed = 3"
			+ " and dc.isreturn = 0"
			+ " and g.prodtreeid = 5062"
			+ " and dc.clientid not in (907,300003,300701,301271,8169,14970,1823)" //4151, 
			+ " and dc.docdate >=  '"+minus45+"' "
			+ "and dc.docdate <= '"+today+"' "
			+ "and ca.id = mgrca.cardindexiddst "
			+ "and id.innermigrationid = mgrca.innermigrationid "
			+ "and ca.goodsid = g.id "
			+ "and im.id = id.innermigrationid"); 
	
	Query returns = em.createNativeQuery("select "
			+ "c.id Client_Code, "
			+ "dc.docdate Del_Date, "
			+ "g.code1 SKU_Code,  "
			+ "id.itemcount * -1 Del_Volume, "
			+ "(id.itemcount * id.retprice * g.coef * -1) Del_Amount, "
			+ "dc.agentid TA, "
			+ "g.id goodsid, "
			+ "ca.buyprice * g.coef, "
			+ "ii.regnumber, "
			+ "ca.id caid, "
			+ "ii.id incomeid "
			+ "from "
			+ " doccommon dc, "
			+ "incomeinvoicedet id, "
			+ "goods g, "
			+ "client c, "
			+ "cardindex ca, "
			+ "incomeinvoice ii "
			+ "where "
			+ "c.id=dc.clientid "
			+ "and dc.id = id.incomeinvoiceid "
			+ "and g.id = id.goodsid "
			+ "and dc.STOREIDDST = "+store
			+ " and id.receipted = 1 "
			+ "and dc.signed = 3 "
			+ "and dc.isreturn = 1 "
			+ "and g.prodtreeid = 5062 "
			+ "and dc.clientid not in (907,300003,300701,301271,8169,14970,1823) " //4151 
			+ " and dc.docdate >=  '"+minus45+"' "
			+ "and dc.docdate <= '"+today+"' "
			+ "and ca.id = id.cardindexid "
			+ "and ca.goodsid = g.id "
			+ " and ii.id = id.incomeinvoiceid "); 
	
	
	for (Object d : form1.getResultList()){
		Object[] cols = (Object[]) d;
		Delivery s = new Delivery();
		s.setClientId(String.valueOf(cols[0]));
		s.setDocDate(String.valueOf(cols[1]));
		s.setSkuCode(String.valueOf(cols[2]));
		s.setItemCount(String.valueOf(cols[3]));
		s.setAmount(String.valueOf(cols[4])); //em.createNativeQuery("select SUMITEMPRICEWITHOVERH from SPROUTCOMEINVOICEDET ("+cols[9]+",Null,0,Null,Null,0,0) where goodsid = "+cols[6]+" ").getSingleResult().toString()); //String.valueOf(cols[4]));
		s.setTa(String.valueOf(cols[5]));
		s.setProdCode(String.valueOf(cols[6]));
		s.setPrice(String.valueOf(cols[7]));
		s.setOutcomeInvoice(String.valueOf(cols[8]));
		s.setDistr_id("4");
		del.add(s);

	}
	for (Object d : form2.getResultList()){
		Object[] cols = (Object[]) d;
		Delivery s = new Delivery();
		s.setClientId(String.valueOf(cols[0]));
		s.setDocDate(String.valueOf(cols[1]));
		s.setSkuCode(String.valueOf(cols[2]));
		s.setItemCount(String.valueOf(cols[3]));
		s.setAmount(String.valueOf(cols[4]));//em.createNativeQuery("select distinct SUMITEMPRICEWITHOVERH from SPRINNERMIGRATIONDET ("+cols[9]+",Null,0,Null,Null,0,0) where goodsid = "+cols[6]).getSingleResult().toString());//String.valueOf(cols[4])); // +" and itemcount = "+cols[3]
		s.setTa(String.valueOf(cols[5]));
		s.setProdCode(String.valueOf(cols[6]));
		s.setPrice(String.valueOf(cols[7]));
		s.setOutcomeInvoice(String.valueOf(cols[8]));
		s.setDistr_id("4");
		del.add(s);
		
	}
	for (Object d : returns.getResultList()){
		Object[] cols = (Object[]) d;
		Delivery s = new Delivery();
		s.setClientId(String.valueOf(cols[0]));
		s.setDocDate(String.valueOf(cols[1]));
		s.setSkuCode(String.valueOf(cols[2]));
		s.setItemCount(String.valueOf(cols[3]));
		//int sum = (int) em.createNativeQuery("select SUMITEMPRICEWITHOVERH from sprincomeinvoicedet ("+cols[8]+",Null,0,1,Null,Null, Null,0,0) where goodsid = "+cols[6]+" and itemcount = ("+cols[3]+" * -1)  ").getSingleResult());
		if(cols[4] != null)
		s.setAmount("-"+String.valueOf(cols[4]));//em.createNativeQuery("select SUMITEMPRICEWITHOVERH from sprincomeinvoicedet ("+cols[10]+",Null,0,1,Null,Null, Null,0,0) where goodsid = "+cols[6]+"  and cardindexid = "+cols[9]).getSingleResult().toString());//String.valueOf(cols[4]));
		s.setTa(String.valueOf(cols[5]));
		s.setProdCode(String.valueOf(cols[6]));
		s.setPrice(String.valueOf(cols[7]));
		s.setOutcomeInvoice(String.valueOf(cols[8]));
		s.setDistr_id("4");
		
		if (cols[4] != null && s.getAmount().contains("--")){
			s.setAmount(s.getAmount().substring(1));
			
		}
		
		if (cols[4] != null && Double.valueOf(s.getAmount()) < 0 && Double.valueOf(s.getItemCount()) > 0){
			s.setItemCount("-"+s.getItemCount());
		} 
		if (cols[4] == null){
			s.setAmount("0.0");
		}
		
		del.add(s);
		
	}
	
	return del;
}

public List<RemObj> stocks(){
	

	Map<String,String> props = new HashMap<String,String>();
	props.put("javax.persistence.jdbc.url", "jdbc:firebirdsql:192.168.20.17/3050:alter?lc_ctype=WIN1251;sql_dialect=3");
	props.put("javax.persistence.jdbc.user","SYSDBA");
	props.put("javax.persistence.jdbc.password","strongpass");
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("database",props);
	EntityManager em = emf.createEntityManager();
	List<RemObj> remainders = new ArrayList();
	
	for (int i = 0; i < iter; i++){   ///////////////////////////////////////////////////////////////////////////////////////
		LocalDate day = today.minusDays(i);
	Query rem = em.createNativeQuery("select"
			+ " r.goodsid, "
			+"r.REMAINDERITEMS2 ," //"sum(r.SUMREMAINDER) Stock_Volume, " //r.REMAINDERITEMS1 !!!!
			+ "p.val, "
			+ "r.incomeitems "
			+ "from "
			+ "SPRTURNOVERBALANCESHEET ('0','4066,4067,4068,4069', '"+day+"', '"+day+"',1, 1,1,5062,0) r, " //"SPRACTUALREMAINDERS ('0','4066,4067,4068,4069', '"+day+"', Null,1,0,0,0) r, "
			+ "goods g, "
			+ "fixedprice p "
			+ "where g.id=r.goodsid "
			+ "and p.goodsid = g.id "
			+ "and p.pricetypeid = 1 "
			+ " order by r.GOODSID Desc");//"group by 1,3");
	for (Object o : rem.getResultList()){
		Object[] cols = (Object[]) o;
		Double tmp = (Double)cols[1];
		if(tmp < 0){
			tmp = 0.0;
		}
		
		
		RemObj remobj = new RemObj("4", String.valueOf(day), String.valueOf(cols[0]), String.valueOf(tmp), String.valueOf((Double)cols[2]*tmp));
		remainders.add(remobj);
	}
	}

	return remainders;
	
}

public List<Client> client(){
	Map<String,String> props = new HashMap<String,String>();
	props.put("javax.persistence.jdbc.url", "jdbc:firebirdsql:192.168.20.17/3050:alter?lc_ctype=WIN1251;sql_dialect=3");
	props.put("javax.persistence.jdbc.user","SYSDBA");
	props.put("javax.persistence.jdbc.password","strongpass");
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("database",props);
	EntityManager em = emf.createEntityManager();
	LocalDate now = LocalDate.now();
	Query getC = em.createNativeQuery("select distinct "
			+ "c.id, "
			+ "c.sname, "
			+ "c.addresslocation "
			+ "from "
			+ " outcomeinvoicedet id,"
			+ " goods g,"
			+ " client c,"
			+ " doccommon dc "
			+ "where"
			+ " c.id = dc.clientid "
			+ "and dc.id = id.outcomeinvoiceid "
			+ "and g.id = id.goodsid "
			+ "and g.prodtreeid = 5062 "
			+ "and id.receipted = 1 "
			+ "and dc.SIGNED = 3 "
			+ "and (dc.storeidsrc = 0 or dc.storeidsrc = 3000)"
			+ "and dc.docdate >= '"+minus45+"' "//"+now.minusDays(45)+"'"
			+ "and dc.clientid not in (907,300003,300701,301271,8169,14970,1823) " //4151,
			+ "and dc.docdate <=  '"+today+"' "
			
			
		 + "union select distinct "
		+ "c.id,"
		+ " c.sname, "
		+ "c.addresslocation "
		+ "from  "
		+ "incomeinvoicedet id, "
		+ "goods g, "
		+ "client c, "
		+ "doccommon dc "
		+ "where "
		+ "c.id = dc.clientid "
		+ "and dc.id = id.incomeinvoiceid "
		+ "and g.id = id.goodsid "
		+ "and g.prodtreeid = 5062 "
		+ "and id.receipted = 1 "
		+ "and dc.SIGNED = 3 "
		+ "and dc.isreturn = 1"
		+ "and dc.storeiddst = 0 "
		+ "and dc.clientid not in (907,300003,300701,301271,8169,14970,1823) " //4151,
		+ "and dc.docdate >=  '"+minus45+"'"//"+now.minusDays(45)+"'"
				+ " and dc.docdate <=  '"+today+"'"
			
			+ "union select distinct "
			+ "c.id,"
			+ " c.sname, "
			+ "c.addresslocation "
			+ "from  "
			+ "innermigrationdet id, "
			+ "goods g, "
			+ "client c, "
			+ "doccommon dc "
			+ "where "
			+ "c.id = dc.clientid "
			+ "and dc.id = id.innermigrationid "
			+ "and g.id = id.goodsid "
			+ "and g.prodtreeid = 5062 "
			+ "and id.receipted = 1 "
			+ "and dc.SIGNED = 3 "
			+ "and dc.storeidsrc = 0 "
			+ "and dc.clientid not in (907,300003,300701,301271,8169,14970,1823) " //4151,
			+ "and dc.docdate >=  '"+minus45+"' "//"+now.minusDays(45)+"'"
			+ "and dc.docdate <=  '"+today+"'");
			
	List<Client> cl = new ArrayList();
	for (Object o : getC.getResultList()){
		Object[] cols = (Object[]) o;
		
		Client c = new Client(String.valueOf(cols[0]), (String)cols[1], (String)cols[2], "4");
		
		cl.add(c);
	}
	return cl;
	
	
}

public List ta(){
	
	Map<String,String> props = new HashMap<String,String>();
	props.put("javax.persistence.jdbc.url", "jdbc:firebirdsql:192.168.20.17/3050:alter?lc_ctype=WIN1251;sql_dialect=3");
	props.put("javax.persistence.jdbc.user","SYSDBA");
	props.put("javax.persistence.jdbc.password","strongpass");
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("database",props);
	EntityManager em = emf.createEntityManager();
	
	
	LocalDate now = LocalDate.now();
	Query getC = em.createNativeQuery(""
			+ "select distinct "
			+ "a.id Distr_Code, a.sname TA_Name "
			+ "from  "
			+ "innermigrationdet id, goods g, agents a, doccommon dc "
			+ "where a.id = dc.agentid and dc.id = id.innermigrationid and g.id = id.goodsid and dc.STOREIDSRC = 0 "
			+ "and g.prodtreeid = 5062 and id.receipted = 1 and dc.signed = 3 and dc.clientid not in (907,300003,300701,301271,4151,8169,14970) "
			+ "and dc.docdate >= '"+minus45+"' and dc.docdate <= '"+today+"' "
			+ "union select distinct "
			+ "a.id, a.sname "
			+ "from  "
			+ "outcomeinvoicedet id, goods g, agents a, doccommon dc "
			+ "where "
			+ "a.id = dc.agentid and dc.id = id.outcomeinvoiceid and g.id = id.goodsid and (dc.STOREIDSRC = 0 or dc.STOREIDSRC = 3000) and g.prodtreeid = 5062 "
			+ "and id.receipted = 1 and dc.SIGNED = 3 and dc.clientid not in (907,300003,300701,301271,4151,8169,14970) and dc.docdate >= '"+minus45+"'"//"+now.minusDays(45)+"' "
			+ "and dc.docdate <= '"+today+"' "
			+ "union select distinct "
			+ "a.id, a.sname "
			+ "from doccommon dc, incomeinvoicedet id, goods g, agents a where a.id=dc.agentid and dc.id = id.incomeinvoiceid "
			+ "and g.id = id.goodsid and dc.STOREIDDST = 0 and id.receipted = 1 and dc.signed = 3 "
			+ "and dc.isreturn = 1 and g.prodtreeid = 5062 and dc.clientid not in (907,300003,300701,301271,4151,8169,14970) "
			+ "and dc.docdate >= '"+minus45+"'"//"+now.minusDays(45)
			+" and dc.docdate <= '"+today+"'");
	return getC.getResultList();
	
}

public List sku(){
	Map<String,String> props = new HashMap<String,String>();
	props.put("javax.persistence.jdbc.url", "jdbc:firebirdsql:192.168.20.17/3050:alter?lc_ctype=WIN1251;sql_dialect=3");
	props.put("javax.persistence.jdbc.user","SYSDBA");
	props.put("javax.persistence.jdbc.password","strongpass");
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("database",props);
	EntityManager em = emf.createEntityManager();
	
	Query getSku = em.createNativeQuery("select g.id, g.sname, g.code, g.measureid from goods g where g.prodtreeid = 5062");
	return getSku.getResultList();
	
}

public List recive(){
	
	Map<String,String> props = new HashMap<String,String>();
	props.put("javax.persistence.jdbc.url", "jdbc:firebirdsql:192.168.20.17/3050:alter?lc_ctype=WIN1251;sql_dialect=3");
	props.put("javax.persistence.jdbc.user","SYSDBA");
	props.put("javax.persistence.jdbc.password","strongpass");
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("database",props);
	EntityManager em = emf.createEntityManager();
	
	Query getSku = em.createNativeQuery("select inc.docdate, i.incomeinvoiceid, i.goodsid, i.itemcount "
			+ "from incomeinvoicedet i, goods g, incomeinvoice inc "
			+ "where i.goodsid=g.id and g.prodtreeid = 5062 and inc.id = i.incomeinvoiceid "
			+ "and inc.docdate >= '"+minus45+"'"//"+ LocalDate.now().minusDays(45)+"' "
					+ "and inc.docdate <= '"+today+"' and i.itemcount > 0 and inc.isreturn = 0"); 	
	//System.out.println(delta+ " DELTA DAYS"); ////////////////////////////////////////////////////////////////////////
	
	return getSku.getResultList();
	
}

}
