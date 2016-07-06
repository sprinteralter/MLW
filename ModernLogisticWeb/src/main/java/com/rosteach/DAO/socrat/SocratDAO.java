package com.rosteach.DAO.socrat;

import java.sql.SQLException;

public interface SocratDAO {
	public String Insert(String database, String name, String password, String path) throws SQLException, InstantiationException, IllegalAccessException;

}
