package com.rosteach.services.databinding;

import java.util.List;

import com.rosteach.entities.ClientRequest;

public interface DataBindingService {
	public List<ClientRequest> getRequests(String database,String username,String password, String inputIds);
	public List<Integer> setClientsRequests(List<ClientRequest> clientsRequests,String database, String username, String password,
			String inputIds);
}
