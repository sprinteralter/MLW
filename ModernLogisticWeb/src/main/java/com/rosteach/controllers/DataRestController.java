package com.rosteach.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rosteach.entities.ClientsRequests;
import com.rosteach.services.ClientsRequestsService;

@RestController
@RequestMapping(value= "/getData")
public class DataRestController {
	@Autowired
	private ClientsRequestsService requestsService;
	@RequestMapping(value="/get", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<List<ClientsRequests>> getAllReports(){
		return new ResponseEntity<List<ClientsRequests>>(requestsService.getAllRequests(),HttpStatus.OK);
	}
}
