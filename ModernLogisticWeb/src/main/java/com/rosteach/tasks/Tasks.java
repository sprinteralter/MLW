package com.rosteach.tasks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.apache.commons.io.IOUtils;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

//@EnableScheduling
@Repository
public class Tasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    String fileName;
    FileWriter writer;
    CSVPrinter csvFilePrinter = null;
    
    private final String LINE_SEPARATOR = "\n";
    CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(LINE_SEPARATOR);
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    private static String timer =  "01 43 14 * * ?";
    
    //@Scheduled(cron="01 43 14 * * ?")
    public void reportCurrentTime() throws IOException {
    	sku();
		/*delivery();
		remainder();
		clients();
		ta();
		recive();*/
		
        System.out.println("The time is now " + dateFormat.format(new Date())+" Job is DONE" );
        

}
    
    private void sku() throws IOException{
    	Map<String,String> props = new HashMap<String,String>();
		props.put("javax.persistence.jdbc.url", "jdbc:firebirdsql:192.168.20.17/3050:alter?lc_ctype=WIN1251;sql_dialect=3");
		props.put("javax.persistence.jdbc.user","SYSDBA");
		props.put("javax.persistence.jdbc.password","strongpass");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("database",props);
		EntityManager em = emf.createEntityManager();
		
		Query q = em.createNativeQuery("select g.id, g.code, g.name, g.measureid from SPRGOODS (Null,Null,Null,Null,5062,0) g");
		List<Sku> sku = new ArrayList();
		
		for (Object o : q.getResultList()){
			Object[] cols = (Object[]) o;
			Sku s = new Sku();
			s.setId(String.valueOf((int)cols[0]));
			s.setCode(String.valueOf(cols[1]));
			s.setName(String.valueOf(cols[2]));
			s.setMeasureid(String.valueOf(cols[3]));
			sku.add(s);
		}
		
		writer = new FileWriter("C:/RepOut/spot2d_homka/sku.csv");
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader("ID дистрибьютора;Код продукта дистрибьютора;Штришкод;Название продукта;ID единицы измерения продукта").withEscape('\\').withQuoteMode(QuoteMode.NONE); //"id","code","name","measureid"
		csvFilePrinter = new CSVPrinter(writer, csvFileFormat);

		for (Sku s : sku){
			if (s.getMeasureid().equals(3))
				s.setMeasureid("4");
			
			if (s.getMeasureid().equals(7))
				s.setMeasureid("3");
			
			if(s.getMeasureid().equals(0) || Integer.valueOf(s.getMeasureid()) > 3 )
				s.setMeasureid("4");
			
			
			csvFilePrinter.printRecord("4"+";"+s.getId()+";"+s.getCode()+";"+s.name+";"+s.getMeasureid());//s.getId()+";"+s.getCode()+";"+s.name+";"+s.getMeasureid()
		
		}
		writer.flush();
		writer.close();
		csvFilePrinter.close();
    }

    private void delivery() throws IOException{
    	LocalDate today = LocalDate.now();
    	LocalDate minus45 = today.minusDays(45);
    	List<Delivery> del = new ArrayList();
    	
    	Map<String,String> props = new HashMap<String,String>();
		props.put("javax.persistence.jdbc.url", "jdbc:firebirdsql:192.168.20.17/3050:alter?lc_ctype=WIN1251;sql_dialect=3");
		props.put("javax.persistence.jdbc.user","SYSDBA");
		props.put("javax.persistence.jdbc.password","strongpass");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("database",props);
		EntityManager em = emf.createEntityManager();
		
    	Query form1 = em.createNativeQuery("select "
    			+ "c.id Client_Code, "
    			+ "dc.docdate Del_Date, "
    			+ "trim(cast(UDFUREPLACESTR(g.code1, ',', '') as varchar(20))) SKU_Code,"
    			+ " id.itemcount Del_Volume, "
    			+ "dc.mainsumm Del_Amount,"
    			+ " dc.agentid TA, "
    			+ "id.goodsid, "
    			+ "p.VALWITHOVERH price, "
    			+ "id.outcomeinvoiceid, "
    			+ "dc.storeidsrc "
    			+ "from "
    			+ "doccommon dc,"
    			+ " outcomeinvoicedet id,"
    			+ " goods g, "
    			+ "client c,"
    			+ " SPRFIXEDPRICE (1,Null,0,Null,5,5062,0,Null) p "
    			+ "where c.id=dc.clientid "
    			+ "and dc.id = id.outcomeinvoiceid "
    			+ "and g.id = id.goodsid "
    			+ "and dc.STOREIDSRC != (820) "
    			+ "and id.receipted = 1 "
    			+ "and dc.signed = 3 "
    			+ "and dc.isreturn = 0 "
    			+ "and g.prodtreeid in (5062) "
    			+ "and dc.clientid not in (907,4151,8169,300701,14970) "
    			+ "and dc.docdate >= '"+minus45+"'"
    			+ " and dc.docdate <= '"+today+"'"
    			+ " and dc.id not in (select bi.outcomeinvoiceid from bill bi) "
    			+ "and p.goodsid = g.id");
    	
    	Query form2 = em.createNativeQuery(""
    			+ "select c.id Client_Code, "
    			+ "dc.docdate Del_Date, "
    			+ "trim(cast(UDFUREPLACESTR(g.code1, ',', '') as varchar(20))) SKU_Code,"
    			+ " id.itemcount Del_Volume, "
    			+ "dc.mainsumm Del_Amount,"
    			+ "dc.agentid TA, "
    			+ "g.id, "
    			+ "p.VALWITHOVERH price,"
    			+ "id.innermigrationid, "
    			+ "dc.storeidsrc "
    			+ "from "
    			+ "doccommon dc,"
    			+ " innermigrationdet id,"
    			+ " goods g,"
    			+ " client c, "
    			+ "SPRFIXEDPRICE (1,Null,0,Null,5,5062,0,Null) p "
    			+ "where c.id=dc.clientid "
    			+ "and dc.id = id.innermigrationid "
    			+ "and g.id = id.goodsid"
    			+ " and id.receipted = 1 "
    			+ "and dc.storeidsrc in (0,3000)"
    			+ " and dc.signed = 3"
    			+ " and dc.isreturn = 0"
    			+ " and g.prodtreeid in (5062)"
    			+ " and dc.clientid not in (907,4151,8169, 300701,14970,3219)"
    			+ " and dc.docdate >= '"+minus45+"'"
    			+ " and dc.docdate <= '"+today+"'"
    			+ "and dc.id not in (select bi.outcomeinvoiceid from bill bi)"
    			+ " and p.goodsid = g.id ");
    	
    	Query back = em.createNativeQuery("select"
    			+ " c.id Client_Code,"
    			+ " dc.docdate Del_Date,"
    			+ " UDFUREPLACESTR(g.code1, ',', '') SKU_Code,"
    			+ " id.itemcount * -1 Del_Volume,"
    			+ " dc.mainsumm * -1 Del_Amount,"
    			+ " dc.agentid TA,"
    			+ " g.id,"
    			+ " p.VALWITHOVERH price,"
    			+ " id.incomeinvoiceid, "
    			+ " dc.storeidsrc "
    			+ "from doccommon dc,"
    			+ " incomeinvoicedet id,"
    			+ " goods g,"
    			+ " client c,"
    			+ " SPRFIXEDPRICE (1,Null,0,Null,5,5062,0,Null) p "
    			+ "where c.id=dc.clientid "
    			+ "and dc.id = id.incomeinvoiceid "
    			+ "and g.id = id.goodsid and dc.STOREIDDST in (0,3000) "
    			+ "and id.receipted = 1 "
    			+ "and dc.signed = 3 "
    			+ "and dc.isreturn = 1 "
    			+ "and g.prodtreeid in (5062) "
    			+ "and dc.clientid not in (907,4151,8169, 300701,14970,3219) "
    			+ "and dc.docdate >= '2016-07-01' "
    			+ "and dc.docdate <= '2016-08-01' "
    			+ "and dc.id not in (select bi.outcomeinvoiceid from bill bi) "
    			+ "and p.goodsid = g.id");
    	
    	writer = new FileWriter("C:/RepOut/spot2d_homka/delivery.csv");
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader("ID дистрибьютора;Код клиента ERP;Дата;Код продукта дистрибьютора;Количество;Сумма отгрузки;Код ТА;Закупочная цена;Номер расходной накладной").withEscape('\\').withQuoteMode(QuoteMode.NONE); //"id","code","name","measureid"
		csvFilePrinter = new CSVPrinter(writer, csvFileFormat);
		List<Integer> sklads = new ArrayList();
		
		for (Object d : form1.getResultList()){
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
			s.setDistr_id("4");
			Integer skladId = ((Integer) cols[9]);
			
			
			if (skladId != null && (skladId== 3000 || skladId == 3005 || skladId == 3010 || skladId==3015 || skladId == 3020)){
				s.setDistr_id("5");
			} else s.setDistr_id("4");
			del.add(s);
		}
		
		for (Object d : form2.getResultList()){
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
			
			Integer skladId = ((Integer) cols[9]);
			if (skladId != null && (skladId== 3000 || skladId == 3005 || skladId == 3010 || skladId==3015 || skladId == 3020)){
				s.setDistr_id("5");
			} else s.setDistr_id("4");
			del.add(s);
		}
		
		for (Object d : back.getResultList()){
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
			Integer skladId = ((Integer) cols[9]);
			
			if (skladId != null && (skladId== 3000 || skladId == 3005 || skladId == 3010 || skladId==3015 || skladId == 3020)){
				s.setDistr_id("5");
			} else s.setDistr_id("4");
			
			del.add(s);
		}
		
		
		for (Delivery d : del){
			csvFilePrinter.printRecord(d.getDistr_id()+";"+d.getClientId()+";"+d.getDocDate()+";"+d.getProdCode()+";"+d.getItemCount()+";"+d.getAmount()+";"+d.getTa()+";"+d.getPrice()+";"+d.getOutcomeInvoice());//s.getId()+";"+s.getCode()+";"+s.name+";"+s.getMeasureid()
		}
		
		
		
		writer.flush();
		writer.close();
		csvFilePrinter.close();

    }
    
    public void remainder() throws IOException{
    	Map<String,String> props = new HashMap<String,String>();
		props.put("javax.persistence.jdbc.url", "jdbc:firebirdsql:192.168.20.17/3050:alter?lc_ctype=WIN1251&columnLabelForName=true;sql_dialect=3");
		props.put("javax.persistence.jdbc.user","SYSDBA");
		props.put("javax.persistence.jdbc.password","strongpass");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("database",props);
		EntityManager em = emf.createEntityManager();
		LocalDate today = LocalDate.now();
		writer = new FileWriter("C:/RepOut/spot2d_homka/stocks.csv");
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader("ID дистрибьютора;Дата;Код продукта дистрибьютора;Количество;Сумма в закупочных ценах").withEscape('\\').withQuoteMode(QuoteMode.NONE); //"id","code","name","measureid"
		csvFilePrinter = new CSVPrinter(writer, csvFileFormat);
		
		for (int i = 0; i < 46; i++){
			LocalDate day = today.minusDays(i);
		
		Query remainder = em.createNativeQuery("select "
				+ "sum(r.SUMREMAINDER),"
				+ " r.goodsid,"
				+ " c.val"
				+ " from"
				+ " SPRACTUALREMAINDERS ('0','4066,4067,4068,4069', '"+day+"', Null,1,0,0,0) r,"
				+ " goods g,"
				+ " SPRFIXEDPRICE_BYGOODS (r.goodsid,0,Null) c "
				+ "where"
				+ " g.id=r.goodsid"
				+ " and c.pricetypeid = 1 group by 2,3");
				
		Query remainderBC = em.createNativeQuery(" select"
				+ "  sum(r.SUMREMAINDER),"
				+ " r.goodsid,"
				+ " c.val"
				+ " from"
				+ " SPRACTUALREMAINDERS ('3000','4066,4067,4068,4069', '"+day+"', Null,1,0,0,0) r,"
				+ " goods g,"
				+ " SPRFIXEDPRICE_BYGOODS (r.goodsid,0,Null) c"
				+ " where "
				+ "g.id=r.goodsid and c.pricetypeid = 1 group by 2,3");
		
		
		
		
		for (Object o : remainder.getResultList()){
			Object[] cols = (Object[]) o;
			csvFilePrinter.printRecord("4;"+day+";"+cols[1]+";"+cols[0]+";"+(((Double)cols[2])*((Double)cols[0])));
			
		}
		
		for (Object o : remainderBC.getResultList()){
			Object[] cols = (Object[]) o;
			csvFilePrinter.printRecord("5;"+day+";"+cols[1]+";"+cols[0]+";"+(((Double)cols[2])*((Double)cols[0])));
			
		}
		}
		writer.flush();
		writer.close();
		csvFilePrinter.close();
    	
    }
    
    public void clients() throws IOException{
    	Map<String,String> props = new HashMap<String,String>();
		props.put("javax.persistence.jdbc.url", "jdbc:firebirdsql:192.168.20.17/3050:alter?lc_ctype=WIN1251&columnLabelForName=true;sql_dialect=3");
		props.put("javax.persistence.jdbc.user","SYSDBA");
		props.put("javax.persistence.jdbc.password","strongpass");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("database",props);
		EntityManager em = emf.createEntityManager();
		LocalDate today = LocalDate.now();
		
		Query clients = em.createNativeQuery("select distinct "
				+ "c.id, "
				+ "c.sname, "
				+ "c.addresslocation "
				+ "from "
				+ "innermigrationdet id,"
				+ " goods g, client c,"
				+ " doccommon dc"
				+ " where"
				+ " c.id = dc.clientid "
				+ "and dc.id = id.innermigrationid "
				+ "and g.id = id.goodsid "
				+ "and g.prodtreeid in (5062) "
				+ "and id.receipted = 1 "
				+ "and dc.SIGNED = 3 "
				+ "and dc.clientid not in (907,4151,8169,300701,14970) "
				+ "and dc.docdate >= '"+today.minusDays(45)+"'"
				+ "and dc.docdate <= '"+today+"'"
				+ "union "
				+ "select distinct "
				+ "c.id, "
				+ "c.sname, "
				+ "c.addresslocation "
				+ "from  "
				+ "outcomeinvoicedet id,"
				+ " goods g,"
				+ " client c,"
				+ " doccommon dc "
				+ "where "
				+ "c.id = dc.clientid "
				+ "and dc.id = id.outcomeinvoiceid "
				+ "and g.id = id.goodsid "
				+ "and g.prodtreeid in (5062) "
				+ "and id.receipted = 1 "
				+ "and dc.SIGNED = 3 "
				+ "and dc.clientid not in (907,4151,8169,300701,14970) "
				+ "and dc.docdate >= '"+today.minusDays(45)+"'"
				+ "and dc.docdate <= '"+today+"'");
		
		writer = new FileWriter("C:/RepOut/spot2d_homka/clients.csv");
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader("ID дистрибьютора;Код клиента ERP;Название клиента;Адрес клиента").withEscape('\\').withQuoteMode(QuoteMode.NONE); //"id","code","name","measureid"
		csvFilePrinter = new CSVPrinter(writer, csvFileFormat);
		
		for (Object o : clients.getResultList()){
			Object[] cols = (Object[]) o;
			csvFilePrinter.printRecord("4;"+cols[0]+";"+cols[1]+";"+cols[2]);
			
		}
		writer.flush();
		writer.close();
		csvFilePrinter.close();
		
    }
    
    public void ta() throws IOException{
    	Map<String,String> props = new HashMap<String,String>();
		props.put("javax.persistence.jdbc.url", "jdbc:firebirdsql:192.168.20.17/3050:alter?lc_ctype=WIN1251&columnLabelForName=true;sql_dialect=3");
		props.put("javax.persistence.jdbc.user","SYSDBA");
		props.put("javax.persistence.jdbc.password","strongpass");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("database",props);
		EntityManager em = emf.createEntityManager();
		LocalDate today = LocalDate.now();
		
		Query ta = em.createNativeQuery("select distinct "
				+ "a.id Distr_Code, "
				+ "a.sname TA_Name, "
				+ "dc.storeidsrc "
				+ "from  "
				+ "innermigrationdet id, "
				+ "goods g, "
				+ "agents a, "
				+ "doccommon dc "
				+ "where "
				+ "a.id = dc.agentid "
				+ "and dc.id = id.innermigrationid "
				+ "and g.id = id.goodsid "
				+ "and dc.STOREIDSRC < 3021 "
				+ "and g.prodtreeid in (5062) "
				+ "and id.receipted = 1 "
				+ "and dc.signed = 3 "
				+ "and dc.clientid not in (907,4151,8169,14970) "
				+ "and dc.docdate >= '"+today.minusDays(45)+"' "
				+ "and dc.docdate <= '"+today+"' "
				+ "union select distinct "
				+ "a.id, a.sname, "
				+ "dc.storeidsrc "
				+ "from  "
				+ "outcomeinvoicedet id, "
				+ "goods g, "
				+ "agents a, "
				+ "doccommon "
				+ "dc where a.id = dc.agentid "
				+ "and dc.id = id.outcomeinvoiceid"
				+ " and g.id = id.goodsid "
				+ "and dc.STOREIDSRC < 3021 "
				+ "and g.prodtreeid in (5062) "
				+ "and id.receipted = 1"
				+ " and dc.SIGNED = 3 "
				+ "and dc.clientid not in (907,4151,8169,14970)"
				+ " and dc.docdate >= '"+today.minusDays(45)+"' "
				+ " and dc.docdate <=  '"+today+"' "
				+ " union select distinct"
				+ " a.id,"
				+ " a.sname,"
				+ " dc.storeidsrc"
				+ " from "
				+ "doccommon dc,"
				+ " incomeinvoicedet id,"
				+ " goods g,"
				+ " agents a "
				+ "where a.id=dc.agentid "
				+ "and dc.id = id.incomeinvoiceid "
				+ "and g.id = id.goodsid "
				+ "and dc.STOREIDDST = 0 "
				+ "and id.receipted = 1 "
				+ "and dc.signed = 3 "
				+ "and dc.isreturn = 1 "
				+ "and g.prodtreeid in (5062) "
				+ "and g.HIDDEN = 0 "
				+ "and dc.clientid not in (907,4151,8169, 300701,14970) "
				+ " and dc.docdate >= '"+today.minusDays(45)+"' "
				+ " and dc.docdate <=  '"+today+"' "
				);
		writer = new FileWriter("C:/RepOut/spot2d_homka/ta.csv");
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader("ID дистрибьютора;Код ТА;Имя ТА").withEscape('\\').withQuoteMode(QuoteMode.NONE); //"id","code","name","measureid"
		csvFilePrinter = new CSVPrinter(writer, csvFileFormat);
		
		for (Object o : ta.getResultList()){
			Object[] cols = (Object[]) o;
			
			if (cols[2] != null && (int)cols[2] > 2999 && (int)cols[2] < 3021){
				csvFilePrinter.printRecord("5;"+cols[0]+";"+cols[1]);
			} else csvFilePrinter.printRecord("4;"+cols[0]+";"+cols[1]);
			
		}
		writer.flush();
		writer.close();
		csvFilePrinter.close();
		
		}
    
    
    public void recive() throws IOException{
    	Map<String,String> props = new HashMap<String,String>();
		props.put("javax.persistence.jdbc.url", "jdbc:firebirdsql:192.168.20.17/3050:alter?lc_ctype=WIN1251&columnLabelForName=true;sql_dialect=3");
		props.put("javax.persistence.jdbc.user","SYSDBA");
		props.put("javax.persistence.jdbc.password","strongpass");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("database",props);
		EntityManager em = emf.createEntityManager();
		LocalDate today = LocalDate.now();
		writer = new FileWriter("C:/RepOut/spot2d_homka/recive.csv");
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader("ID дистрибьютора;Дата;Кодпродукта дистрибьютора;Количество").withEscape('\\').withQuoteMode(QuoteMode.NONE); //"id","code","name","measureid"
		csvFilePrinter = new CSVPrinter(writer, csvFileFormat);
		Query recive = em.createNativeQuery("select  docdate, id, storeid from SPRINCOMEINVOICE (Null, Null, '"+today.minusDays(45)+"','"+today+"', Null, Null, Null, 2011) ");
		
		for (Object ids : recive.getResultList()){
			Object[] c = (Object[]) ids;
			Query details  = em.createNativeQuery("select goodsid, itemcount from SPRINCOMEINVOICEDET ("+c[1]+",Null,0,119,Null,Null, Null,5062,0)");
			for (Object dets : details.getResultList()){
				Object[] d = (Object[]) dets;
				
				if (c[2] != null && (int)c[2] > 2999 && (int)c[2] < 3021){
					csvFilePrinter.printRecord("5;"+c[0]+";"+d[0]+";"+d[1]);
				} else csvFilePrinter.printRecord("4;"+c[0]+";"+d[0]+";"+d[1]);
			}
		}
		
		writer.flush();
		writer.close();
		csvFilePrinter.close();
    }
    
}

