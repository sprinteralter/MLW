package com.rosteach.DAO.security;

import javax.servlet.http.HttpServletRequest;

public interface UserDAO {
public User getUserByName(String name, String database);
public void createUser(User user);
}
