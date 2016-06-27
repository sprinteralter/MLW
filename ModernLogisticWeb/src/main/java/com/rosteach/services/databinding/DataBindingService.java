package com.rosteach.services.databinding;

import java.util.HashMap;
import java.util.List;

import com.rosteach.entities.ClientRequest;
import com.rosteach.entities.ClientRequestDetails;

public interface DataBindingService {
	public HashMap<ClientRequest,List<ClientRequestDetails>> getClientsRequests(
			String database,
			String username,
			String password,
			String inputIds);
	public List<Integer> setClientsRequestsWithDetails(
			HashMap<ClientRequest,List<ClientRequestDetails>> clientsRequests,
			String database,
			String username,
			String password);
}
