package com.rosteach.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.rosteach.DAO.security.GetDetails;
import com.rosteach.connection.FTPConnectionEDI;
import com.rosteach.entities.ClientRequest;
import com.rosteach.entities.ClientRequestDetails;
//import com.rosteach.entities.ClientsRequests;
import com.rosteach.entities.DataBind;
import com.rosteach.entities.ResultLog;
import com.rosteach.entities.SPROutcomeInvoice;
//import com.rosteach.services.ClientsRequestsService;
import com.rosteach.services.SPROutcomeInvoiceService;
import com.rosteach.services.databinding.DataBindingService;
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
	 * method for generating all needed data for xml confirmation
	 * */
	@RequestMapping(value="/getInvoices", method=RequestMethod.GET, produces={"application/json; charset=UTF-8"})
	public ResponseEntity<List<SPROutcomeInvoice>> getAllClientsRequests(){
		GetDetails currentUser = new GetDetails();
		return new ResponseEntity<List<SPROutcomeInvoice>>(invoicesService.getInvoicesByLocalDate(currentUser.getDB(), currentUser.getName(), currentUser.getPass()),HttpStatus.OK);
	}
	@RequestMapping(value="/connectToFtpEDI", method=RequestMethod.GET, produces={"application/json; charset=UTF-8"})
	public ResponseEntity<String> checkConnectionFTP(){
		String result;
		
		FTPConnectionEDI connection = new FTPConnectionEDI();
			boolean check = connection.setConnection();
			if(check==true){
				boolean send = connection.sendFiles();
				result = "{'result':'send_"+send+"'}";
			}
			else{
				result = "{'result':'connection_"+check+"'}";
			}
		System.out.println(result);
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}
	/**
	 * method for generating all needed data for xml confirmation
	 * */
	@RequestMapping(value="/confirm", method=RequestMethod.POST, produces={"application/json; charset=UTF-8"})
	public ResponseEntity<List<ResultLog>> confirmRequests(@RequestBody String request,@RequestHeader("key") String option){
		List<ResultLog> result =null;
		XmlGenerator generator = new XmlGenerator();
		if(option.equals("notificate")){
			result = generator.generateNotification(request);
		}else if(option.equals("confirm")){
			result = generator.generateConfirmation(request);
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
}