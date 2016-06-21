package com.rosteach.connection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class ConnectionFB {
	@Autowired
	@Qualifier("dataSourceAlter")
	public DataSource datasource;
	
	public Connection getConnection(){
		Connection connection = null; 
			try{
				connection = datasource.getConnection();
			}
			catch(SQLException ex){
				ex.getStackTrace();
			}
		return connection;
	}
	public void closeConnection(Connection conn){
		if(conn==null){
			return;
		}
		try{
			conn.close();
		}
		catch(SQLException ex){
			ex.getStackTrace();
		}
	}
}
