package com.rosteach.DAO.security;

public interface UserDAO {
public User getUserByName(String name, String database);
public void createUser(User user);
}
