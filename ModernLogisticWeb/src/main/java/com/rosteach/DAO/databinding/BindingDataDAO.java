package com.rosteach.DAO.databinding;

import java.util.HashMap;
import java.util.List;

import com.rosteach.entities.ClientRequest;
import com.rosteach.entities.ClientRequestDetails;

public interface BindingDataDAO {
	public HashMap<ClientRequest,List<ClientRequestDetails>> getClientsRequestsDetails(
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
