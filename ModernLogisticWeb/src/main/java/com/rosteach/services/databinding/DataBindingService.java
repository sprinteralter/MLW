package com.rosteach.services.databinding;

import java.util.HashMap;
import java.util.List;

import com.rosteach.entities.ClientRequest;
import com.rosteach.entities.ClientRequestDetails;

public interface DataBindingService {
	public List<ClientRequestDetails> getDetailsById(Integer id,
			String database, 
			String username, 
			String password);
	public HashMap<ClientRequest,List<ClientRequestDetails>> getClientsRequests(
			String database,
			String username,
			String password,
			String inputIds,
			String startDate,
			String endDate);
	public String setClientsRequestsWithDetails(
			HashMap<ClientRequest,List<ClientRequestDetails>> clientsRequests,
			String database,
			String username,
			String password);
}
