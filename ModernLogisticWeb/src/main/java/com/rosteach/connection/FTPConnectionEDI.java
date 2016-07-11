package com.rosteach.connection;

import java.io.IOException;

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
		return true;
	}
}
