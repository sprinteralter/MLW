package com.rosteach.DAO.lktrans;

import java.sql.SQLException;

public interface LktransDAO {
	public String Insert(String database, String name, String password, String path) throws SQLException, InstantiationException, IllegalAccessException;
}
