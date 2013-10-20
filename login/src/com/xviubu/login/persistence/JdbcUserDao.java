package com.xviubu.login.persistence;
import com.xviubu.login.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import java.sql.*;
import java.util.*;

public class JdbcUserDao implements UserDao
{
	public void addUser(User user)
	{
		jdbcTemplate.update(SQL_INSERT_USER,user.getUsername(),user.getPassword(),user.getEmail(),user.isActive());
	}

	public void saveUser(User user)
	{
		jdbcTemplate.update(SQL_UPDATE_USER,user.getUsername(),user.getPassword(),user.getEmail(),user.isActive(),user.getId());
	}

	public User getUserByUsername(String username)
	{
		try
		{
		return jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_USERNAME,
				new ParameterizedRowMapper<User>()
				{
					public User mapRow(ResultSet rs,int rowNum) throws SQLException
					{
						User user = new User();
						user.setId(rs.getInt(1));
						user.setUsername(rs.getString(2));
						user.setPassword(rs.getString(3));
						user.setEmail(rs.getString(4));
						user.setActive(rs.getBoolean(5));
						
						return user;
					}
				}
				,username);
		}
		catch(EmptyResultDataAccessException e)
		{
			return null;
		}
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}
	private JdbcTemplate jdbcTemplate;

	private static final String  SQL_INSERT_USER = "insert into user(username,password,email,active) values(?,?,?,?)";
	private static final String  SQL_UPDATE_USER = "update user set username = ?,password = ?,email = ?,active = ? where id = ?";
	private static final String  SQL_SELECT_USER_BY_USERNAME = "select * from user where username = ?";
}

