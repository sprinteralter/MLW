package com.rosteach.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.rosteach.DAO.security.GetDetails;
import com.rosteach.connection.FTPConnectionEDI;
import com.rosteach.entities.COMDOC;
import com.rosteach.entities.ClientRequest;
import com.rosteach.entities.ClientRequestDetails;
//import com.rosteach.entities.ClientsRequests;
import com.rosteach.entities.DataBind;
import com.rosteach.entities.EntityManagerReferee;
import com.rosteach.entities.Links;
import com.rosteach.entities.ResultLog;
import com.rosteach.entities.SPROutcomeInvoice;
//import com.rosteach.services.ClientsRequestsService;
import com.rosteach.services.SPROutcomeInvoiceService;
import com.rosteach.services.databinding.DataBindingService;
import com.rosteach.util.JsonMapperUtil;
import com.rosteach.util.QueryManagerUtil;
import com.rosteach.validators.QueryValidator;
import com.rosteach.xmlgen.XmlGenerator;

@RestController
@RequestMapping(value= "/data")
public class DataRestController {

	@Autowired
	private SPROutcomeInvoiceService invoicesService;
	
	/*@Autowired
	private ClientsRequestsService requestsService;*/
	
	@Autowired
	private DataBindingService dataService;
	
	/**
	 * method for get start page invoices 
	 * */
	@RequestMapping(value="/getInvoices", method=RequestMethod.GET, produces={"application/json; charset=UTF-8"})
	public ResponseEntity<List<SPROutcomeInvoice>> getAllClientsRequests(){
		return new ResponseEntity<List<SPROutcomeInvoice>>(invoicesService.getInvoicesByLocalDate(""),HttpStatus.OK);
	}
	
	/**
	 * method for get start page invoices according to specific options
	 * @throws IOException 
	 * @throws JsonProcessingException 
	 * */
	@RequestMapping(value="/refreshGrid", method=RequestMethod.PUT, produces={"application/json; charset=UTF-8"})
	public ResponseEntity<List<SPROutcomeInvoice>> refreshGrid(@RequestBody String options) throws JsonProcessingException, IOException{
		System.out.println("------------------"+JsonMapperUtil.getDateFromOptions(options));
		return new ResponseEntity<List<SPROutcomeInvoice>>(invoicesService.getInvoicesByLocalDate(JsonMapperUtil.getDateFromOptions(options)),HttpStatus.OK);
	}
	
