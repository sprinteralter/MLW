package com.rosteach.services.databinding;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rosteach.DAO.databinding.BindingDataDAO;
import com.rosteach.entities.ClientRequest;

@Repository
public class DataBindingServiceImpl implements DataBindingService{
	@Autowired
	private BindingDataDAO dataDAO;
	@Override
	public List<ClientRequest> getRequests(String database,String username,String password, String inputIds) {
		return dataDAO.getClientsRequests(database, username, password, inputIds);
	}
	@Override
	public List<Integer> setClientsRequests(List<ClientRequest> clientsRequests, String database, String username,
			String password, String inputIds) {
		
		return dataDAO.setClientsRequests(clientsRequests, database, username, password, inputIds);
	}

}
