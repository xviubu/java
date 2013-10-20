package com.xviubu.spitter.persistence;

import java.sql.*;
import java.util.*;
import org.springframework.jdbc.core.support.*;
import com.xviubu.spitter.model.*;

public class SimpleJdbcDaoSupportDao extends JdbcDaoSupport implements SpitterDao
{

	public void addSpitter(Spitter spitter)
	{
		getJdbcTemplate().update(SQL_INSERT_SPITTER,spitter.getUsername(),spitter.getPassword(),spitter.getFullname(),spitter.getEmail(),spitter.isUpdateByEmail());
	}

	public void saveSpitter(Spitter spitter)
	{

	}
	public Spitter getSpitterById(long id)
	{
		return null;
	}


	private static final  String SQL_INSERT_SPITTER = "insert into spitter(username,password,fullname,email,update_by_email) values(?,?,?,?,?)";
}
