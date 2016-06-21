package com.rosteach.DAO;

import java.util.List;

import com.rosteach.entities.Report;

public interface ReportDAO {
	public int createReport(Report sprinterReport);
	public Report updateReport(Report sprinterReport);
	public void deleteReport(int id);
	public List<Report> getAllReports(String type);
	public List<Report> getAllReports();
	public Report getReport(int id);
}
