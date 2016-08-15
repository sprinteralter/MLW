package com.rosteach.util;

import java.io.File;
import java.util.HashSet;
import java.util.Set;


public class FilesUtil {
	public FilesUtil(){
	}
	public Set<File> getCOMDOCS(){
		Set<File> files = new HashSet<File>();
		
			File directory =  new File("C:/MLW/XMLCOMDOC.xml/"); 
			if(directory.isDirectory()){
				String [] s = directory.list();
				for(int i=0;i<s.length;i++){
					File file = new File(directory,s[i]);
					if(file.isFile()||file.exists()){
						files.add(file);
					}
				}
			}
		return files;
	}
}
