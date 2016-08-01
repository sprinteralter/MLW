/**
 * @author Rostislav Pavlenko
 * */
package com.rosteach.controllers;

import java.io.File;
import java.sql.SQLException;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.rosteach.DAO.InsertionDocInvoice;
import com.rosteach.DAO.eko.EkoDAO;
import com.rosteach.DAO.food.FoodDAO;
import com.rosteach.DAO.lktrans.LktransDAO;
import com.rosteach.DAO.novus.NovusDAO;
import com.rosteach.DAO.security.GetDetails;
import com.rosteach.DAO.tavria.TavriaDAO;
import com.rosteach.upload.FilesUploader;
import com.rosteach.validators.FilesValidator;

@Controller
public class XMLController {
	
	@Autowired
	private NovusDAO novus;
	
	@Autowired
	private EkoDAO eko;
	
	@Autowired
	private LktransDAO lk;
	
	@Autowired
	private FoodDAO food;
	
	@Autowired
	private TavriaDAO tavr;
	
	/**
	 * File upload mapping for Veres tab
	 * */
	@RequestMapping(value = "/uploadFile", method=RequestMethod.POST, produces={"text/plain;charset=UTF-8"})
	public @ResponseBody String uploadFile(@RequestParam("file[]") MultipartFile [] file){
		String result = "";
		//checking and saving file block
		FilesUploader files = new FilesUploader();
		FilesValidator validator = new FilesValidator();
		//validate all parameters
		File directory = validator.checkDirectory(files.getDirectory());
		validator.scanForFile(files.getRootPath());
		
		if(validator.checkType(file)==true){
			result = files.saveFiles(file,directory);
		}
		else {
			result = "Invalid type of file or files!!";
		}
		return result;
    }
	
	//Tampering Data from xml into Database for Veres tab 
	
	@RequestMapping(value = "/PushVeres", method = RequestMethod.GET,produces={"text/plain;charset=UTF-8"})
	public @ResponseBody String insertion() throws JAXBException,SQLException{
		String result = "";
		InsertionDocInvoice insertion = new InsertionDocInvoice();
		GetDetails currentUser = new GetDetails();
		/*String data="";
		if(dataBase.equals("alter_ros")){
			data="jdbc:firebirdsql:192.168.20.85/3050:alter_ros";
		} else if(dataBase.equals("Alter")){
			data="jdbc:firebirdsql:192.168.20.17/3050:alter";
		} else if(dataBase.equals("alter_curent")){
			data="jdbc:firebirdsql:192.168.20.13/3050:alter_curent";
		}	*/
			//check date of document
			if(insertion.checkDate()==true){
				insertion.insertData(currentUser.getDB(),currentUser.getName(),currentUser.getPass());
				result="Insertion into database was successfull, documents date: "+insertion.getDate();
			}
			else{
				result="Insertion into database was denied, out of date: "+insertion.getDate()+" !";
			}
		return result;
	}
	//-----------------------------------------NOVUS-------------------------------------------------------
	
	@RequestMapping(value = "/uploadNovus", method=RequestMethod.POST, produces={"text/plain;charset=UTF-8"})
	public @ResponseBody String uploadNovus(@RequestParam("file[]") MultipartFile [] file){
		String result = "";
		//checking and saving file block
		GetDetails currentUser = new GetDetails();
		FilesUploader files = new FilesUploader(currentUser.getName());
		FilesValidator validator = new FilesValidator();
		//validate all parameters
		File directory = validator.checkDirectory(files.getDirectory());
		validator.scanForFile(files.getRootPath());
		
		if(validator.checkType(file)==true){
			result = files.saveFiles(file,directory);
		}
		else {
			result = "Invalid type of file or files!!";
		}
		return result;
    }
	
	@RequestMapping(value = "/PushNovus", method = RequestMethod.GET, produces={"text/plain;charset=UTF-8"})
	public @ResponseBody String insertionNovus() throws JAXBException,SQLException{
		
		GetDetails currentUser = new GetDetails();
		String db = currentUser.getDB();
		String path="C:/MLW/"+currentUser.getName();
	 	
		String result = novus.Insert(db, currentUser.getName(), currentUser.getPass(), path);
		
		
		return result;
	}
	
	//ajax checking date of the document
	@RequestMapping(value = "/getDate", method = RequestMethod.GET)
	public @ResponseBody String getDate() throws JAXBException{
		String result = new InsertionDocInvoice().getDate();
		return result;
	}
	
	//-----------------------------------------EKO-------------------------------------------------------
	
	@RequestMapping(value = "/uploadEko", method=RequestMethod.POST, produces={"text/plain;charset=UTF-8"})
	public @ResponseBody String uploadEko(@RequestParam("file[]") MultipartFile [] file){
		String result = "";
		//checking and saving file block
		GetDetails currentUser = new GetDetails();
		FilesUploader files = new FilesUploader(currentUser.getName());
		FilesValidator validator = new FilesValidator();
		//validate all parameters
		File directory = validator.checkDirectory(files.getDirectory());
		validator.scanForFile(files.getRootPath());
			
		if(validator.checkType(file)==true){
			result = files.saveFiles(file,directory);
		}
		else {
			result = "Invalid type of file or files!!";
		}
		return result;
	}
		
	@RequestMapping(value = "/PushEko", method = RequestMethod.GET, produces={"text/plain;charset=UTF-8"})
	public @ResponseBody String insertionEko() throws JAXBException,SQLException, InstantiationException, IllegalAccessException{
		
		GetDetails currentUser = new GetDetails();
		String db = currentUser.getDB();
		String path="C:/MLW/"+currentUser.getName();
		 	
		String result = eko.Insert(db, currentUser.getName(), currentUser.getPass(), path);
		return result;
	}	
	
