package com.rosteach.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rosteach.DAO.ClientsRequestsDAO;
import com.rosteach.entities.ClientsRequests;

@Repository
public class ClientsRequestsServiceImpl implements ClientsRequestsService{
	
	@Autowired
	private ClientsRequestsDAO requestsDAO;
	
	@Override
	public List<ClientsRequests> getAllRequests() {
		return requestsDAO.getAllRequests();
	}

}
