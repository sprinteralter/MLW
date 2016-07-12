package com.rosteach.controllers.ferrero;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.rosteach.entities.ferrero.FerreroRequest24;
import com.rosteach.services.ferrero.FerreroRequestService;



@RestController
@RequestMapping(value= "getOrders")
public class Rest {

	@Autowired
	private FerreroRequestService requestsService;
	
	
	/*@RequestMapping(value="/get", method=RequestMethod.GET, produces={"application/json; charset=UTF-8"})
	public ResponseEntity<List<FerreroRequest24>> getAllReports(){
		//List<FerreroRequest24> all = requestsService.getAllRequests();
		return new ResponseEntity<List<FerreroRequest24>>(requestsService.getAllRequests(),HttpStatus.OK);
		
	}*/
	
	@RequestMapping(value="/post", method=RequestMethod.POST, produces={"application/json; charset=UTF-8"})
	public ResponseEntity<List<FerreroRequest24>> ferr24(@RequestBody String date){
		
		//JSONObject obj = new JSONObject(date);
		Date start = null;
		Date end = null;
		
		try {
			JSONArray arr = new JSONArray(date);
			JSONObject startDateJSON = arr.getJSONObject(0);
			JSONObject endDateJSON = arr.getJSONObject(1);

			String startDate = startDateJSON.getString("startdate");
			String endDate = endDateJSON.getString("enddate");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			start = new java.sql.Date( sdf.parse(startDate).getTime()) ;
			end = new java.sql.Date( sdf.parse(endDate).getTime()) ;

		} catch (JSONException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("---------------------date----------"+date);
		
		
		return new  ResponseEntity<List<FerreroRequest24>>(requestsService.getOrders24(start, end ),HttpStatus.OK); //esponseEntity<String>(date,HttpStatus.OK);//
	}
	
}
