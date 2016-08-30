package com.rosteach.tasks;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.apache.commons.io.FilenameUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class CsvGenerator {
	FileWriter writer;
    CSVPrinter csvFilePrinter = null;
    private final String LINE_SEPARATOR = "\n";
    CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(LINE_SEPARATOR);
    String path = "C:/RepOut/spot2d_homka/";
    
	LocalDate now = LocalDate.now();
	LocalDate minus45 = now.minusDays(45);
	
	AlterDelivery ad;
	
public CsvGenerator(String s, String po) throws IOException{
	 now = LocalDate.parse(po);
	 minus45 = LocalDate.parse(s);
	 path = "C:/SPOT2D/spot2d_homka/";
	 
	 ad = new AlterDelivery(minus45, now);
	 	
	 	sku();
		delivery();
		remainder();
		clients();
		ta();
		recive();
	 
	 System.out.println(now + " AAAAAAAAAAAAAAAA "+minus45);
}



public CsvGenerator(LocalDate s, LocalDate po) throws IOException{
	 now = po;
	 minus45 = s;
	 
	 ad = new AlterDelivery(minus45, now);
	 	
	 	sku();
		delivery();
		remainder();
		clients();
		ta();
		recive();
		
	 
	 System.out.println("Homka uploaded. Job is done!");
}


public void CurlUpload(String fileName) throws ParseException, IOException{
	String path = "C:/SPOT2D/";
	Runtime.getRuntime().exec(path+fileName); //cmd /c 
	
	/*File[] files = new File(path).listFiles();
	
	for(File f : files){
	    HttpEntity entity = MultipartEntityBuilder.create().addTextBody("__login", "alters").addTextBody("__password", "UOcla967").addTextBody("__did", "4").addBinaryBody(f.getName(), f, ContentType.create("text/plain; charset=UTF-8; filename="+f.getName().substring(0, f.getName().lastIndexOf("."))), f.getName().substring(0, f.getName().lastIndexOf("."))).build(); //.substring(0, f.getName().lastIndexOf("."))  // application/octet-stream
	    
	    //entity.addPart("file", new FileBody(f));
	    
	    HttpPost request = new HttpPost("http://lsnack.spot2d.com/dinfo/auto-upload.phtml");
	    request.setEntity(entity);
	    
	    
	    HttpClient client = new DefaultHttpClient();
	    HttpResponse response = client.execute(request);
	    System.out.println(response.getStatusLine().getStatusCode()+ " RESPONSE STATUS ");
	
	
	}
*/
	

}



private void sku() throws IOException{
	
	
	writer = new FileWriter(path+"sku.csv");
	CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader("ID дистрибьютора;Код продукта дистрибьютора;Штришкод;Название продукта;ID единицы измерения продукта").withEscape('\\').withQuoteMode(QuoteMode.NONE); //"id","code","name","measureid"
	csvFilePrinter = new CSVPrinter(writer, csvFileFormat);

/*		for (Object o : new BcDelivery().sku()){
		Object[] cols = (Object[]) o;		
			csvFilePrinter.printRecord("5;"+cols[0]+";"+cols[2]+";"+cols[1]+";"+cols[3]); 
	}
	*/
	for (Object o : ad.sku()){
		Object[] cols = (Object[]) o;		
			csvFilePrinter.printRecord("4;"+cols[0]+";"+cols[2]+";"+cols[1]+";"+cols[3]); 
			
			
	}
			
	writer.flush();
	writer.close();
	csvFilePrinter.close();
}


private void delivery() throws IOException{
	
	List<Delivery> del = ad.delivery();
	//del.addAll(new BcDelivery().delivery());
	writer = new FileWriter(path+"delivery.csv");
	CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader("ID дистрибьютора;Код клиента ERP;Дата;Код продукта дистрибьютора;Количество;Сумма отгрузки;Код ТА;Закупочная цена;Номер расходной накладной").withEscape('\\').withQuoteMode(QuoteMode.NONE); //"id","code","name","measureid"
	csvFilePrinter = new CSVPrinter(writer, csvFileFormat);
	for (Delivery d : del){
		csvFilePrinter.printRecord(d.getDistr_id()+";"+d.getClientId()+";"+d.getDocDate()+";"+d.getProdCode()+";"+d.getItemCount()+";"+d.getAmount()+";"+d.getTa()+";"+d.getPrice()+";"+d.getOutcomeInvoice());//s.getId()+";"+s.getCode()+";"+s.name+";"+s.getMeasureid()
	
		
	}
	
	writer.flush();
	writer.close();
	csvFilePrinter.close();

}

public void remainder() throws IOException{
	LocalDate today = LocalDate.now();
	writer = new FileWriter(path+"stocks.csv");
	CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader("ID дистрибьютора;Дата;Код продукта дистрибьютора;Количество;Сумма в закупочных ценах").withEscape('\\').withQuoteMode(QuoteMode.NONE); //"id","code","name","measureid"
	csvFilePrinter = new CSVPrinter(writer, csvFileFormat);
	
	List<RemObj> bcrem = ad.stocks();
	//bcrem.addAll(new BcDelivery().stocks());
	
	for (RemObj o : bcrem){
		csvFilePrinter.printRecord(o.getSklad()+";"+o.getDate()+";"+o.getProdCode()+";"+o.getCol()+";"+o.getSumPrice());
		
	}
	writer.flush();
	writer.close();
	csvFilePrinter.close();
	

	
}

public void clients() throws IOException{
	
	writer = new FileWriter(path+"clients.csv");
	CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader("ID дистрибьютора;Код клиента ERP;Название клиента;Адрес клиента").withEscape('\\').withQuoteMode(QuoteMode.NONE); //"id","code","name","measureid"
	csvFilePrinter = new CSVPrinter(writer, csvFileFormat);
	
	List<Client> clients =ad.client();
	//clients.addAll(new BcDelivery().client());
	
	for (Client o : clients){
		csvFilePrinter.printRecord(o.location+";"+o.clientCode+";"+o.clientName+";"+o.clientAdr);
		
	}
	writer.flush();
	writer.close();
	csvFilePrinter.close();
	
}

public void ta() throws IOException{
	
	writer = new FileWriter(path+"ta.csv");
	CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader("ID дистрибьютора;Код ТА;Имя ТА").withEscape('\\').withQuoteMode(QuoteMode.NONE); //"id","code","name","measureid"
	csvFilePrinter = new CSVPrinter(writer, csvFileFormat);
	
	/*for (Object o : new BcDelivery().ta()){
		Object[] cols = (Object[]) o;		
			csvFilePrinter.printRecord("5;"+cols[0]+";"+cols[1]); 
	}*/
	for (Object o : ad.ta()){
		Object[] cols = (Object[]) o;		
			csvFilePrinter.printRecord("4;"+cols[0]+";"+cols[1]); 
	}
	writer.flush();
	writer.close();
	csvFilePrinter.close();
	
	}


public void recive() throws IOException{
	
	LocalDate today = LocalDate.now();
	writer = new FileWriter(path+"receive.csv");
	CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader("ID дистрибьютора;Дата;Код продукта дистрибьютора;Количество").withEscape('\\').withQuoteMode(QuoteMode.NONE); //"id","code","name","measureid"
	csvFilePrinter = new CSVPrinter(writer, csvFileFormat);
	
	/*for (Object o : new BcDelivery().recive()){
		Object[] cols = (Object[]) o;		
			csvFilePrinter.printRecord("5;"+cols[0]+";"+cols[2]+";"+cols[3]); 
	}*/
	
	for (Object o : ad.recive()){
		Object[] cols = (Object[]) o;		
			csvFilePrinter.printRecord("4;"+cols[0]+";"+cols[2]+";"+cols[3]);
			
			
	}
	writer.flush();
	writer.close();
	csvFilePrinter.close();
	

}

}
