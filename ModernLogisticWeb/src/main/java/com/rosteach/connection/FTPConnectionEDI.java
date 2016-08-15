package com.rosteach.connection;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import com.rosteach.entities.Links;

public class FTPConnectionEDI {
	private final String server="ftpex.edi.su";
	private final int connectport=21;
	private FTPClient ftpClient;
	
	public FTPConnectionEDI(){
	}
	
	public boolean getConnection(String user,String password){
		ftpClient = new FTPClient();
		int reply;
		try{
			ftpClient.connect(server,connectport);
			ftpClient.login(user, password);
			ftpClient.enterLocalPassiveMode();
			
			System.out.println("-------------------------ftpReplyString--------------------------"+ftpClient.getReplyString());
		
			reply = ftpClient.getReplyCode();
			System.out.println("------------------reply-------------"+reply);
		}
		catch(IOException ex){
			System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
		}
		System.out.println("inconnection------"+ftpClient.isConnected());	
		return ftpClient.isConnected();
	}
	public boolean sendFiles(String path){
		try{
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			
			File pack = new File(path);
			System.out.println("-----------pack name-----------"+pack.getName());
			if(pack.isDirectory()){
				String s[] = pack.list();
				int size = s.length;
				int outputsize = 0;
				boolean send;
				System.out.println("-----------number of files----------"+size);
				System.out.println("-----------starting sending----------");
				for(int i=0;i<s.length;i++){
					File localFile = new File(path,s[i]);
					System.out.println("-----------file name---------"+s[i]);
					String ftpFileName ="outbox/"+ s[i];
					
					//creating input stream
					InputStream inputStream = new FileInputStream(localFile);
					
					send = ftpClient.storeFile(ftpFileName, inputStream);
					System.out.println("-----------send----------"+send);
					if(send==true){
						outputsize=i+1;
						System.out.println("-----------outputsize----------"+outputsize);
					}
					inputStream.close();
					System.out.println("-------------File is deleted: "+localFile.delete());
				}
				
				if(size!=outputsize){
					return false;
				}
				return true;
			}
			else{
				return false;
			}
		}
		catch(IOException ex){
			System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
		}
		finally{
			try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
                System.out.println("connection-------------"+ftpClient.isConnected());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
		}
		return false;
	}
	public boolean getCOMDOCS(String path){
		try{
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			
			System.out.println("-----------starting comdoc----------");
			//File downloadpath = new File("C:/MLW/XMLCOMDOC/");
			
			File directory =  new File("C:/MLW/XMLCOMDOC.xml/"); 
			FTPFile [] remoteFiles = ftpClient.listFiles(path);
			if(!directory.exists()){
				directory.mkdirs();
			}
				for(int i=0;i<remoteFiles.length;i++){
					String name = remoteFiles[i].getName();
					if(name.startsWith("comdoc") & name.endsWith("xml")){
						File downloadpath = new File("C:/MLW/XMLCOMDOC.xml/"+name);
						OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadpath));
						System.out.println(name);
						boolean success = ftpClient.retrieveFile(path+name, outputStream);
						System.out.println(success);
						if(success){	
							outputStream.close();
						}
					}
				}
		}
		catch(IOException ex){
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		finally{
			try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
                System.out.println("incomdoc-connection-------------"+ftpClient.isConnected());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
		}
		return true;
	}
}
