package com.rosteach.excel;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.rosteach.entities.SverkaPoVigruzkeBoxList;
import com.rosteach.entities.SverkaPoVigruzkeBox;

public class ExcelSverkaPoVigruzke extends AbstractExcelView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, 
			HSSFWorkbook workbook, 
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// New Excel sheet
		HSSFSheet all = workbook.createSheet("AllSverka");
		//HSSFSheet jacobs = workbook.createSheet("Jacobs");
		//Excel file name change
		response.setHeader("Content-Disposition", "attachment; filename=sverka.xls");
		
		//create style for table header 
		CellStyle styleHeader = workbook.createCellStyle();
		styleHeader.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		styleHeader.setFillPattern(CellStyle.SOLID_FOREGROUND);
		styleHeader.setBorderBottom(CellStyle.BORDER_THIN);
		styleHeader.setBorderTop(CellStyle.BORDER_THIN);
		styleHeader.setBorderRight(CellStyle.BORDER_THIN);
		styleHeader.setBorderLeft(CellStyle.BORDER_THIN);
			
		//create font for header
		Font font = workbook.createFont();
		font.setColor(IndexedColors.BLACK.getIndex());
		styleHeader.setFont(font);
			
		//create style for rows
		CellStyle styleRow = workbook.createCellStyle();
		styleRow.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		styleRow.setBorderTop(HSSFCellStyle.BORDER_THIN);
		styleRow.setBorderRight(HSSFCellStyle.BORDER_THIN);
		styleRow.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
		//Get data from model
		SverkaPoVigruzkeBoxList sverkaList = (SverkaPoVigruzkeBoxList) model.get("sverkaList");
		int rowCount = 3;
		int idCount = 1;
		int size = 0;
		for(SverkaPoVigruzkeBox row: sverkaList.getSverkaList()){
			HSSFRow excelRow = all.createRow(rowCount++);	
			
			excelRow.createCell(0).setCellValue(idCount++);
			excelRow.getCell(0).setCellStyle(styleRow);
			
			excelRow.createCell(1).setCellValue(row.getClientsName());
			excelRow.getCell(1).setCellStyle(styleRow);
			
			excelRow.createCell(2).setCellValue(row.getOrder());
			excelRow.getCell(2).setCellStyle(styleRow);
			
			excelRow.createCell(3).setCellValue(row.getShipment());
			excelRow.getCell(3).setCellStyle(styleRow);
		}
		//set excel header
		setExcelHeaderTable(all,styleHeader);
		//set autosize for needed columns
		for(int i=0; i<=5; i++){
			all.autoSizeColumn(i);
		}
				
		size = idCount+2;
		setExcelHeader(all,request,size);
		all.setColumnWidth(6, 3000);
			
		setExcelHeaderName(all,request,workbook);
	}
	public void setExcelHeaderTable(HSSFSheet excelSheet, CellStyle styleHeader){
		//set Excel Header names second row
		HSSFRow excelHeaderSecondRow = excelSheet.createRow(2);
		//create our cells in 2 row
		excelHeaderSecondRow.createCell(0).setCellValue("#");
		excelHeaderSecondRow.getCell(0).setCellStyle(styleHeader);
		
		excelHeaderSecondRow.createCell(1).setCellValue("Имя клиента");
		excelHeaderSecondRow.getCell(1).setCellStyle(styleHeader);
		
		excelHeaderSecondRow.createCell(2).setCellValue("Заказано");
		excelHeaderSecondRow.getCell(2).setCellStyle(styleHeader);
		
		excelHeaderSecondRow.createCell(3).setCellValue("Отгружено");
		excelHeaderSecondRow.getCell(3).setCellStyle(styleHeader);
	}
	public void setExcelHeader(HSSFSheet excelSheet, HttpServletRequest request, int size){
		//set Excel Header names first row
		HSSFRow excelHeaderFirstRow = excelSheet.createRow(1);	
		String orderSum = "SUM(C4:C"+size+")";
		String shipmentSum = "SUM(D4:D"+size+")";
		
		//excelHeaderFirstRow.createCell(0).setCellValue("������ �������: "+request.getParameter("startDate"));
		//excelHeaderFirstRow.createCell(2).setCellValue("����� �������: "+request.getParameter("endDate"));
		
		excelHeaderFirstRow.createCell(2).setCellType(HSSFCell.CELL_TYPE_FORMULA);
		excelHeaderFirstRow.getCell(2).setCellFormula(orderSum);
		
		excelHeaderFirstRow.createCell(3).setCellType(HSSFCell.CELL_TYPE_FORMULA);
		excelHeaderFirstRow.getCell(3).setCellFormula(shipmentSum);
	}
	public void setExcelHeaderName(HSSFSheet excelSheet, HttpServletRequest request, HSSFWorkbook workbook){
		HSSFRow excelHeaderFirstRow = excelSheet.createRow(0);
		CellStyle style = workbook.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		excelHeaderFirstRow.createCell(1).setCellValue("Отчет-сверка по выгрузке(ящ)");
		excelHeaderFirstRow.getCell(1).setCellStyle(style);
	}

}
