package com.rosteach.DAO.novus;

import java.sql.SQLException;

public interface NovusDAO {
	public String Insert(String database, String name, String password, String path) throws SQLException;
}
