package com.rosteach.services.ferrero;

import java.sql.Date;
import java.util.List;

import com.rosteach.entities.ferrero.FerreroRequest24;



public interface FerreroRequestService {
	

	public List<FerreroRequest24> getAllRequests();
	public List<FerreroRequest24> getOrders24(java.sql.Date startdate, java.sql.Date enddate );
}
