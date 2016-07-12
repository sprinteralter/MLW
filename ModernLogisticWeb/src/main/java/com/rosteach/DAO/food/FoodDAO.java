package com.rosteach.DAO.food;

import java.sql.SQLException;

public interface FoodDAO {
	public String Insert(String database, String name, String password, String path) throws SQLException, InstantiationException, IllegalAccessException;

}
