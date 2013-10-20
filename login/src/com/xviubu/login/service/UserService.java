package com.xviubu.login.service;
import com.xviubu.login.domain.User;


public interface UserService
{
	public boolean userIsExist(String username );
	public void addUser(User user);
	public User getUserByUsername(String username);
	public void saveUser(User user);
}