	@RequestMapping(value="/connectToFtpEDI", method=RequestMethod.GET, produces={"application/json; charset=UTF-8"})
	public ResponseEntity<List<ResultLog>> checkConnectionFTP(@RequestHeader("key") int option){
		System.out.println("sendoption----------------"+option);
		List<ResultLog> result = new LinkedList<ResultLog>();
		ResultLog res= new ResultLog();
		String path="";
		if(option==2){
			path = "C:/MLW/XMLDESADV/"+LocalDate.now()+"/";
		}else if(option==1){
			path = "C:/MLW/XMLORDERSP/"+LocalDate.now()+"/";
		}
		/*else if(option==3){
			path = "C:/MLW/XMLCOMDOC/"+LocalDate.now()+"/";
		}*/
		FTPConnectionEDI connection = new FTPConnectionEDI();
		
			boolean check = connection.getConnection("uasprintftp","c4eea2d5");
			
			if(check==true){
				boolean send = connection.sendFiles(path);
				
				if(send){
					res.setTotalInfo("Отправлено!");
					res.setTotalname("Отправка документов на сервера EDI успешна!");
					result.add(res);
				}else{
					res.setTotalInfo("Ошибка!");
					res.setTotalname("Ошибка при передачи файлов, обратитесь к системному администратору!");
					result.add(res);
				}
			}
			else{
				res.setTotalInfo("Ошибка!");
				res.setTotalname("Взможно связь с сервером отсутствует, либо отсутствуют файлы для отправки!");
				result.add(res);
			}
		return new ResponseEntity<List<ResultLog>>(result,HttpStatus.OK);
	}
	/**
	 * method for generating all needed data for xml confirmation
	 * */
	@RequestMapping(value="/confirm", method=RequestMethod.POST, produces={"application/json; charset=UTF-8"})
	public ResponseEntity<List<ResultLog>> confirmRequests(@RequestBody String request,@RequestHeader("key") int option){
		System.out.println("---------------option:   "+option);
		EntityManager entityManager = new EntityManagerReferee().getConnection();
		entityManager.getTransaction().begin();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SQL"); 
	    EntityManager em =  emf.createEntityManager();
	    em.getTransaction().begin();
	    
	    List<ResultLog> result = new QueryValidator().checkAllForXMLGen(request, entityManager, em);
	    em.getTransaction().commit();
		em.clear();
		em.close();
		emf.close();
		
		EntityManagerFactory emfBird = entityManager.getEntityManagerFactory();
		entityManager.getTransaction().commit();
		entityManager.close();
		emfBird.close();
		
		XmlGenerator generator = new XmlGenerator();
		if(result.size()==0){
			if(option==2){
				result = generator.generateNotification(request);
			}else if(option==1){
				result = generator.generateConfirmation(request);
			}
			else if(option==3){
				result = generator.generateCOMDOC(request);;
			}
		}
		return new ResponseEntity<List<ResultLog>>(result,HttpStatus.OK);
	}
	/**
	 * method for transfer data between databases
	 * */
	@RequestMapping(value = "/databind", method=RequestMethod.POST,consumes={"application/json"}, produces={"application/json; charset=UTF-8"})
	public String bindData(@RequestBody String request){
		System.out.println("----------------------------"+request);
		String name = new Gson().fromJson(request, DataBind.class).getName();
		String password = new Gson().fromJson(request, DataBind.class).getPassword();
		String dataFrom = new Gson().fromJson(request, DataBind.class).getDatabaseFrom();
		String dataTo = new Gson().fromJson(request, DataBind.class).getDatabaseTo();
		String ids = new Gson().fromJson(request, DataBind.class).getIds();
		String startDate = new Gson().fromJson(request, DataBind.class).getStartDate();
		String endDate = new Gson().fromJson(request, DataBind.class).getEndDate();
		
		System.out.println("name---------------"+name);
		HashMap<ClientRequest,List<ClientRequestDetails>> service = dataService.getClientsRequests("alter", "SYSDBA", "strongpass", "335216,335217", "13.07.2016", "13.07.2016");
		
		return dataService.setClientsRequestsWithDetails(service,"sprinter","SYSDBA","strongpass");
		//new ResponseEntity<List<ClientRequest>>(dataService.getRequests("alter", name, password, ""),HttpStatus.OK);ResponseEntity<List<ClientRequest>>
    }
	/**
	 * method for ftp connection and getting comdocs as json collection
	 * */
	@RequestMapping(value="/comdocs", method=RequestMethod.GET, produces={"application/json; charset=UTF-8"})
	public ResponseEntity<List<COMDOC>> getAllCOMDOCS(){
		FTPConnectionEDI ftp = new FTPConnectionEDI();
		boolean connection = ftp.getConnection("uasprinterk", "b279bedf");
		List<COMDOC> result=null;
		if(connection){
			try {
				ftp.getCOMDOCS("inbox/");
				result = new XmlGenerator().getLinks();
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("reaultList-----------"+result.size());
		return new ResponseEntity<List<COMDOC>>(result,HttpStatus.OK);
	}
	/**
	 * method for ftp connection and getting comdocs as json collection
	 * */
	@RequestMapping(value="/comdoc", method=RequestMethod.GET, produces={"application/json; charset=UTF-8"})
	public ResponseEntity<List<COMDOC>> signComdoc(@RequestHeader("key") int id){
		List<COMDOC> result=null;
		System.out.println("requestid-------------------------- "+id);
		return new ResponseEntity<List<COMDOC>>(result,HttpStatus.OK);
	}
	
}