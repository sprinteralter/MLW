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

public class BcDelivery {
	
/*	FileWriter writer;
	CSVPrinter csvFilePrinter = null;
	String LINE_SEPARATOR = "\n";
	CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(LINE_SEPARATOR);*/
	
public List<Delivery> delivery() throws IOException{	
	LocalDate today = LocalDate.now();
	LocalDate minus45 = today.minusDays(45);
	List<Delivery> del = new ArrayList();
	int store = 3000;
	
	Map<String,String> bcprops = new HashMap<String,String>();
	bcprops.put("javax.persistence.jdbc.url", "jdbc:firebirdsql:192.168.30.156/3050:bcalter?lc_ctype=WIN1251;sql_dialect=3");
	bcprops.put("javax.persistence.jdbc.user","SYSDBA");
	bcprops.put("javax.persistence.jdbc.password","Tdhjgf");
	
	EntityManagerFactory emfbc = Persistence.createEntityManagerFactory("database",bcprops);
	EntityManager embc = emfbc.createEntityManager();
	Query form1BC = embc.createNativeQuery("select "
			+ "c.id Client_Code,  "
			+ "dc.docdate Doc_Date, "
			+ "g.code1 SKU_Code, "
			+ "id.itemcount itemcount,  "
			+ "dc.mainsumm price_sum, "
			+ "dc.agentid TA, "
			+"g.id, "
			+ "p.val price_one, "
			+ "id.outcomeinvoiceid "
			+ "from "
			+ "doccommon dc, "
			+ "outcomeinvoicedet id, "
			+ "goods g, "
			+ "client c, "
			+ "fixedprice p "
			+ "where "
			+ "c.id=dc.clientid"
			+ " and dc.id = id.outcomeinvoiceid"
			+ " and g.id = id.goodsid "
			+ "and dc.STOREIDSRC >="+store
			+ " and id.receipted = 1"
			+ " and dc.signed = 3"
			+ " and dc.isreturn = 0"
			+ " and g.prodtreeid in (5062)"
			+ " and dc.clientid not in (300003,300701,301271)"
			+ " and dc.docdate >= '"+minus45+"' "
			+ "and dc.docdate <= '"+today+"' "
			+ "and p.goodsid = g.id "
			+ "and p.pricetypeid = 1"); 
	
	Query form2BC = embc.createNativeQuery("select "
			+ "c.id Client_Code,  "
			+ "dc.docdate Doc_Date, "
			+ "g.code1 SKU_Code, "
			+ "id.itemcount itemcount,  "
			+ "dc.mainsumm price_sum, "
			+ "dc.agentid TA, "
			+"g.id, "
			+ "p.val price_one, "
			+ "id.innermigrationid "
			+ "from "
			+ "doccommon dc, "
			+ "innermigrationdet id, "
			+ "goods g, "
			+ "client c, "
			+ "fixedprice p "
			+ "where "
			+ "c.id=dc.clientid"
			+ " and dc.id = id.innermigrationid"
			+ " and g.id = id.goodsid "
			//+ " and dc.STOREIDSRC >="+store
			+ " and id.receipted = 1"
			+ " and dc.storeidsrc >="+store
			+ " and dc.signed = 3"
			+ " and dc.isreturn = 0"
			+ " and g.prodtreeid = 5062"
			+ " and dc.clientid not in (300003,300701,301271)"
			+ " and dc.docdate >= '"+minus45+"' "
			+ "and dc.docdate <= '"+today+"' "
			+ "and p.goodsid = g.id "
			+ "and p.pricetypeid = 1"); 
	
	Query returnsBC = embc.createNativeQuery("select "
			+ "c.id Client_Code, "
			+ "dc.docdate Del_Date, "
			+ "g.code1 SKU_Code,  "
			+ "id.itemcount * -1 Del_Volume, "
			+ "dc.mainsumm * -1 Del_Amount, "
			+ "dc.agentid TA, g.id, "
			+ "p.val, "
			+ "id.incomeinvoiceid "
			+ "from "
			+ "doccommon dc, "
			+ "incomeinvoicedet id, "
			+ "goods g, "
			+ "client c, "
			+ "fixedprice p "
			+ "where "
			+ "c.id=dc.clientid "
			+ "and dc.id = id.incomeinvoiceid "
			+ "and g.id = id.goodsid "
			+ "and dc.STOREIDDST = "+store
			+ "and id.receipted = 1 "
			+ "and dc.signed = 3 "
			+ "and dc.isreturn = 1 "
			+ "and g.prodtreeid = 5062 "
			+ "and dc.clientid not in (300003,300701,301271) "
			+ "and dc.docdate >= '"+minus45+"' "
			+ "and dc.docdate <= '"+today+"' "
			+ "and p.goodsid = g.id "
			+ "and p.pricetypeid = 5"); 
	
	
	
	/*writer = new FileWriter("C:/RepOut/spot2d_homka/delivery.csv");
	CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader("ID дистрибьютора;Код клиента ERP;Дата;Код продукта дистрибьютора;Количество;Сумма отгрузки;Код ТА;Закупочная цена;Номер расходной накладной").withEscape('\\').withQuoteMode(QuoteMode.NONE); //"id","code","name","measureid"
	csvFilePrinter = new CSVPrinter(writer, csvFileFormat);*/
	
	
	for (Object d : form1BC.getResultList()){
		Object[] cols = (Object[]) d;
		Delivery s = new Delivery();
		s.setClientId(String.valueOf(cols[0]));
		s.setDocDate(String.valueOf(cols[1]));
		s.setSkuCode(String.valueOf(cols[2]));
		s.setItemCount(String.valueOf(cols[3]));
		s.setAmount(String.valueOf(cols[4]));
		s.setTa(String.valueOf(cols[5]));
		s.setProdCode(String.valueOf(cols[6]));
		s.setPrice(String.valueOf(cols[7]));
		s.setOutcomeInvoice(String.valueOf(cols[8]));
		s.setDistr_id("5");
		del.add(s);
	}
	for (Object d : form2BC.getResultList()){
		Object[] cols = (Object[]) d;
		Delivery s = new Delivery();
		s.setClientId(String.valueOf(cols[0]));
		s.setDocDate(String.valueOf(cols[1]));
		s.setSkuCode(String.valueOf(cols[2]));
		s.setItemCount(String.valueOf(cols[3]));
		s.setAmount(String.valueOf(cols[4]));
		s.setTa(String.valueOf(cols[5]));
		s.setProdCode(String.valueOf(cols[6]));
		s.setPrice(String.valueOf(cols[7]));
		s.setOutcomeInvoice(String.valueOf(cols[8]));
		s.setDistr_id("5");
		del.add(s);
	}
	for (Object d : returnsBC.getResultList()){
		Object[] cols = (Object[]) d;
		Delivery s = new Delivery();
		s.setClientId(String.valueOf(cols[0]));
		s.setDocDate(String.valueOf(cols[1]));
		s.setSkuCode(String.valueOf(cols[2]));
		s.setItemCount(String.valueOf(cols[3]));
		s.setAmount(String.valueOf(cols[4]));
		s.setTa(String.valueOf(cols[5]));
		s.setProdCode(String.valueOf(cols[6]));
		s.setPrice(String.valueOf(cols[7]));
		s.setOutcomeInvoice(String.valueOf(cols[8]));
		s.setDistr_id("5");
		del.add(s);
	}
	
	/*for (Delivery d : del){
		csvFilePrinter.printRecord(d.getDistr_id()+";"+d.getClientId()+";"+d.getDocDate()+";"+d.getProdCode()+";"+d.getItemCount()+";"+d.getAmount()+";"+d.getTa()+";"+d.getPrice()+";"+d.getOutcomeInvoice());//s.getId()+";"+s.getCode()+";"+s.name+";"+s.getMeasureid()
	}
	
	writer.flush();
	writer.close();
	csvFilePrinter.close();*/
	return del;
}

public List<RemObj> stocks(){

	Map<String,String> bcprops = new HashMap<String,String>();
	bcprops.put("javax.persistence.jdbc.url", "jdbc:firebirdsql:192.168.30.156/3050:bcalter?lc_ctype=WIN1251;sql_dialect=3");
	bcprops.put("javax.persistence.jdbc.user","SYSDBA");
	bcprops.put("javax.persistence.jdbc.password","Tdhjgf");
	
	EntityManagerFactory emfbc = Persistence.createEntityManagerFactory("database",bcprops);
	EntityManager embc = emfbc.createEntityManager();
	List<RemObj> remainders = new ArrayList();
	
	for (int i = 0; i < 45; i++){
		LocalDate day = LocalDate.now().minusDays(i);
	Query rem = embc.createNativeQuery("select"
			+ " r.goodsid, "
			+ "sum(r.SUMREMAINDER) Stock_Volume, "
			+ "p.val "
			+ "from "
			+ "SPRACTUALREMAINDERS ('3000,3001','4066,4067,4068,4069', '"+day+"', Null,1,0,0,0) r, "
			+ "goods g, "
			+ "fixedprice p "
			+ "where"
			+ " g.id=r.goodsid "
			+ "and p.goodsid = g.id "
			+ "and p.pricetypeid = 1 "
			+ "group by 1,3");
	for (Object o : rem.getResultList()){
		Object[] cols = (Object[]) o;
		RemObj remobj = new RemObj("4", String.valueOf(day), String.valueOf(cols[0]), String.valueOf(cols[1]), String.valueOf((Double)cols[2]*(Double)cols[1]));
		remainders.add(remobj);
	}
	
	}
	return remainders;
}

public List<Client> client(){
	Map<String,String> bcprops = new HashMap<String,String>();
	bcprops.put("javax.persistence.jdbc.url", "jdbc:firebirdsql:192.168.30.156/3050:bcalter?lc_ctype=WIN1251;sql_dialect=3");
	bcprops.put("javax.persistence.jdbc.user","SYSDBA");
	bcprops.put("javax.persistence.jdbc.password","Tdhjgf");
	
	EntityManagerFactory emfbc = Persistence.createEntityManagerFactory("database",bcprops);
	EntityManager embc = emfbc.createEntityManager();
	
	
	LocalDate now = LocalDate.now();
	Query getC = embc.createNativeQuery("select distinct "
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
			+ "and dc.storeidsrc >= 3000 "
			+ "and dc.docdate >= '"+now.minusDays(45)+"'"
			+ "and dc.clientid not in (300003,300701,301271) "
			
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
	+ "and dc.storeiddst = 3000 "
	+ "and dc.docdate >=  '"+now.minusDays(45)+"'"
			+ "and dc.docdate <=  '"+now+"'"
			
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
			+ "and dc.storeidsrc >= 3000 "
			+ "and dc.docdate >=  '"+now.minusDays(45)+"'");
	List<Client> cl = new ArrayList();
	for (Object o : getC.getResultList()){
		Object[] cols = (Object[]) o;
		Client c = new Client(String.valueOf(cols[0]), String.valueOf(cols[1]), String.valueOf(cols[2]), "5");
		cl.add(c);
	}
	return cl;
	
}

public List ta(){
	
	Map<String,String> bcprops = new HashMap<String,String>();
	bcprops.put("javax.persistence.jdbc.url", "jdbc:firebirdsql:192.168.30.156/3050:bcalter?lc_ctype=WIN1251;sql_dialect=3");
	bcprops.put("javax.persistence.jdbc.user","SYSDBA");
	bcprops.put("javax.persistence.jdbc.password","Tdhjgf");
	
	EntityManagerFactory emfbc = Persistence.createEntityManagerFactory("database",bcprops);
	EntityManager embc = emfbc.createEntityManager();
	
	
	LocalDate now = LocalDate.now();
	Query getC = embc.createNativeQuery(""
			+ "select distinct "
			+ "a.id Distr_Code, a.sname TA_Name "
			+ "from  "
			+ "innermigrationdet id, goods g, agents a, doccommon dc "
			+ "where a.id = dc.agentid and dc.id = id.innermigrationid and g.id = id.goodsid and dc.STOREIDSRC >= 3000 "
			+ "and g.prodtreeid = 5062 and id.receipted = 1 and dc.signed = 3 and dc.clientid not in (300003,300701,301271) "
			+ "and dc.docdate >= '"+now.minusDays(45)+"' and dc.docdate <= '"+now+"' "
			+ "union select distinct "
			+ "a.id, a.sname "
			+ "from  "
			+ "outcomeinvoicedet id, goods g, agents a, doccommon dc "
			+ "where "
			+ "a.id = dc.agentid and dc.id = id.outcomeinvoiceid and g.id = id.goodsid and dc.STOREIDSRC >= 3000 and g.prodtreeid = 5062 "
			+ "and id.receipted = 1 and dc.SIGNED = 3 and dc.clientid not in (300003,300701,301271) and dc.docdate >= '"+now.minusDays(45)+"' "
			+ "and dc.docdate <= '"+now+"' "
			+ "union select distinct "
			+ "a.id, a.sname "
			+ "from doccommon dc, incomeinvoicedet id, goods g, agents a where a.id=dc.agentid and dc.id = id.incomeinvoiceid "
			+ "and g.id = id.goodsid and dc.STOREIDDST = 3000 and id.receipted = 1 and dc.signed = 3 "
			+ "and dc.isreturn = 1 and g.prodtreeid = 5062 and dc.clientid not in (300003,300701,301271) "
			+ "and dc.docdate >= '"+now.minusDays(45)+"' and dc.docdate <= '"+now+"'");
	return getC.getResultList();
	
}

public List sku(){
	Map<String,String> bcprops = new HashMap<String,String>();
	bcprops.put("javax.persistence.jdbc.url", "jdbc:firebirdsql:192.168.30.156/3050:bcalter?lc_ctype=WIN1251;sql_dialect=3");
	bcprops.put("javax.persistence.jdbc.user","SYSDBA");
	bcprops.put("javax.persistence.jdbc.password","Tdhjgf");
	
	EntityManagerFactory emfbc = Persistence.createEntityManagerFactory("database",bcprops);
	EntityManager embc = emfbc.createEntityManager();
	Query getSku = embc.createNativeQuery("select g.id, g.sname, g.code, g.measureid from goods g where g.prodtreeid = 5062");
	return getSku.getResultList();
	
}

public List recive(){
	
	Map<String,String> bcprops = new HashMap<String,String>();
	bcprops.put("javax.persistence.jdbc.url", "jdbc:firebirdsql:192.168.30.156/3050:bcalter?lc_ctype=WIN1251;sql_dialect=3");
	bcprops.put("javax.persistence.jdbc.user","SYSDBA");
	bcprops.put("javax.persistence.jdbc.password","Tdhjgf");
	
	EntityManagerFactory emfbc = Persistence.createEntityManagerFactory("database",bcprops);
	EntityManager embc = emfbc.createEntityManager();
	
	Query getSku = embc.createNativeQuery("select inc.docdate, i.incomeinvoiceid, i.goodsid, i.itemcount "                         //45!!!!!!!!!!!!!!!!!!!!!!!!
			+ "from incomeinvoicedet i, goods g, incomeinvoice inc "
			+ "where i.goodsid=g.id and g.prodtreeid = 5062 and inc.id = i.incomeinvoiceid and inc.docdate > '"+ LocalDate.now().minusDays(45)+"' and inc.docdate <= '"+LocalDate.now()+"' and i.itemcount > 0"); 	
	return getSku.getResultList();
	
}

}
