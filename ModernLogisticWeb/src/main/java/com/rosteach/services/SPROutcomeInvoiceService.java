package com.rosteach.services;

import java.util.List;

import com.rosteach.entities.SPROutcomeInvoice;
import com.rosteach.entities.SPROutcomeInvoiceDetails;

public interface SPROutcomeInvoiceService {
	public List<SPROutcomeInvoice> getInvoicesByLocalDate(String date);
	public List<SPROutcomeInvoiceDetails> getInvoicesDetailsById(Integer id,String database, String username, String password);
}
