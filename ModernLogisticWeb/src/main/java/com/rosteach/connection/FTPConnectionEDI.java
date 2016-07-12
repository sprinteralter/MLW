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
		try{
			ftpClient.connect(server,connectport);
			ftpClient.login(user, password);
			ftpClient.enterLocalPassiveMode();
		}
		catch(IOException ex){
			System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
		}
		if(ftpClient.isConnected()){
			return true;
		}
		return false;
	}
	
	public boolean sendFiles(){
		try{
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			
			File pack = new File("C:/MLW/XMLDESADV/");
			if(pack.isDirectory()){
				String s[] = pack.list();
				for(int i=0;i<s.length;i++){
					File localFile = new File("C:/MLW/XMLDESADV/",s[i]);
					System.out.println(s[i]);
					String ftpFileName = s[i];
					
					//creating input stream
					InputStream inputStream = new FileInputStream(localFile);
					
					boolean send = ftpClient.storeFile(ftpFileName, inputStream);
					if(send){
						return true;
					}
					inputStream.close();
				}
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