class Sku{
	public Sku(){};
	public String id;
	public String code;
	public String name;
	public String measureid;
	String distr_id;
	
	
	public String getDistr_id() {
		return distr_id;
	}
	public void setDistr_id(String distr_id) {
		this.distr_id = distr_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMeasureid() {
		return measureid;
	}
	public void setMeasureid(String measureid) {
		this.measureid = measureid;
	}
	
@Override
public String toString() {
	return "Sku [id="+id+", code="+code+", name="+name+", measureid="+measureid+"]";
		
	}

}

class Delivery{   
	String clientId;
	String docDate;
	String skuCode;
	String itemCount;
	String Amount;
	String ta;
	String prodCode;
	String price;
	String outcomeInvoice;
	String distr_id;
	
	
	public Delivery(){}
	
	
	
	public String getDistr_id() {
		return distr_id;
	}



	public void setDistr_id(String distr_id) {
		this.distr_id = distr_id;
	}



	public String getPrice() {
		return price;
	}



	public void setPrice(String price) {
		this.price = price;
	}



	public String getOutcomeInvoice() {
		return outcomeInvoice;
	}



	public void setOutcomeInvoice(String outcomeInvoice) {
		this.outcomeInvoice = outcomeInvoice;
	}



	public String getProdCode() {
		return prodCode;
	}


	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}


	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getDocDate() {
		return docDate;
	}
	public void setDocDate(String docDate) {
		this.docDate = docDate;
	}
	public String getSkuCode() {
		return skuCode;
	}
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}
	public String getItemCount() {
		return itemCount;
	}
	public void setItemCount(String itemCount) {
		this.itemCount = itemCount;
	}
	public String getAmount() {
		return Amount;
	}
	public void setAmount(String amount) {
		Amount = amount;
	}
	public String getTa() {
		return ta;
	}
	public void setTa(String ta) {
		this.ta = ta;
	}
	
	
}