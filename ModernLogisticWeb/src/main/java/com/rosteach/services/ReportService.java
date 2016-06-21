package com.rosteach.services;

import java.util.List;

import com.rosteach.entities.Report;

public interface ReportService {
	public int createReport(Report report);
	public Report updateReport(Report report);
	public void deleteReport(int id);
	public List<Report> getAllReports(String type);
	public List<Report> getAllReports();
	public Report getReport(int id);
}
