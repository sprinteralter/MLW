package com.rosteach.DAO.orders;

import java.sql.SQLException;

public interface OrdersDAO {
	public String Insert(String database, String name, String password, String path, int mainID) throws SQLException, InstantiationException, IllegalAccessException;
}
