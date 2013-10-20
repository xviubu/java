package com.xviubu.spitter.persistence;

import org.springframework.jdbc.core.namedparam.*;
import java.sql.*;
import java.util.*;
import com.xviubu.spitter.model.*;

public class NamedParameterJdbcTemplateSpitterDao implements SpitterDao
{	

	public void addSpitter(Spitter spitter)
	{
		Map<String,Object>  params = new HashMap<String,Object>();
		params.put("username",spitter.getUsername());
		params.put("password",spitter.getPassword());
		params.put("fullname",spitter.getFullname());
		params.put("email",spitter.getEmail());
		params.put("update_by_email",spitter.isUpdateByEmail());

		jdbcTemplate.update(SQL_INSERT_SPITTER,params);
	}
	public void saveSpitter(Spitter spitter)
	{

	}
	public Spitter getSpitterById(long id)
	{
		return null;
	}
	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}
	private static final String SQL_INSERT_SPITTER = "insert into spitter(username,password,fullname,email,update_by_email) values(:username,:password,:fullname,:email,:update_by_email)"	;
	private NamedParameterJdbcTemplate jdbcTemplate;

}
