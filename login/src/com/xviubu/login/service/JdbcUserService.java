package com.xviubu.login.service;

import com.xviubu.login.domain.User;
import com.xviubu.login.persistence.*;

public class JdbcUserService implements UserService
{
	public boolean userIsExist(String username)
	{
		User user = userDao.getUserByUsername(username); 
		if(user == null)
		{
			return false;
		}
		return true;

	}

	public void addUser(User user)
	{
		if(userIsExist(user.getUsername()))
		{
			throw new IllegalStateException("username already exists");
		}
		else
		{
			user.activate();
			userDao.addUser(user);
		}
	}
	public User getUserByUsername(String username)
	{
		return userDao.getUserByUsername(username);
	}
	public void saveUser(User user)
	{
		userDao.saveUser(user);
	}

	public void setUserDao(UserDao userDao)
	{
		this.userDao = userDao;
	}
	private UserDao userDao;
}
