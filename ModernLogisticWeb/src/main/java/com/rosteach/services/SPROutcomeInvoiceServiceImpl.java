package com.rosteach.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rosteach.DAO.SPROutcomeInvoiceDAO;
import com.rosteach.entities.SPROutcomeInvoice;
import com.rosteach.entities.SPROutcomeInvoiceDetails;

@Repository
public class SPROutcomeInvoiceServiceImpl implements SPROutcomeInvoiceService{
	@Autowired
	private SPROutcomeInvoiceDAO invoiceDAO;
	@Override
	public List<SPROutcomeInvoice> getInvoicesByLocalDate(String database, String username, String password) {
		return invoiceDAO.getInvoicesByLocalDate(database, username, password);
	}
	@Override
	public List<SPROutcomeInvoiceDetails> getInvoicesDetailsById(Integer id, String database, String username,
			String password) {
		return invoiceDAO.getInvoicesDetailsById(id, database, username, password);
	}
}
