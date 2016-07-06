package com.rosteach.controllers.ferrero;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rosteach.entities.ferrero.FerreroRequest;
import com.rosteach.services.ClientsRequestsService;
import com.rosteach.services.ferrero.FerreroRequestService;



@RestController
@RequestMapping(value= "/getOrders")
public class Rest {

	@Autowired
	private FerreroRequestService requestsService;
	
	
	@RequestMapping(value="/get", method=RequestMethod.GET, produces={"application/json; charset=UTF-8"})
	public ResponseEntity<List<FerreroRequest>> getAllReports(){
		
		List<FerreroRequest> all = requestsService.getAllRequests();
	    
		
		return new ResponseEntity<List<FerreroRequest>>(requestsService.getAllRequests(),HttpStatus.OK);
	}
	
}
