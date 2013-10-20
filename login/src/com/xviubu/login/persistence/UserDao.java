package com.xviubu.login.persistence;

import com.xviubu.login.domain.User;

public interface UserDao
{
	public void addUser(User user);
	public void saveUser(User user);
	public User getUserByUsername(String username);
}
