package com.rosteach.DAO;

import java.util.List;

import com.rosteach.entities.SPROutcomeInvoice;
import com.rosteach.entities.SPROutcomeInvoiceDetails;

public interface SPROutcomeInvoiceDAO {
	public List<SPROutcomeInvoice> getInvoicesByLocalDate(String date);
	public List<SPROutcomeInvoiceDetails> getInvoicesDetailsById(Integer id,String database, String username, String password);
}
