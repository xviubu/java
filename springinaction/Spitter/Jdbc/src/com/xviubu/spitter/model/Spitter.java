package com.xviubu.spitter.model;
import java.util.*;

public class Spitter
{
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}

	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getFullname()
	{
		return fullname;
	}
	public void setFullname(String fullname)
	{
		this.fullname = fullname;
	}

	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}

	public boolean isUpdateByEmail()
	{
		return update_by_email;
	}
	public void setUpdateByEmail(boolean update_by_email)
	{
		this.update_by_email = update_by_email;
	}
	
	public List<Spittle> getSpittles()
	{
		return spittles;
	}
	public void setSpittles(List<Spittle> spittles)
	{
		this.spittles = spittles;
	}

	public String toString()
	{
		return "Id = " + this.getId() + "\n"
		   	+  "UserName = " + this.getUsername() + "\n"
			+  "PassWord = " + this.getPassword() + "\n"
			+  "FullName = " + this.getFullname() +"\n"
			+  "Email = " + this.getEmail() + "\n";
	}
	private long id;
	private String username;
	private String password;
	private String fullname;
	private String email;
	private boolean update_by_email;
	private List<Spittle> spittles;

}
