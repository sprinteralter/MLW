package com.rosteach.DAO.security;

public interface UserDAO {
public User getUserByName(String name);
public void createUser(User user);
}
