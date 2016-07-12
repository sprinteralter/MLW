package com.rosteach.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.rosteach.DAO.security.GetDetails;
import com.rosteach.entities.ClientRequest;
import com.rosteach.entities.ClientRequestDetails;
//import com.rosteach.entities.ClientsRequests;
import com.rosteach.entities.DataBind;
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
	@RequestMapping(value="/getSalesInvoises", method=RequestMethod.GET, produces={"application/json; charset=UTF-8"})
	public ResponseEntity<List<SPROutcomeInvoice>> getAllSalesInvoice(){
		return null;//new ResponseEntity<List<SalesInvoice>>(requestsService.getAllRequests(),HttpStatus.OK);
	}
	/**
	 * method for generating all needed data for xml confirmation
	 * */
	@RequestMapping(value="/confirm", method=RequestMethod.POST, produces={"application/json; charset=UTF-8"})
	public ResponseEntity<String> confirmRequests(@RequestBody String request){
		System.out.println("------------------------------"+request);
		
		XmlGenerator generator = new XmlGenerator();
		
		boolean result = generator.generateNotification(request);
		
		System.out.println("--------------------"+result);
		
		
		return new ResponseEntity<String>(request,HttpStatus.OK);
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
		HashMap<ClientRequest,List<ClientRequestDetails>> service = dataService.getClientsRequests("alter_curent", name, password, ids, startDate, endDate);
		
		return dataService.setClientsRequestsWithDetails(service,"sprinter_curent",name,"sysadmin");
		//new ResponseEntity<List<ClientRequest>>(dataService.getRequests("alter", name, password, ""),HttpStatus.OK);ResponseEntity<List<ClientRequest>>
    }
}