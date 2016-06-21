/**
 * @author Rostislav Pavlenko
 * */
package com.rosteach.upload;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FilesUploader {
	private static final Logger logger = LoggerFactory.getLogger(FilesUploader.class);
	private String fileName = null;
	private String rootPath = "C:/MLW/XMLDOC/";
	private File directory = new File(rootPath);
	
	public FilesUploader(){		
	}
	
	public FilesUploader(String name){	
		rootPath = rootPath+name+"/";
		directory = new File(rootPath);
		System.out.println(rootPath);
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public File getDirectory() {
		return directory;
	}

	public void setDirectory(File directory) {
		this.directory = directory;
	}

	//method for saving files 
	public String saveFiles(MultipartFile[] file, File directory){
		String result ="";
		for(int i=0;i<file.length;i++){
			try {
				byte[] fileBytes = file[i].getBytes();	
				fileName=file[i].getOriginalFilename();
                File newFile = new File(directory.getAbsolutePath(),fileName);
                
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newFile));
                stream.write(fileBytes);
                stream.flush();
                stream.close();
                result+="--Server directory.path: " + rootPath+"\n"+"    File original name: " + file[i].getOriginalFilename()+"\n"+"    File content type: " + file[i].getContentType()+"\n"+"    File is saved under: "+newFile+"\n";
                logger.info(result);
			} 
			catch (Exception e) {
            	result = e.getMessage();
            }
		}
		return result;
	}
}
