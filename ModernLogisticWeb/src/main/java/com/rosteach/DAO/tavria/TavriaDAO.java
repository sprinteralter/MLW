package com.rosteach.DAO.tavria;

import java.sql.SQLException;

public interface TavriaDAO {
	public String Insert(String database, String name, String password, String path) throws SQLException, InstantiationException, IllegalAccessException;
}
