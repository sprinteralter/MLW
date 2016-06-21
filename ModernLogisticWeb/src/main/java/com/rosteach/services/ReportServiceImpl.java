package com.rosteach.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rosteach.DAO.ReportDAO;
import com.rosteach.entities.Report;

@Service
@Transactional
public class ReportServiceImpl implements ReportService{
	@Autowired
	private ReportDAO reportDAO; 
	//constructor
	public ReportServiceImpl(){
	}
	@Override
	public int createReport(Report report) {
		return reportDAO.createReport(report);
	}

	@Override
	public Report updateReport(Report report) {
		return reportDAO.updateReport(report);
	}

	@Override
	public void deleteReport(int id) {
		reportDAO.deleteReport(id);
	}

	@Override
	public List<Report> getAllReports(String type) {
		return reportDAO.getAllReports(type);
	}

	@Override
	public Report getReport(int id) {
		return reportDAO.getReport(id);
	}
	@Override
	public List<Report> getAllReports() {
		return reportDAO.getAllReports();
	}

}
