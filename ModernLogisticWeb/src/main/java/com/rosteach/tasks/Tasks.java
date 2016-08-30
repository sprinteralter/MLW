package com.rosteach.tasks;


import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;




import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

@EnableScheduling
@Repository
public class Tasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    String fileName;
    FileWriter writer;
    CSVPrinter csvFilePrinter = null;
    
    private final String LINE_SEPARATOR = "\n";
    CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(LINE_SEPARATOR);
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    
    
    
   List<String> oborot = new ArrayList<String>();
    int res = 0;
    
    @Scheduled(cron="01 00 03 * * ?") //fixedDelay=2500000) //
    public void reportCurrentTime() throws IOException {
    	/*sku();
		delivery();
		remainder();
		clients();
		ta();
		recive();*/
    	
    	/*System.out.println("Start");
		FTPUploader ftpUploader = new FTPUploader("ftp.journaldev.com", "ftpUser", "ftpPassword");
		//FTP server path is relative. So if FTP account HOME directory is "/home/pankaj/public_html/" and you need to upload 
		// files to "/home/pankaj/public_html/wp-content/uploads/image2/", you should pass directory parameter as "/wp-content/uploads/image2/"
		ftpUploader.uploadFile("D:\\Pankaj\\images\\MyImage.png", "image.png", "/wp-content/uploads/image2/");
		ftpUploader.disconnect();
		System.out.println("Done");*/
		
    	new CsvGenerator(LocalDate.now().minusDays(45), LocalDate.now()).CurlUpload("uploadAuto.bat");
        System.out.println("The time is now " + dateFormat.format(new Date())+" Job is DONE ");
               
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