	//-----------------------------------------LK-TRANS-------------------------------------------------------
	
		@RequestMapping(value = "/uploadLK", method=RequestMethod.POST, produces={"text/plain;charset=UTF-8"})
		public @ResponseBody String uploadLK(@RequestParam("file[]") MultipartFile [] file){
			String result = "";
			//checking and saving file block
			GetDetails currentUser = new GetDetails();
			FilesUploader files = new FilesUploader(currentUser.getName());
			FilesValidator validator = new FilesValidator();
			//validate all parameters
			File directory = validator.checkDirectory(files.getDirectory());
			validator.scanForFile(files.getRootPath());
				
			if(validator.checkType(file)==true){
				result = files.saveFiles(file,directory);
			}
			else {
				result = "Invalid type of file or files!!";
			}
			return result;
		}
			
		@RequestMapping(value = "/PushLK", method = RequestMethod.GET, produces={"text/plain;charset=UTF-8"})
		public @ResponseBody String insertionLK() throws JAXBException,SQLException, InstantiationException, IllegalAccessException{
			
			GetDetails currentUser = new GetDetails();
			String db = currentUser.getDB();
			String path="C:/MLW/"+currentUser.getName();
			 	
			String result = lk.Insert(db, currentUser.getName(), currentUser.getPass(), path);
			return result;
		}	
		
		//-----------------------------------------Socrat-------------------------------------------------------
		
		@RequestMapping(value = "/uploadSocrat", method=RequestMethod.POST, produces={"text/plain;charset=UTF-8"})
		public @ResponseBody String uploadSocrat(@RequestParam("file[]") MultipartFile [] file){
			String result = "";
			//checking and saving file block
			GetDetails currentUser = new GetDetails();
			FilesUploader files = new FilesUploader(currentUser.getName());
			FilesValidator validator = new FilesValidator();
			//validate all parameters
			File directory = validator.checkDirectory(files.getDirectory());
			validator.scanForFile(files.getRootPath());
			
			if(validator.checkType(file)==true){
				result = files.saveFiles(file,directory);
			}
			else {
				result = "Invalid type of file or files!!";
			}
			return result;
	    }
		
		@RequestMapping(value = "/PushSocrat", method = RequestMethod.GET, produces={"text/plain;charset=UTF-8"})
		public @ResponseBody String insertionSocrat() throws JAXBException,SQLException{
			
			GetDetails currentUser = new GetDetails();
			String db = currentUser.getDB();
			String path="C:/MLW/"+currentUser.getName();
		 	
			String result = novus.Insert(db, currentUser.getName(), currentUser.getPass(), path);
			
			
			return result;
		}
		
		//-----------------------------------------Food-------------------------------------------------------
		
				@RequestMapping(value = "/uploadFood", method=RequestMethod.POST, produces={"text/plain;charset=UTF-8"})
				public @ResponseBody String uploadFood(@RequestParam("file[]") MultipartFile [] file){
					String result = "";
					//checking and saving file block
					GetDetails currentUser = new GetDetails();
					FilesUploader files = new FilesUploader(currentUser.getName());
					FilesValidator validator = new FilesValidator();
					//validate all parameters
					File directory = validator.checkDirectory(files.getDirectory());
					validator.scanForFile(files.getRootPath());
					
					if(validator.checkType(file)==true){
						result = files.saveFiles(file,directory);
					}
					else {
						result = "Invalid type of file or files!!";
					}
					return result;
			    }
				
				@RequestMapping(value = "/PushFood", method = RequestMethod.GET, produces={"text/plain;charset=UTF-8"})
				public @ResponseBody String insertionFood() throws JAXBException,SQLException{
					
					GetDetails currentUser = new GetDetails();
					String db = currentUser.getDB();
					String path="C:/MLW/"+currentUser.getName();
				 	
					String result = null;
					try {
						result = food.Insert(db, currentUser.getName(), currentUser.getPass(), path);
					} catch (InstantiationException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					return result;
				}
				
				//-----------------------------------------TAVRIA-------------------------------------------------------
				
				@RequestMapping(value = "/uploadTavria", method=RequestMethod.POST, produces={"text/plain;charset=UTF-8"})
				public @ResponseBody String uploadTavria(@RequestParam("file[]") MultipartFile [] file){
					String result = "";
					//checking and saving file block
					GetDetails currentUser = new GetDetails();
					FilesUploader files = new FilesUploader(currentUser.getName());
					FilesValidator validator = new FilesValidator();
					//validate all parameters
					File directory = validator.checkDirectory(files.getDirectory());
					validator.scanForFile(files.getRootPath());
					
					if(validator.checkType(file)==true){
						result = files.saveFiles(file,directory);
					}
					else {
						result = "Invalid type of file or files!!";
					}
					return result;
			    }
				
				@RequestMapping(value = "/PushTavria", method = RequestMethod.GET, produces={"text/plain;charset=UTF-8"})
				public @ResponseBody String insertionTavria() throws JAXBException,SQLException{
					
					GetDetails currentUser = new GetDetails();
					String db = currentUser.getDB();
					String path="C:/MLW/"+currentUser.getName();
				 	
					String result = null;
					try {
						result = tavr.Insert(db, currentUser.getName(), currentUser.getPass(), path);
					} catch (InstantiationException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					return result;
				}
		
		
}
