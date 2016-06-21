package com.rosteach.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rosteach.entities.Report;
import com.rosteach.util.HibernateUtil;

@Repository
public class ReportDAOImpl implements ReportDAO{
	
	@Autowired
	private HibernateUtil hibernateUtil;
	//constructor
	public ReportDAOImpl(){
	}
	
	@Override
	public int createReport(Report report) {
		return (int)hibernateUtil.create(report);
	}

	@Override
	public Report updateReport(Report report) {
		return hibernateUtil.update(report);
	}
	//method to delete report
	@Override
	public void deleteReport(int id) {
		Report report = new Report();
		report.setId(id);
		hibernateUtil.delete(report);
	}
	//method to get all reports 
	@Override
	public List<Report> getAllReports(String type) {
		return hibernateUtil.fetchAllExcel(Report.class,type);
	}
	
	@Override
	public List<Report> getAllReports() {
		return hibernateUtil.fetchAllExcel(Report.class);
	}
	@Override
	public Report getReport(int id) {
		return hibernateUtil.fetchById(id, Report.class);
	}
}
