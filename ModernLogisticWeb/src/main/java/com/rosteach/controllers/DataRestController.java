package com.rosteach.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.rosteach.entities.ClientsRequests;
import com.rosteach.entities.DataBind;
import com.rosteach.services.ClientsRequestsService;
import com.rosteach.services.databinding.DataBindingService;

@RestController
@RequestMapping(value= "/getData")
public class DataRestController {
	@Autowired
	private ClientsRequestsService requestsService;
	
	@Autowired
	private DataBindingService dataService;
	
	@RequestMapping(value="/get", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<List<ClientsRequests>> getAllReports(){
		return new ResponseEntity<List<ClientsRequests>>(requestsService.getAllRequests(),HttpStatus.OK);
	}
	@RequestMapping(value = "/databind", method=RequestMethod.POST,consumes={"application/json"}, produces={"application/json"})
	public String bindData(@RequestBody String request){
		System.out.println("----------------------------"+request);
		
		String name = new Gson().fromJson(request, DataBind.class).getName();
		String password = new Gson().fromJson(request, DataBind.class).getPassword();
		String dataFrom = new Gson().fromJson(request, DataBind.class).getDatabaseFrom();
		String dataTo = new Gson().fromJson(request, DataBind.class).getDatabaseTo();
		String ids = new Gson().fromJson(request, DataBind.class).getIds();
		
		System.out.println("name---------------"+name);
		
		dataService.getClientsRequests(dataFrom, name, password, ids);
		
		//dataService.setClientsRequests(inputData, "sprinter_curent", name, "sysadmin", "");
		
		return request;
		//new ResponseEntity<List<ClientRequest>>(dataService.getRequests("alter", name, password, ""),HttpStatus.OK);ResponseEntity<List<ClientRequest>>
    }
}
