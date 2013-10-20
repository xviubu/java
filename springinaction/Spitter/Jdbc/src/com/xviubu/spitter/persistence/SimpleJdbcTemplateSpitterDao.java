package com.xviubu.spitter.persistence;

import org.springframework.jdbc.core.simple.*;
import java.sql.*;
import com.xviubu.spitter.model.*;
import java.util.*;

public class SimpleJdbcTemplateSpitterDao implements SpitterDao
{

	public void addSpitter(Spitter spitter)
	{
		jdbcTemplate.update(SQL_INSERT_SPITTER,spitter.getUsername(),spitter.getPassword(),spitter.getFullname(),spitter.getEmail(),spitter.isUpdateByEmail());
	}

	public void saveSpitter(Spitter spitter)
	{
		jdbcTemplate.update(SQL_UPDATE_SPITTER,spitter.getUsername(),spitter.getPassword(),spitter.getFullname(),spitter.getEmail(),spitter.getId());
	}

	public Spitter getSpitterById(long id)
	{
		return jdbcTemplate.queryForObject(SQL_SELECT_SPITTER_BY_ID,
				new ParameterizedRowMapper<Spitter>()
				{
					public Spitter mapRow(ResultSet rs,int rowNum) throws SQLException
					{
						Spitter spitter = new Spitter();	
						spitter.setId(rs.getLong(1));
						spitter.setUsername(rs.getString(2));
						spitter.setPassword(rs.getString(3));
						spitter.setFullname(rs.getString(4));
						spitter.setEmail(rs.getString(5));
						return spitter;
					}
				},
				id);
	}

	public void setJdbcTemplate(SimpleJdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}
	private SimpleJdbcTemplate jdbcTemplate;

	private static final  String SQL_INSERT_SPITTER = "insert into spitter(username,password,fullname,email,update_by_email) values(?,?,?,?,?)";
	private static final String SQL_SELECT_SPITTER_BY_ID = "select id,username,password,fullname,email from spitter where id = ?";
	private static final String SQL_UPDATE_SPITTER = "update spitter set username = ?,password = ?,fullname = ?, email = ? where id = ?";
}

