/**
 * @author Pavlenko R.A.
 */
package com.rosteach.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rosteach.entities.ClientRequest;
import com.rosteach.entities.Report;
import com.rosteach.services.ReportService;
import com.rosteach.services.databinding.DataBindingService;


@Controller
public class DataLogisticController {
	//private static final Logger logger = LoggerFactory.getLogger(DataLogistic.class);
	@Autowired
	private ReportService reportService;
	
	@Autowired
	private DataBindingService dataService;
	
	@RequestMapping(value = "/Data")
	public String page() {
		return "DataLogistic";
	}
	@RequestMapping(value="/getReports", method=RequestMethod.GET, produces={"application/json"})
	public @ResponseBody ResponseEntity<List<Report>> getAllReports(HttpServletRequest request){
		String requestValue = request.getQueryString(); 
		return new ResponseEntity<List<Report>>(reportService.getAllReports(requestValue),HttpStatus.OK);
	}
	@RequestMapping(value = "/databind", method=RequestMethod.GET)
	public @ResponseBody String bindData(@RequestParam("inputDataBase") String inputdata,
			@RequestParam("name") String name,
			@RequestParam("password") String password,
			@RequestParam("selectFrom") String databasefrom,
			@RequestParam("selectIn") String databasein){
		String result = inputdata+","+name+","+password+","+databasefrom+","+databasein;
		System.out.println("--------------------------------------------------------"+result);
		//List<ClientRequest> inputData = dataService.getRequests("alter", name, password, "");
		
		//dataService.setClientsRequests(inputData, "sprinter_curent", name, "sysadmin", "");
		
		return result;
				//new ResponseEntity<List<ClientRequest>>(dataService.getRequests("alter", name, password, ""),HttpStatus.OK);ResponseEntity<List<ClientRequest>>
    }
}