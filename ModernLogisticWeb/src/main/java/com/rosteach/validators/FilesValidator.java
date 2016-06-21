/**
 * @author Rostislav Pavlenko
 * */
package com.rosteach.validators;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FilesValidator {
	private static final Logger logger = LoggerFactory.getLogger(FilesValidator.class);
	//method for checking type of the each file
	public boolean checkType(MultipartFile[] file){
		for (MultipartFile each : file) {
			String temp = each.getOriginalFilename();
			if(temp.endsWith("xml")){
				logger.info("FileType: "+temp);
				return true;
			}
		}
		return false;
	}
	//method for checking all path of directory and if false create all tree of rootPath
	public File checkDirectory(File directory){
		if(!directory.exists()){
			directory.mkdirs();
			logger.info("Directory was created with path: "+directory.getAbsolutePath());
		}
		return directory;
	}
	//method for checking directory on files and delete it if find something 
	public void scanForFile(String rootPath){
		for (File each: new File(rootPath).listFiles()){
			//check each and delete it  
			if(each.isFile()){
				each.delete();
				logger.info("File with name :["+each.getName()+"] was deleted!!");
			}
		}
		
	}
}
