package com.rosteach.DAO.eko;

import java.sql.SQLException;

public interface EkoDAO {
	public String Insert(String database, String name, String password, String path) throws SQLException;

}
