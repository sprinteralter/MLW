package com.rosteach.services.databinding;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rosteach.DAO.databinding.BindingDataDAO;
import com.rosteach.entities.ClientRequest;
import com.rosteach.entities.ClientRequestDetails;

@Repository
public class DataBindingServiceImpl implements DataBindingService{
	@Autowired
	private BindingDataDAO dataDAO;
	@Override
	public List<ClientRequestDetails> getDetailsById(Integer id,
			String database, 
			String username, 
			String password){
		return dataDAO.getDetailsById(id, database, username, password);
	};
	@Override
	public HashMap<ClientRequest,List<ClientRequestDetails>> getClientsRequests(
			String database,
			String username,
			String password,
			String inputIds,
			String startDate,
			String endDate) {
		return dataDAO.getClientsRequestsDetails(database, username, password, inputIds, startDate, endDate);
	}
	@Override
	public String setClientsRequestsWithDetails(
			HashMap<ClientRequest,List<ClientRequestDetails>> clientsRequests,
			String database,
			String username,
			String password){
		
		return dataDAO.setClientsRequestsWithDetails(clientsRequests,database,username,password);
	}

}
