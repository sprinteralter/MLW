package com.rosteach.connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class FTPConnectionEDI {
	private final String server="ftpex.edi.su";
	private final int connectport=21;
	private final String user="uasprintftp";
	private final String password="c4eea2d5";
	
	private FTPClient ftpClient;
	
	public FTPConnectionEDI(){
	}
	
	public boolean setConnection(){
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
					}
					inputStream.close();
				}
				System.out.println("-----------outputsize----------"+outputsize);
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
            } catch (IOException ex) {
                ex.printStackTrace();
            }
		}
		return false;
	}
}
