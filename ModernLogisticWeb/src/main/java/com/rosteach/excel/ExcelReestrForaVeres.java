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

import com.rosteach.entities.ReestrForaVeres;
import com.rosteach.entities.ReestrForaVeresList;

public class ExcelReestrForaVeres extends AbstractExcelView{
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model, 
			HSSFWorkbook workbook,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// New Excel sheet
		HSSFSheet excelSheet = workbook.createSheet("Veres");
		//Excel file name change
		response.setHeader("Content-Disposition", "attachment; filename=reestr.xls");
		
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
		ReestrForaVeresList reestrList = (ReestrForaVeresList) model.get("reestrList");
		int rowCount = 3;
		int idCount = 1;
		int size = 0;
		for(ReestrForaVeres row: reestrList.getReestrList()){
			HSSFRow excelRow = excelSheet.createRow(rowCount++);	
			
			excelRow.createCell(0).setCellValue(idCount++);
			excelRow.getCell(0).setCellStyle(styleRow);
			
			excelRow.createCell(1).setCellValue(row.getRegnumber());
			excelRow.getCell(1).setCellStyle(styleRow);
			
			excelRow.createCell(2).setCellValue(row.getClientsName());
			excelRow.getCell(2).setCellStyle(styleRow);
			
			excelRow.createCell(3).setCellValue(row.getClientadresslocation());
			excelRow.getCell(3).setCellStyle(styleRow);
			
			excelRow.createCell(4).setCellValue(row.getComment1());
			excelRow.getCell(4).setCellStyle(styleRow);
			
			excelRow.createCell(5).setCellValue(row.getFoundation());
			excelRow.getCell(5).setCellStyle(styleRow);
			
			excelRow.createCell(6).setCellValue(row.getMainsumm());
			excelRow.getCell(6).setCellStyle(styleRow);
		}
		
		//set excel header
		setExcelHeaderTable(excelSheet,styleHeader);
		//set autosize for needed columns
		for(int i=0; i<=5; i++){
			excelSheet.autoSizeColumn(i);
		}
		
		size = idCount+2;
		setExcelHeader(excelSheet,request,size);
		excelSheet.setColumnWidth(6, 3000);
		
		setExcelHeaderName(excelSheet,request);
	}
	
	public void setExcelHeaderTable(HSSFSheet excelSheet, CellStyle styleHeader){
		//set Excel Header names second row
		HSSFRow excelHeaderSecondRow = excelSheet.createRow(2);
		excelHeaderSecondRow.createCell(0).setCellValue("#");
		excelHeaderSecondRow.getCell(0).setCellStyle(styleHeader);
		
		excelHeaderSecondRow.createCell(1).setCellValue("Рег. номер");
		excelHeaderSecondRow.getCell(1).setCellStyle(styleHeader);
		
		excelHeaderSecondRow.createCell(2).setCellValue("Имя клиента");
		excelHeaderSecondRow.getCell(2).setCellStyle(styleHeader);
		
		excelHeaderSecondRow.createCell(3).setCellValue("Адрес доставки");
		excelHeaderSecondRow.getCell(3).setCellStyle(styleHeader);
	
		excelHeaderSecondRow.createCell(4).setCellValue("Комментарий");
		excelHeaderSecondRow.getCell(4).setCellStyle(styleHeader);
		
		excelHeaderSecondRow.createCell(5).setCellValue("Ответственный");
		excelHeaderSecondRow.getCell(5).setCellStyle(styleHeader);
		
		excelHeaderSecondRow.createCell(6).setCellValue("Сумма");
		excelHeaderSecondRow.getCell(6).setCellStyle(styleHeader);
	}
	public void setExcelHeader(HSSFSheet excelSheet, HttpServletRequest request, int size){
		//set Excel Header names first row
		HSSFRow excelHeaderFirstRow = excelSheet.createRow(1);	
		String summ = "SUM(G4:G"+size+")";
		
		excelHeaderFirstRow.createCell(1).setCellValue("Дата с: "+request.getParameter("startDate"));
		excelHeaderFirstRow.createCell(3).setCellValue("Дата по: "+request.getParameter("endDate"));
		
		excelHeaderFirstRow.createCell(6).setCellType(HSSFCell.CELL_TYPE_FORMULA);
		excelHeaderFirstRow.getCell(6).setCellFormula(summ);
	}
	public void setExcelHeaderName(HSSFSheet excelSheet, HttpServletRequest request){
		HSSFRow excelHeaderFirstRow = excelSheet.createRow(0);
		
		excelHeaderFirstRow.createCell(3).setCellValue("ФОРА ВЕРЕС");
	}
}
